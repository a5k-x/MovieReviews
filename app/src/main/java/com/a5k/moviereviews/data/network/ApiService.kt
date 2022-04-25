package com.a5k.moviereviews.data.network

import com.a5k.moviereviews.data.model.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("reviews/all.json")
   suspend fun getDataModel(@Query("api-key") apiKey:String,
    @Query("offset")page:Int):DataModel?
}