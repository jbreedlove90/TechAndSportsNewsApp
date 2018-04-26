package com.jasminebreedlove.techandsportsnews

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_sportsarticle_detail.*


class SportsArticleDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sportsarticle_detail)
        setSupportActionBar(sports_detail_toolbar)

        sports_fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            addSportsFragment()
        }
    }

    fun addSportsFragment() {
        val fragment = SportsArticleDetailFragment().apply {
            arguments = Bundle().apply {
                putString(SportsArticleDetailFragment.ARTICLE_TITLE,
                        intent.getStringExtra(SportsArticleDetailFragment.ARTICLE_TITLE))
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
