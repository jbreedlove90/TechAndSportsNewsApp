package com.jasminebreedlove.techandsportsnews.tech

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jasminebreedlove.techandsportsnews.R
import com.jasminebreedlove.techandsportsnews.dao.Article

import kotlinx.android.synthetic.main.activity_article_list.*
import kotlinx.android.synthetic.main.article_list_content.view.*
import kotlinx.android.synthetic.main.article_list.*
import org.jetbrains.anko.AnkoLogger

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.view.View.GONE
import android.view.View.VISIBLE
import java.util.*

class TechArticleListActivity : AppCompatActivity(), AnkoLogger {

    private var twoPane: Boolean = false // todo: might need to move to viewmodel
    lateinit var techViewModel: TechViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)
        setSupportActionBar(toolbar)
        toolbar.title = title

        if (article_detail_container != null) {
            twoPane = true
        }

        // initialize viewmodel
        techViewModel = ViewModelProviders.of(this).get(TechViewModel::class.java)
        setupRecyclerView()

    //    getTechFeed()
    } // onCreate()

    fun setupRecyclerView() {
        progressBarTech.visibility = VISIBLE // todo: figure out why progress bar isn't showing
        // create observer
        val articleListObserver = Observer { articleList: ArrayList<Article>? ->
            if (articleList != null) {
                val techAdapter = TechArticleRecycler(this, articleList, twoPane)
                article_list.adapter = techAdapter
            }
        }

        progressBarTech.visibility = GONE
            // another way to write the observer
//        val seekBarValObserver = object : Observer<ArrayList<Article>> {
//            override fun onChanged(t: ArrayList<Article>?) {
//            }
//        }

        // observe data in viewmodel with observer
        techViewModel.loadNewsFromTech().observe(this, articleListObserver)
    } // setupRecyclerView()

    /*private fun getTechFeed() {
        info("getting tech news from abc rss feeds:::\n")
        NewsServiceImpl().setupRetrofit().getRssFeed("technologyheadlines").enqueue(object : Callback<Rss> {
            override fun onResponse(call: Call<Rss>?, response: Response<Rss>?) {
                response?.body()?.channel?.articleList?.forEach { articles.add(it) }

                response?.body()?.channel?.articleList?.forEach { info("Tech article info:::: title: " +
                        "${it.articleTitle}\ndescription: ${it.description}\npub date: ${it.pubDate}\ncategory: ${it.category}") }

                article_list.adapter = ArticleRecyclerViewAdapter(this@TechArticleListActivity, articles, twoPane)
            }

            override fun onFailure(call: Call<Rss>?, t: Throwable?) {
                info("Error in processing request:: ${t?.cause}")
            }
        })
    } // getTechFeed()*/

    // todo: add article link to view
}
