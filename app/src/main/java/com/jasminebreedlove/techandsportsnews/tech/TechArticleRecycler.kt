package com.jasminebreedlove.techandsportsnews.tech

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jasminebreedlove.techandsportsnews.R
import com.jasminebreedlove.techandsportsnews.dao.Article
import kotlinx.android.synthetic.main.article_list_content.view.*
import java.util.ArrayList

class TechArticleRecycler(private val parentActivity: TechArticleListActivity,
                          private val articles: ArrayList<Article>,
                          private val twoPane: Boolean) : RecyclerView.Adapter<TechArticleRecycler.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val article = v.tag as Article
            if (twoPane) {
                val fragment = TechArticleDetailFragment().apply {
                    arguments = Bundle().apply {
                        putString(TechArticleDetailFragment.ARTICLE_TITLE, article.articleTitle)
                        putString(TechArticleDetailFragment.ARTICLE_LINK, article.link)
                        putString(TechArticleDetailFragment.ARTICLE_PUB, article.pubDate)
                        putString(TechArticleDetailFragment.ARTICLE_DESCRIPTION, article.description)
                        putString(TechArticleDetailFragment.ARTICLE_CATEGORY, article.category)
                    }
                }
                parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.article_detail_container, fragment) // replace existing article content
                        .commit()
            } else {
                addTechFragment(article, v)

            }
        }
    }

    private fun addTechFragment(article: Article, v: View) {
        val intent = Intent(v.context, TechArticleDetailActivity::class.java).apply {
            putExtra(TechArticleDetailFragment.ARTICLE_TITLE, article.articleTitle)
            putExtra(TechArticleDetailFragment.ARTICLE_LINK, article.link)
            putExtra(TechArticleDetailFragment.ARTICLE_PUB, article.pubDate)
            putExtra(TechArticleDetailFragment.ARTICLE_DESCRIPTION, article.description)
            putExtra(TechArticleDetailFragment.ARTICLE_CATEGORY, article.category)
        }
        v.context.startActivity(intent)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.article_list_content, parent, false)
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
        val articleTitle: TextView = view.article_title
    } // inner custom ViewHolder class

}