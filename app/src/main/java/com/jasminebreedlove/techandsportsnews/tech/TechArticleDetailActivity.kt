package com.jasminebreedlove.techandsportsnews.tech

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.jasminebreedlove.techandsportsnews.R
import com.jasminebreedlove.techandsportsnews.dao.Article
import kotlinx.android.synthetic.main.activity_article_detail.*

class TechArticleDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        setSupportActionBar(detail_toolbar)

        fab.setOnClickListener {
            val article = intent.getSerializableExtra(TechArticleDetailFragment.ARTICLE) as Article
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
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            addTechFragment()
        }
    }

    private fun addTechFragment() {
        val fragment = TechArticleDetailFragment()
                .newInstance(intent.getSerializableExtra(TechArticleDetailFragment.ARTICLE) as Article)

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
