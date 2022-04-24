package com.a5k.moviereviews.data.repository

import com.a5k.moviereviews.data.model.DataModel
import com.a5k.moviereviews.data.network.RetrofitData

class RepositoryImp(private val retrofit:RetrofitData):Repository {

    override suspend fun getDataModel(api: String, page: Int): DataModel? =retrofit.getDataModel(api, page)
}