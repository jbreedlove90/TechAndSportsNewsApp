package com.jasminebreedlove.techandsportsnews.tech

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.jasminebreedlove.techandsportsnews.api.NewsRepository
import com.jasminebreedlove.techandsportsnews.dao.Article

class TechViewModel : ViewModel() {

    lateinit var articles: LiveData<ArrayList<Article>>
    //var articleItem: LiveData<Article> = Transformations.switchMap(articleTag, (article) -> {})
    val newsRepo: NewsRepository = NewsRepository()

    fun loadNewsFromTech(): LiveData<ArrayList<Article>> {
        articles = newsRepo.loadRssFeed("technologyheadlines")
        return articles
    }

}