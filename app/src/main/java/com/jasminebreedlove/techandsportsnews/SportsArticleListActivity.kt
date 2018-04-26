package com.jasminebreedlove.techandsportsnews

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jasminebreedlove.techandsportsnews.dao.Article
import com.jasminebreedlove.techandsportsnews.dao.Rss

import com.jasminebreedlove.techandsportsnews.utils.NewsServiceImpl
import kotlinx.android.synthetic.main.activity_sportsarticle_list.*
import kotlinx.android.synthetic.main.article_list.*
import kotlinx.android.synthetic.main.sportsarticle_list.*
import kotlinx.android.synthetic.main.sportsarticle_list_content.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SportsArticleListActivity : AppCompatActivity(), AnkoLogger {

    private var twoPane: Boolean = false
    var articles: ArrayList<Article> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sportsarticle_list)

        setSupportActionBar(sports_toolbar)
        sports_toolbar.title = title

        if (sportsarticle_detail_container != null) {
            twoPane = true
        }

        getSportsFeed()
    } // onCreate()

    private fun getSportsFeed() {
        info("getting sports news from abc rss feeds:::\n")
        NewsServiceImpl().setupRetrofit().getRssFeed("sportsheadlines").enqueue(object : Callback<Rss> {
            override fun onResponse(call: Call<Rss>?, response: Response<Rss>?) {
                response?.body()?.channel?.articleList?.forEach { articles.add(it) }

                sportsarticle_list.adapter = SportsArticleListActivity.SimpleItemRecyclerViewAdapter(this@SportsArticleListActivity, articles, twoPane)
            }

            override fun onFailure(call: Call<Rss>?, t: Throwable?) {
                info("Error in processing request:: ${t?.cause}")
            }
        })
    }

    // todo: add article link to view
    class SimpleItemRecyclerViewAdapter(private val parentActivity: SportsArticleListActivity,
                                        private val articles: ArrayList<Article>,
                                        private val twoPane: Boolean) :
            RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val article = v.tag as Article
                if (twoPane) {
                    val fragment = SportsArticleDetailFragment().apply {
                        arguments = Bundle().apply {
                            putString(SportsArticleDetailFragment.ARTICLE_TITLE, article.articleTitle)
                            putString(SportsArticleDetailFragment.ARTICLE_LINK, article.link)
                            putString(SportsArticleDetailFragment.ARTICLE_PUB, article.pubDate)
                            putString(SportsArticleDetailFragment.ARTICLE_DESCRIPTION, article.description)
                            putString(SportsArticleDetailFragment.ARTICLE_CATEGORY, article.category)
                        }
                    }
                    parentActivity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.sportsarticle_detail_container, fragment)
                            .commit()
                } else {
                    val intent = Intent(v.context, SportsArticleDetailActivity::class.java).apply {
                        putExtra(SportsArticleDetailFragment.ARTICLE_TITLE, article.articleTitle)
                        putExtra(SportsArticleDetailFragment.ARTICLE_LINK, article.link)
                        putExtra(SportsArticleDetailFragment.ARTICLE_PUB, article.pubDate)
                        putExtra(SportsArticleDetailFragment.ARTICLE_DESCRIPTION, article.description)
                        putExtra(SportsArticleDetailFragment.ARTICLE_CATEGORY, article.category)
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.sportsarticle_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val article = articles[position]
            holder.articleTitle.text = article.articleTitle

            with(holder.articleTitle) {
                tag = article
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = articles.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val articleTitle: TextView = view.sports_article_title
        }
    }
}
