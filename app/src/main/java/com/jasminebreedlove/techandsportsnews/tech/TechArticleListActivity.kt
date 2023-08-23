package com.jasminebreedlove.techandsportsnews.tech

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jasminebreedlove.techandsportsnews.dao.Article

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.core.widget.NestedScrollView
import com.jasminebreedlove.techandsportsnews.R
import com.jasminebreedlove.techandsportsnews.databinding.ActivityArticleListBinding
import java.util.*

class TechArticleListActivity : AppCompatActivity() {

    private var twoPane: Boolean = false // todo: might need to move to viewmodel
    lateinit var techViewModel: TechViewModel
    private lateinit var binding: ActivityArticleListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityArticleListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = title

        //(activity as TechArticleDetailActivity)?.findViewById<NestedScrollView>(R.id.article_detail_container)
        /*if ((activity as TechArticleDetailActivity)?.findViewById<NestedScrollView>(R.id.article_detail_container) != null) {
            twoPane = true
        }*/

        // initialize viewmodel
        techViewModel = ViewModelProviders.of(this).get(TechViewModel::class.java)
        twoPane = techViewModel.getIsTwoPane()
        setupRecyclerView()

    } // onCreate()

    private fun setupRecyclerView() {
        binding.progressBarTech.visibility = VISIBLE // todo: figure out why progress bar isn't showing
        // create observer
        val articleListObserver = Observer { articleList: ArrayList<Article>? ->
            if (articleList != null) {
                val techAdapter = TechArticleRecycler(this, articleList, twoPane)
                binding.includeArticleListTech.articleList.adapter = techAdapter
            }
        }
        binding.progressBarTech.visibility = GONE
            // another way to write the observer
//        val seekBarValObserver = object : Observer<ArrayList<Article>> {
//            override fun onChanged(t: ArrayList<Article>?) {
//            }
//        }

        // observe data in viewmodel with observer
        techViewModel.loadNewsFromTech().observe(this, articleListObserver)
    } // setupRecyclerView()

}
