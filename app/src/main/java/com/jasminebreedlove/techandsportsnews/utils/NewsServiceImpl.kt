package com.jasminebreedlove.techandsportsnews.utils

import com.jasminebreedlove.techandsportsnews.dao.Rss
import org.jetbrains.anko.AnkoLogger
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET

import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

/**
 * Created by Jasmine Breedlove on 4/19/2018.
 */
class NewsServiceImpl : AnkoLogger{

    internal fun setupRetrofit() : NewsService {
        // build retrofit adapter
        val retrofit = Retrofit.Builder().baseUrl(BASE_ABC_NEWS_URL)
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(Persister(AnnotationStrategy()))).build()
        // call our interface with retrofit
        return retrofit.create(NewsService::class.java)
    }

    companion object {
        const val BASE_ABC_NEWS_URL = "http://abcnews.go.com/abcnews/"
    }

    interface NewsService {
        @GET("technologyheadlines")
        fun getTechNews() : Call<Rss>

        @GET("sportsheadlines")
        fun getSportsNews() : Call<Rss>
    }
}