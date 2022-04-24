package com.a5k.moviereviews.data.repository

import com.a5k.moviereviews.data.model.DataModel

interface Repository {
    suspend fun getDataModel(api:String, page:Int):DataModel?
}