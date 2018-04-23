package com.jasminebreedlove.techandsportsnews

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class CategoryChooser : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_chooser)
    }

    fun navigateToCategory(view: View) {
        when(view.id) {
            R.id.select_tech_btn -> {
                info("tech ::: ")
                val techIntent = Intent(this, ArticleListActivity::class.java)
                techIntent.putExtra(TECH_CATEGORY_SELECTOR, "retrieve_tech")
                startActivity(techIntent)
            }

            R.id.select_sports_btn -> {
                info("sports :::")
            //    startActivity(Intent(this, SportsArticleListActivity::class.java))
            }

        }
    }

    companion object {
        const val TECH_CATEGORY_SELECTOR = "retrieve_tech"
        const val SPORTS_CATEGORY_SELECTOR = "retrieve_sports"
    }

}
