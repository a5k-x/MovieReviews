package com.a5k.moviereviews.data.network

import com.a5k.moviereviews.data.model.DataModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImp: RetrofitData{

    override suspend fun getDataModel(api: String, page: Int): DataModel? =
       getService().getDataModel(page = page)

    private fun getService():ApiService=retrofit().create(ApiService::class.java)

    private fun retrofit () : Retrofit= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    companion object{
        private const val BASE_URL = "https://api.nytimes.com/svc/movies/v2/"
    }


}