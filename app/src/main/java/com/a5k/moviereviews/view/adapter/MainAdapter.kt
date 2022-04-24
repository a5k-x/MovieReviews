package com.a5k.moviereviews.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.a5k.moviereviews.data.model.DataModel
import com.a5k.moviereviews.data.model.Movies
import com.a5k.moviereviews.databinding.ItemMoviesBinding

class MainAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listDataModel = mutableListOf<Movies>()

    @SuppressLint("NotifyDataSetChanged")
    fun initListData(listDataModel: DataModel){
        this.listDataModel.addAll(listDataModel.results)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(ItemMoviesBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as ViewHolder).bind(listDataModel[position])

    override fun getItemCount(): Int = if (listDataModel.isNotEmpty()){listDataModel.size} else 0
}

class ViewHolder(private val vb:ItemMoviesBinding):RecyclerView.ViewHolder(vb.root){
    fun bind(item: Movies) {
        vb.nameMovies.text = item.display_title
        vb.descriptionMovies.text = item.summary_short
        vb.imageMovies.load(item.multimedia.src){
            transformations(CircleCropTransformation())
        }
    }

}