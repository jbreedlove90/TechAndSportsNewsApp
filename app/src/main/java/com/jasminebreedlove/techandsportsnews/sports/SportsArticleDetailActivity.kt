package com.jasminebreedlove.techandsportsnews.sports

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.jasminebreedlove.techandsportsnews.R
import kotlinx.android.synthetic.main.activity_sportsarticle_detail.*


class SportsArticleDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sportsarticle_detail)
        setSupportActionBar(sports_detail_toolbar)

        sports_fab.setOnClickListener { view ->
            val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareBody = intent.getStringExtra(SportsArticleDetailFragment.ARTICLE_LINK)
            val shareSub = intent.getStringExtra(SportsArticleDetailFragment.ARTICLE_TITLE)
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub)
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(sharingIntent, "Share what's happening in tech and sports via Tech & Sports News App!"))
        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            addSportsFragment()
        }
    }

    private fun addSportsFragment() {
        val fragment = SportsArticleDetailFragment().apply {
            arguments = Bundle().apply {
                putString(SportsArticleDetailFragment.ARTICLE_TITLE,
                        intent.getStringExtra(SportsArticleDetailFragment.ARTICLE_TITLE))
                putString(SportsArticleDetailFragment.ARTICLE_LINK, intent.getStringExtra(SportsArticleDetailFragment.ARTICLE_LINK))
                putString(SportsArticleDetailFragment.ARTICLE_PUB, intent.getStringExtra(SportsArticleDetailFragment.ARTICLE_PUB))
                putString(SportsArticleDetailFragment.ARTICLE_DESCRIPTION, intent.getStringExtra(SportsArticleDetailFragment.ARTICLE_DESCRIPTION))
                putString(SportsArticleDetailFragment.ARTICLE_CATEGORY, intent.getStringExtra(SportsArticleDetailFragment.ARTICLE_CATEGORY))
            }
        }

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
