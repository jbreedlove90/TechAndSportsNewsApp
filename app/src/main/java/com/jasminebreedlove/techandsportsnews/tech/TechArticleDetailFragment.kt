package com.jasminebreedlove.techandsportsnews.tech

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jasminebreedlove.techandsportsnews.R
import com.jasminebreedlove.techandsportsnews.dao.Article
import kotlinx.android.synthetic.main.activity_article_detail.*
import kotlinx.android.synthetic.main.article_detail.view.*

class TechArticleDetailFragment : Fragment() {

    private var article: Article = Article()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARTICLE)) {
                article = it.getSerializable(ARTICLE) as Article
            }
        }
    }

    fun newInstance(article: Article) : TechArticleDetailFragment {
        return TechArticleDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable(TechArticleDetailFragment.ARTICLE, article)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.article_detail, container, false)

        // todo: add category to view and xml layout file
        // load article details in respective views
        article.let {
            activity?.toolbar_layout?.title = it.articleTitle
            rootView.article_pub_date.text = it.pubDate
            rootView.article_description.text = it.description
        }
        return rootView
    }

    companion object {
        const val ARTICLE = "tech_article"
    }
}
