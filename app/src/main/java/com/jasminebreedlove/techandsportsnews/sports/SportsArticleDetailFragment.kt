package com.jasminebreedlove.techandsportsnews.sports

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.jasminebreedlove.techandsportsnews.R
import com.jasminebreedlove.techandsportsnews.dao.Article
import com.jasminebreedlove.techandsportsnews.databinding.SportsarticleDetailBinding

class SportsArticleDetailFragment : Fragment() {

    private var article: Article = Article()
    private var _binding: SportsarticleDetailBinding? = null
    private val binding get() = _binding!!

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
        _binding = SportsarticleDetailBinding.inflate(inflater, container, false)

        // todo: add category to view and xml layout file
        // load article details in respective views
        article.let {
            (activity as SportsArticleDetailActivity)?.findViewById<CollapsingToolbarLayout>(R.id.sports_toolbar_layout)?.title = it.articleTitle
            binding.sportsArticlePubDate.text = it.pubDate
            binding.sportsArticleDescription.text = it.description
            binding.sportsArticleLink.apply {
                val spanStringBuilder = SpannableStringBuilder().apply {
                    append(resources.getString(R.string.full_story_text))
                    setSpan(URLSpan(it.link), 0, this.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                }
                setText(spanStringBuilder, TextView.BufferType.SPANNABLE)
                movementMethod = LinkMovementMethod.getInstance()
            }
        }

        return binding.root
    }

    companion object {
        const val ARTICLE = "sports_article"
    }
}
