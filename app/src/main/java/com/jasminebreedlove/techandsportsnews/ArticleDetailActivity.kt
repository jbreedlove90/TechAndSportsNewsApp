package com.jasminebreedlove.techandsportsnews

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        setSupportActionBar(detail_toolbar)

        fab.setOnClickListener { view ->
            val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareBody = intent.getStringExtra(ArticleDetailFragment.ARTICLE_LINK)
            val shareSub = intent.getStringExtra(ArticleDetailFragment.ARTICLE_TITLE)
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub)
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(sharingIntent, "Share what's happening in tech and sports via Tech & Sports News App!"))
        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            addTechFragment()


        }
    }

    private fun addTechFragment() {
        val fragment = ArticleDetailFragment().apply {
            arguments = Bundle().apply {
                putString(ArticleDetailFragment.ARTICLE_TITLE,
                        intent.getStringExtra(ArticleDetailFragment.ARTICLE_TITLE))
                putString(ArticleDetailFragment.ARTICLE_LINK, intent.getStringExtra(ArticleDetailFragment.ARTICLE_LINK))
                putString(ArticleDetailFragment.ARTICLE_PUB, intent.getStringExtra(ArticleDetailFragment.ARTICLE_PUB))
                putString(ArticleDetailFragment.ARTICLE_DESCRIPTION, intent.getStringExtra(ArticleDetailFragment.ARTICLE_DESCRIPTION))
                putString(ArticleDetailFragment.ARTICLE_CATEGORY, intent.getStringExtra(ArticleDetailFragment.ARTICLE_CATEGORY))
            }
        }

        supportFragmentManager.beginTransaction()
                .add(R.id.article_detail_container, fragment)
                .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    navigateUpTo(Intent(this, ArticleListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}
