package com.jasminebreedlove.techandsportsnews.tech

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jasminebreedlove.techandsportsnews.R
import com.jasminebreedlove.techandsportsnews.dao.Article

import kotlinx.android.synthetic.main.activity_article_list.*
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

    } // onCreate()

    private fun setupRecyclerView() {
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

}
