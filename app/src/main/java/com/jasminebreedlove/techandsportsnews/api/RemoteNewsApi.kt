package com.jasminebreedlove.techandsportsnews.api

import com.jasminebreedlove.techandsportsnews.dao.Rss
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteNewsApi {

    @GET("abcnews/{endpoint}")
    fun loadRssFeed(@Path("endpoint") endpoint: String) : Call<Rss>
}