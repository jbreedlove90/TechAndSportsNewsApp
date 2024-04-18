package com.jasminebreedlove.techandsportsnews.sports

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.jasminebreedlove.techandsportsnews.R
import com.jasminebreedlove.techandsportsnews.dao.Article
import com.jasminebreedlove.techandsportsnews.databinding.ActivitySportsarticleDetailBinding

class SportsArticleDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySportsarticleDetailBinding
    lateinit var sportsViewModel: SportsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySportsarticleDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.sportsDetailToolbar)

        binding.sportsFab.setOnClickListener {
            val article = intent.getSerializableExtra(SportsArticleDetailFragment.ARTICLE) as Article
            val shareBody = article.link
            val shareSub = article.articleTitle
            val sharingIntent = Intent(android.content.Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub)
                putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
            }
            startActivity(Intent.createChooser(sharingIntent, "Share what's happening in tech and sports via Tech & Sports News App!"))
        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            addSportsFragment()
        }

        // initialize viewmodel
        sportsViewModel = ViewModelProviders.of(this).get(SportsViewModel::class.java)

        if (binding.sportsarticleDetailContainer != null) {
            sportsViewModel.twoPane = true
        }
    }

    private fun addSportsFragment() {
        val fragment = SportsArticleDetailFragment()
                .newInstance(intent.getSerializableExtra(SportsArticleDetailFragment.ARTICLE) as Article)

        supportFragmentManager.beginTransaction()
                .add(R.id.sportsarticle_detail_container, fragment)
                .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    navigateUpTo(Intent(this, SportsArticleListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}
