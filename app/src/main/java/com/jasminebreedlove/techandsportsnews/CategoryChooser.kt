package com.jasminebreedlove.techandsportsnews

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jasminebreedlove.techandsportsnews.sports.SportsArticleListActivity
import com.jasminebreedlove.techandsportsnews.tech.TechArticleListActivity
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
                val techIntent = Intent(this, TechArticleListActivity::class.java).apply {
                    putExtra(TECH_CATEGORY_SELECTOR, "retrieve_tech")
                }
                startActivityForResult(techIntent,0)
            //    startActivity(techIntent)
            }

            R.id.select_sports_btn -> {
                info("sports :::")
                val sportsIntent = Intent(this, SportsArticleListActivity::class.java).apply {
                    putExtra(SPORTS_CATEGORY_SELECTOR, "retrieve_sports")
                }
                startActivityForResult(sportsIntent,0)
            //    startActivity(sportsIntent)
            }

        }
    }

    companion object {
        const val TECH_CATEGORY_SELECTOR = "retrieve_tech"
        const val SPORTS_CATEGORY_SELECTOR = "retrieve_sports"
    }

}
