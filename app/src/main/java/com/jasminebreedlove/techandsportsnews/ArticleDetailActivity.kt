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
            Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = ArticleDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ArticleDetailFragment.ARTICLE_TITLE,
                            intent.getStringExtra(ArticleDetailFragment.ARTICLE_TITLE))
                    putString(ArticleDetailFragment.ARTICLE_PUB, intent.getStringExtra(ArticleDetailFragment.ARTICLE_PUB))
                    putString(ArticleDetailFragment.ARTICLE_DESCRIPTION, intent.getStringExtra(ArticleDetailFragment.ARTICLE_DESCRIPTION))
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.article_detail_container, fragment)
                    .commit()
        }
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
