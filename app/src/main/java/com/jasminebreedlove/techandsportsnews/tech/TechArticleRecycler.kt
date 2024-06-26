package com.jasminebreedlove.techandsportsnews.tech

import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jasminebreedlove.techandsportsnews.R
import com.jasminebreedlove.techandsportsnews.dao.Article
import com.jasminebreedlove.techandsportsnews.databinding.ArticleListContentBinding
import java.util.ArrayList

class TechArticleRecycler(private val parentActivity: TechArticleListActivity,
                          private val articles: ArrayList<Article>,
                          private val twoPane: Boolean) : RecyclerView.Adapter<TechArticleRecycler.ViewHolder>() {

    private val onClickListener: View.OnClickListener
    private val techViewModel = TechViewModel().let { ViewModelProviders.of(parentActivity).get(TechViewModel::class.java) }

    init {
        onClickListener = View.OnClickListener { v ->
            // transfer these actions to view model

            val article = v.tag as Article
        //    techViewModel.articleTag.postValue(article)

            if (twoPane) {
                addFragmentToListActivity(article)
            } else {
                addFragmentToDetailActivity(article, v)
            }
        }
    }

    private fun addFragmentToListActivity(article: Article) {
        val fragment = TechArticleDetailFragment().newInstance(article)
        parentActivity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.article_detail_container, fragment) // replace existing article content
                .commit()
    }

    private fun addFragmentToDetailActivity(article: Article, v: View) {
        val intent = Intent(v.context, TechArticleDetailActivity::class.java).apply {
            putExtra(TechArticleDetailFragment.ARTICLE, article)
        }
        v.context.startActivity(intent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ArticleListContentBinding.inflate(LayoutInflater
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

    inner class ViewHolder(view: ArticleListContentBinding) : RecyclerView.ViewHolder(view.root) {
        val articleTitle: TextView = view.articleTitle
    } // inner custom ViewHolder class

}