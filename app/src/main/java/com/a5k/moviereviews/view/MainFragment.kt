package com.a5k.moviereviews.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.a5k.moviereviews.R
import com.a5k.moviereviews.databinding.FragmentMainBinding
import com.a5k.moviereviews.view.adapter.MainAdapter
import com.a5k.moviereviews.viewmodel.MainViewModel

class MainFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    private val liveDataModel: MainViewModel by lazy {
        MainViewModel()
    }
    private val mainAdapter = MainAdapter()
    private var vb: FragmentMainBinding? = null
    private var page: Int = 0
    private var isLoad: Boolean = false
    private lateinit var rotation: Animation
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentMainBinding.inflate(inflater, container, false)
        return vb?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rotation = AnimationUtils.loadAnimation(requireContext(), R.anim.anim)
        vb?.recyclerContainer?.adapter = mainAdapter
        vb?.recyclerContainer?.setOnScrollChangeListener { view, _, _, _, _ ->
            val _view = (view as RecyclerView).layoutManager
            //number of elements in the container
            val numberOfElements = _view?.itemCount!!
            //last item to display
            val lastItemElement = (_view as LinearLayoutManager).findLastVisibleItemPosition()
            if (numberOfElements.minus(lastItemElement) > 5) {
                isLoad = true
            }
            if (numberOfElements.minus(lastItemElement) <= 5 && isLoad) {
                newPage()
            }

        }
        initLiveData()
    }

    private fun newPage() {
        isLoad = false
        page += 20
        liveDataModel.getDataModel(page = page)
    }

    private fun initLiveData() {
        liveDataModel._liveDataDataModel.observe(viewLifecycleOwner) { dataModel -> render(dataModel) }
        liveDataModel.getDataModel(page = page)
    }

    private fun render(dataModel: AppState) {
        when (dataModel) {
            is AppState.Success -> {
                mainAdapter.initListData(dataModel.dataModel)
                initAnimationProgress(false)
            }
            is AppState.Loading -> if (dataModel.progress) {
                initAnimationProgress(true)
            }
            is AppState.Error -> {
                Toast.makeText(requireContext(), dataModel.error.message, Toast.LENGTH_SHORT).show()
                initAnimationProgress(false)
            }
        }
    }

    private fun initAnimationProgress(flag: Boolean) {

        if (flag) {
            vb?.progressStatus.apply {
                this?.visibility = View.VISIBLE
                this?.animation = rotation
            }
        } else {
            vb?.progressStatus.apply {
                this?.visibility = View.GONE
                this?.animation = null
            }
        }

    }

}