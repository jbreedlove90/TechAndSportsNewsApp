package com.jasminebreedlove.techandsportsnews.tech

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jasminebreedlove.techandsportsnews.R
import com.jasminebreedlove.techandsportsnews.dao.Article
import com.jasminebreedlove.techandsportsnews.dao.Rss
import com.jasminebreedlove.techandsportsnews.utils.NewsServiceImpl

import kotlinx.android.synthetic.main.activity_article_list.*
import kotlinx.android.synthetic.main.article_list_content.view.*
import kotlinx.android.synthetic.main.article_list.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TechArticleListActivity : AppCompatActivity(), AnkoLogger {

    private var twoPane: Boolean = false
    var articles: ArrayList<Article> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_list)
        setSupportActionBar(toolbar)
        toolbar.title = title

        if (article_detail_container != null) {
            twoPane = true
        }

        getTechFeed()
    } // onCreate()


    private fun getTechFeed() {
        info("getting tech news from abc rss feeds:::\n")
        NewsServiceImpl().setupRetrofit().getRssFeed("technologyheadlines").enqueue(object : Callback<Rss> {
            override fun onResponse(call: Call<Rss>?, response: Response<Rss>?) {
                response?.body()?.channel?.articleList?.forEach { articles.add(it) }

                response?.body()?.channel?.articleList?.forEach { info("Tech article info:::: title: " +
                        "${it.articleTitle}\ndescription: ${it.description}\npub date: ${it.pubDate}\ncategory: ${it.category}") }

                article_list.adapter = ArticleRecyclerViewAdapter(this@TechArticleListActivity, articles, twoPane)
            }

            override fun onFailure(call: Call<Rss>?, t: Throwable?) {
                info("Error in processing request:: ${t?.cause}")
            }
        })
    } // getTechFeed()

    // todo: add article link to view
    class ArticleRecyclerViewAdapter(private val parentActivity: TechArticleListActivity,
                                     private val articles: ArrayList<Article>,
                                     private val twoPane: Boolean) :
            RecyclerView.Adapter<ArticleRecyclerViewAdapter.ViewHolder>() {

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

    } // custom RecyclerAdapter class
}
