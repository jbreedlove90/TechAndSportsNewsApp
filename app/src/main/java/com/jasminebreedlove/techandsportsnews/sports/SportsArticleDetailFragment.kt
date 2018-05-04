package com.jasminebreedlove.techandsportsnews.sports

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jasminebreedlove.techandsportsnews.R
import com.jasminebreedlove.techandsportsnews.dao.Article
import kotlinx.android.synthetic.main.activity_sportsarticle_detail.*
import kotlinx.android.synthetic.main.sportsarticle_detail.view.*


class SportsArticleDetailFragment : Fragment() {

    private var article: Article = Article()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARTICLE_TITLE)) {
                article.articleTitle = it.getString(ARTICLE_TITLE)
                article.link = it.getString(ARTICLE_LINK)
                article.pubDate = it.getString(ARTICLE_PUB)
                article.description = it.getString(ARTICLE_DESCRIPTION)
                article.category = it.getString(ARTICLE_CATEGORY)

            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.sportsarticle_detail, container, false)

        // todo: add category to view and xml layout file
        // load article details in respective views
        article.let {
            activity?.sports_toolbar_layout?.title = it.articleTitle
            rootView.sports_article_pub_date.text = it.pubDate
            rootView.sports_article_description.text = it.description
        }

        return rootView
    }

    companion object {
        const val ARTICLE_TITLE = "article_title_sports"
        const val ARTICLE_LINK = "article_link_sports"
        const val ARTICLE_PUB = "article_pub_date_sports"
        const val ARTICLE_DESCRIPTION = "article_description_sports"
        const val ARTICLE_CATEGORY = "article_category_sports"
    }
}