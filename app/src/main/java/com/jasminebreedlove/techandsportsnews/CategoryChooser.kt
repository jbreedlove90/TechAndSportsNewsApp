package com.jasminebreedlove.techandsportsnews

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.jasminebreedlove.techandsportsnews.sports.SportsArticleListActivity
import com.jasminebreedlove.techandsportsnews.tech.TechArticleListActivity
//import org.jetbrains.anko.AnkoLogger
//import org.jetbrains.anko.info

class CategoryChooser : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_chooser)
    }

    fun navigateToCategory(view: View) {
        when(view.id) {
            R.id.select_tech_btn -> {
                //info("tech ::: ")
                val techIntent = Intent(this, TechArticleListActivity::class.java).apply {
                    putExtra(TECH_CATEGORY_SELECTOR, "retrieve_tech")
                }
                startActivityForResult(techIntent,0)
            //    startActivity(techIntent)
            }

            R.id.select_sports_btn -> {
                //info("sports :::")
                val sportsIntent = Intent(this, SportsArticleListActivity::class.java).apply {
                    putExtra(SPORTS_CATEGORY_SELECTOR, "retrieve_sports")
                }
                startActivityForResult(sportsIntent,0)
            //    startActivity(sportsIntent)
            }
            R.id.tv_website_link -> {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse("http://abcnews.go.com/")
                startActivity(i)
            }

        }
    }

    companion object {
        const val TECH_CATEGORY_SELECTOR = "retrieve_tech"
        const val SPORTS_CATEGORY_SELECTOR = "retrieve_sports"
    }

}
