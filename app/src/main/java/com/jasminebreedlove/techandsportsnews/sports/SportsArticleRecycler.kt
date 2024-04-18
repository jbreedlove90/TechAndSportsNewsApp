package com.jasminebreedlove.techandsportsnews.sports

import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jasminebreedlove.techandsportsnews.R
import com.jasminebreedlove.techandsportsnews.dao.Article
import com.jasminebreedlove.techandsportsnews.databinding.SportsarticleListContentBinding
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
        return ViewHolder(SportsarticleListContentBinding.inflate(LayoutInflater
            .from(parent.context), parent, false))
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

    inner class ViewHolder(view: SportsarticleListContentBinding) : RecyclerView.ViewHolder(view.root) {
        val articleTitle: TextView = view.sportsArticleTitle
    }
}