package com.a5k.moviereviews.data.network

import com.a5k.moviereviews.data.model.DataModel
import retrofit2.http.GET
import retrofit2.http.Query


//https://api.nytimes.com/svc/movies/v2/reviews/all.json?api-key=CwpFrlbnKaou4Z1xtv87okeoZ0uydgHZ&offset=1
interface ApiService {
    @GET("reviews/all.json")
   suspend fun getDataModel(@Query("api-key") apiKey:String="CwpFrlbnKaou4Z1xtv87okeoZ0uydgHZ",
    @Query("offset")page:Int):DataModel?
}