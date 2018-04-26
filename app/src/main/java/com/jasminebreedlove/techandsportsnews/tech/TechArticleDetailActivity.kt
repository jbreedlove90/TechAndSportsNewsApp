package com.jasminebreedlove.techandsportsnews.tech

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.jasminebreedlove.techandsportsnews.R
import kotlinx.android.synthetic.main.activity_article_detail.*

class TechArticleDetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        setSupportActionBar(detail_toolbar)

        fab.setOnClickListener { view ->
            val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val shareBody = intent.getStringExtra(TechArticleDetailFragment.ARTICLE_LINK)
            val shareSub = intent.getStringExtra(TechArticleDetailFragment.ARTICLE_TITLE)
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
        val fragment = TechArticleDetailFragment().apply {
            arguments = Bundle().apply {
                putString(TechArticleDetailFragment.ARTICLE_TITLE,
                        intent.getStringExtra(TechArticleDetailFragment.ARTICLE_TITLE))
                putString(TechArticleDetailFragment.ARTICLE_LINK, intent.getStringExtra(TechArticleDetailFragment.ARTICLE_LINK))
                putString(TechArticleDetailFragment.ARTICLE_PUB, intent.getStringExtra(TechArticleDetailFragment.ARTICLE_PUB))
                putString(TechArticleDetailFragment.ARTICLE_DESCRIPTION, intent.getStringExtra(TechArticleDetailFragment.ARTICLE_DESCRIPTION))
                putString(TechArticleDetailFragment.ARTICLE_CATEGORY, intent.getStringExtra(TechArticleDetailFragment.ARTICLE_CATEGORY))
            }
        }

        supportFragmentManager.beginTransaction()
                .add(R.id.article_detail_container, fragment)
                .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    navigateUpTo(Intent(this, TechArticleListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}
