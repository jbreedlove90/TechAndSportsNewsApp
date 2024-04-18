package com.jasminebreedlove.techandsportsnews.tech

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jasminebreedlove.techandsportsnews.api.NewsRepository
import com.jasminebreedlove.techandsportsnews.dao.Article

class TechViewModel : ViewModel() {

    lateinit var articles: LiveData<ArrayList<Article>>
    var twoPane: Boolean = false
    //var articleItem: LiveData<Article> = Transformations.switchMap(articleTag, (article) -> {})
    val newsRepo: NewsRepository = NewsRepository()

    fun loadNewsFromTech(): LiveData<ArrayList<Article>> {
        articles = newsRepo.loadRssFeed("technologyheadlines")
        return articles
    }

    fun getIsTwoPane() = twoPane

}