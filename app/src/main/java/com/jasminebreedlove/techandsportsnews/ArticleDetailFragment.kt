package com.jasminebreedlove.techandsportsnews

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jasminebreedlove.techandsportsnews.dao.Article
import kotlinx.android.synthetic.main.activity_article_detail.*
import kotlinx.android.synthetic.main.article_detail.view.*

class ArticleDetailFragment : Fragment() {

    private var article: Article = Article()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARTICLE_TITLE)) {
                article.articleTitle = it.getString(ARTICLE_TITLE)
                article.pubDate = it.getString(ArticleDetailFragment.ARTICLE_PUB)
                article.description = it.getString(ArticleDetailFragment.ARTICLE_DESCRIPTION)
                // TODO: fix toolbar title issue on orientation change
                activity?.toolbar_layout?.title = article.articleTitle
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.article_detail, container, false)

        // load article details in respective views
        article.let {
            rootView.article_pub_date.text = it.pubDate
            rootView.article_description.text = it.description
        }

        return rootView
    }

    companion object {
        const val ARTICLE_TITLE = "item_id"
        const val ARTICLE_PUB = "article_pub_date"
        const val ARTICLE_DESCRIPTION = "article_description"
    }
}
