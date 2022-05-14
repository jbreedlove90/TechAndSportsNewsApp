package com.jasminebreedlove.techandsportsnews.sports

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jasminebreedlove.techandsportsnews.R
import com.jasminebreedlove.techandsportsnews.dao.Article
import kotlinx.android.synthetic.main.sportsarticle_list_content.view.*
import java.util.ArrayList

class SportsArticleRecycler(private val parentActivity: SportsArticleListActivity,
                            private val articles: ArrayList<Article>,
                            private val twoPane: Boolean) : RecyclerView.Adapter<SportsArticleRecycler.ViewHolder>() {

    private val onClickListener: View.OnClickListener
    private val sportsViewModel = SportsViewModel().let { ViewModelProviders.of(parentActivity).get(SportsViewModel::class.java) }

    init {
        onClickListener = View.OnClickListener { v ->
            val article = v.tag as Article
            if (twoPane) {
                addFragmentToListActivity(article)
            } else {
                addFragmentToDetailActivity(article, v)
            }
        }
    }

    private fun addFragmentToListActivity(article: Article) {
        val fragment = SportsArticleDetailFragment().newInstance(article)
        parentActivity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.sportsarticle_detail_container, fragment)
                .commit()
    }

    private fun addFragmentToDetailActivity(article: Article, v: View) {
        val intent = Intent(v.context, SportsArticleDetailActivity::class.java).apply {
            putExtra(SportsArticleDetailFragment.ARTICLE, article)
        }
        v.context.startActivity(intent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.sportsarticle_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]

        with(holder.articleTitle) {
            tag = article
            text = article.articleTitle
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = articles.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val articleTitle: TextView = view.sports_article_title
    }
}