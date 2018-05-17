package com.jasminebreedlove.techandsportsnews.sports

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jasminebreedlove.techandsportsnews.R
import com.jasminebreedlove.techandsportsnews.dao.Article

import kotlinx.android.synthetic.main.activity_sportsarticle_list.*
import kotlinx.android.synthetic.main.sportsarticle_list.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.ArrayList

class SportsArticleListActivity : AppCompatActivity(), AnkoLogger {

    private var twoPane: Boolean = false
    lateinit var sportsViewModel: SportsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sportsarticle_list)
        setSupportActionBar(sports_toolbar)
        sports_toolbar.title = title

        // initialize viewmodel
        sportsViewModel = ViewModelProviders.of(this).get(SportsViewModel::class.java)

        if (sportsarticle_detail_container != null) {
            twoPane = true
        }

        setupRecyclerView()
    } // onCreate()

    private fun setupRecyclerView() {
        sportsProgressBarTech.visibility = View.VISIBLE // todo: figure out why progress bar isn't showing
        // create observer
        val articleListObserver = Observer { articleList: ArrayList<Article>? ->
            if (articleList != null) {
                val sportsAdapter = SportsArticleRecycler(this, articleList, twoPane)
                sportsarticle_list.adapter = sportsAdapter
            }
        }

        sportsProgressBarTech.visibility = View.GONE
        // observe data in viewmodel with observer
        sportsViewModel.loadNewsFromSports().observe(this, articleListObserver)
    } // setupRecyclerView()

    // todo: add article link to view
}
