package com.jasminebreedlove.techandsportsnews.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jasminebreedlove.techandsportsnews.dao.Article
import com.jasminebreedlove.techandsportsnews.dao.Rss
//import org.jetbrains.anko.AnkoLogger
//import org.jetbrains.anko.info
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class NewsRepository {

    private fun initRetrofit() : RemoteNewsApi {
        return Retrofit.Builder().baseUrl("https://abcnews.go.com/")
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(Persister(AnnotationStrategy())))
                .build().create(RemoteNewsApi::class.java)
    }

    fun loadRssFeed(endpoint: String): LiveData<ArrayList<Article>> {
        val articles = MutableLiveData<ArrayList<Article>>()

        initRetrofit().loadRssFeed(endpoint).enqueue(object : Callback<Rss> {
            override fun onResponse(call: Call<Rss>?, response: Response<Rss>?) {
                articles.value = response?.body()?.channel?.articleList // set response to LiveData list
            //    response?.body()?.channel?.articleList?.forEach { articles.value = it }
            //    article_list.adapter = TechArticleListActivity.ArticleRecyclerViewAdapter(this@TechArticleListActivity, articles, twoPane)
            }

            override fun onFailure(call: Call<Rss>?, t: Throwable?) {
                //info("Error in processing request:: ${t?.cause}")
            }
        })
        return articles
    }
}