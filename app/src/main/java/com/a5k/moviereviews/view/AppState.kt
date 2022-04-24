package com.a5k.moviereviews.view

import com.a5k.moviereviews.data.model.DataModel

sealed class AppState{
    data class Success(val dataModel: DataModel):AppState()
    data class Loading(val progress:Boolean):AppState()
    data class Error(val error: Throwable):AppState()
}
