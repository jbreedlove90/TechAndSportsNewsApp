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
            if (it.containsKey(ARTICLE)) {
                article = it.getSerializable(ARTICLE) as Article
            }
        }
    }

    fun newInstance(article: Article) : SportsArticleDetailFragment {
        return SportsArticleDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable(SportsArticleDetailFragment.ARTICLE, article)
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
        const val ARTICLE = "sports_article"
    }
}
