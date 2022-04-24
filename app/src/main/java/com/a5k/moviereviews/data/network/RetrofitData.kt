package com.a5k.moviereviews.data.network

import com.a5k.moviereviews.data.model.DataModel

interface RetrofitData {
    suspend fun getDataModel(api:String, page:Int):DataModel?
}