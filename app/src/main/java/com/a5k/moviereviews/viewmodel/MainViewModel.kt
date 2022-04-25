package com.a5k.moviereviews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a5k.moviereviews.BuildConfig
import com.a5k.moviereviews.data.network.RetrofitImp
import com.a5k.moviereviews.data.repository.RepositoryImp
import com.a5k.moviereviews.view.AppState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO)
    private var liveDataDataModel: MutableLiveData<AppState> = MutableLiveData()
    val _liveDataDataModel: LiveData<AppState> = liveDataDataModel

    fun getDataModel(page: Int) {
        scope.launch {
            liveDataDataModel.postValue(AppState.Loading(true))
            try {
                val data = RepositoryImp(RetrofitImp()).getDataModel(api = BuildConfig.APIKEY, page = page)
                if (data == null) {
                    liveDataDataModel.postValue(AppState.Error(Throwable(ERROR_DOWNLOAD)))
                } else {
                    liveDataDataModel.postValue(AppState.Success(data))
                }
            } catch (e: Exception) {
                liveDataDataModel.postValue(AppState.Error(Throwable(e.message)))
            }
        }
    }
    companion object{
        private const val ERROR_DOWNLOAD = "Error download"
    }
}