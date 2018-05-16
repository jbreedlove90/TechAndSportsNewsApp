package com.jasminebreedlove.techandsportsnews.tech

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.jasminebreedlove.techandsportsnews.api.NewsRepository
import com.jasminebreedlove.techandsportsnews.dao.Article

class TechViewModel : ViewModel() {

    lateinit var articles: LiveData<ArrayList<Article>>
    // gets updated by lifecycle owner
    var articleTag: MutableLiveData<Article> = MutableLiveData()
    //var articleItem: LiveData<Article> = Transformations.switchMap(articleTag, (article) -> {})
    val newsRepo: NewsRepository = NewsRepository()


    fun getArticleList(): LiveData<ArrayList<Article>> = articles

    fun loadNewsFromTech(): LiveData<ArrayList<Article>> {
        articles = newsRepo.loadRssFeed("technologyheadlines")
        return articles
    }

    /*fun getArticle(article: Article) : LiveData<Article> {
        val liveData = article as LiveData<Article>
        return liveData
    }*/

    // todo: use transformations to map the list to an article to get article specific data


    //recycler functions
    fun bindArticle(article: Article) {

    }

}