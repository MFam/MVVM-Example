package com.mfamstory.rxmvvmtest.data.network

import com.mfamstory.rxmvvmtest.data.network.model.ResRepository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface GithubAPI {

    // params is 'q', 'star'
    @GET("/search/repositories")
    fun search(@QueryMap params: Map<String, String>): Single<ResRepository>

}