package com.jasminebreedlove.techandsportsnews.sports

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.jasminebreedlove.techandsportsnews.api.NewsRepository
import com.jasminebreedlove.techandsportsnews.dao.Article

class SportsViewModel : ViewModel() {

    lateinit var articles: LiveData<ArrayList<Article>>
    //var articleItem: LiveData<Article> = Transformations.switchMap(articleTag, (article) -> {})
    val newsRepo: NewsRepository = NewsRepository()

    fun loadNewsFromSports(): LiveData<ArrayList<Article>> {
        articles = newsRepo.loadRssFeed("sportsheadlines")
        return articles
    }
}