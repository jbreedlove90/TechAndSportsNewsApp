package com.jasminebreedlove.techandsportsnews.sports

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.jasminebreedlove.techandsportsnews.dao.Article
import com.jasminebreedlove.techandsportsnews.databinding.ActivitySportsarticleListBinding

import java.util.ArrayList

class SportsArticleListActivity : AppCompatActivity() {

    private var twoPane: Boolean = false
    lateinit var sportsViewModel: SportsViewModel
    private lateinit var binding: ActivitySportsarticleListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySportsarticleListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.sportsToolbar)
        binding.sportsToolbar.title = title

        // initialize viewmodel
        sportsViewModel = ViewModelProviders.of(this).get(SportsViewModel::class.java)

        /*if (sportsarticle_detail_container != null) {
            twoPane = true
        }*/
        twoPane = sportsViewModel.getIsTwoPane()

        setupRecyclerView()
    } // onCreate()

    private fun setupRecyclerView() {
        binding.sportsProgressBarTech.visibility = View.VISIBLE // todo: figure out why progress bar isn't showing
        // create observer
        val articleListObserver = Observer { articleList: ArrayList<Article>? ->
            if (articleList != null) {
                val sportsAdapter = SportsArticleRecycler(this, articleList, twoPane)
                binding.includeSports.sportsarticleList.adapter = sportsAdapter
            }
        }

        binding.sportsProgressBarTech.visibility = View.GONE
        // observe data in viewmodel with observer
        sportsViewModel.loadNewsFromSports().observe(this, articleListObserver)
    } // setupRecyclerView()

    // todo: add article link to view
}
