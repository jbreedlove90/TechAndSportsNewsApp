package com.jasminebreedlove.techandsportsnews.tech

import android.content.Intent
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.jasminebreedlove.techandsportsnews.R
import com.jasminebreedlove.techandsportsnews.dao.Article
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.v7.widget.Toolbar
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.*
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.view.ViewGroup
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf

// todo: figure out how to log system back button key press
@RunWith(AndroidJUnit4::class)
class TechArticleDetailActivityTest {

    val ARTICLE = Article("Article Test", "https://abcnews.go.com/Technology/", "5/17/2018",
            "Article Test Description", "Category")

    @get:Rule // need get bc kotlin property not recognized by junit
    val mActivityTestRule =
            ActivityTestRule(TechArticleDetailActivity::class.java, true, false)

    @Before
    fun setUp() {
        val detailFragment = TechArticleDetailFragment().newInstance(ARTICLE)
        // Lazily start the Activity from the ActivityTestRule this time to inject the start Intent
        val startIntent = Intent().apply {
            putExtra(TechArticleDetailFragment.ARTICLE, ARTICLE)
        }
        mActivityTestRule.launchActivity(startIntent)
        mActivityTestRule.activity.supportFragmentManager.beginTransaction().add(detailFragment, TechArticleDetailFragment.ARTICLE).commit()
    }

    @Test
    fun articleDetailIsDisplayed_Ui() {
        // check that article title, description, pub date and link is displayed
        // verify toolbar displayed
        val viewGroup = onView(
                Matchers.allOf(withId(R.id.detail_toolbar), childAtPosition(Matchers.allOf(withId(R.id.toolbar_layout),
                                        childAtPosition(
                                                IsInstanceOf.instanceOf(ViewGroup::class.java),
                                                0)),
                                0),
                        isDisplayed()))
        viewGroup.check(matches(isDisplayed()))

        onView(withId(R.id.article_pub_date)).check(matches(withText(ARTICLE.pubDate)))
        onView(withId(R.id.article_description)).check(matches(withText(ARTICLE.description)))
        onView(withId(R.id.article_link)).check(matches(withText("Full story")))
    //            .perform(openLinkWithUri(ARTICLE.link))
    //    mActivityTestRule.finishActivity()
    }

    @Test
    fun clickOnFab_Ui() {
        // Click on the share fab
        onView(withId(R.id.fab)).perform(click())
    }

    /*fun withToolbarTitle(title: CharSequence): Matcher<View> {
        return checkToolbarTitle(`is`(title))
    }

    fun matchToolbarTitle(
            title: CharSequence): ViewInteraction {
        return onView(isAssignableFrom(Toolbar::class.java))
                .check(matches(checkToolbarTitle(`is`(title))))
    }

    fun checkToolbarTitle(textMatcher: Matcher<CharSequence>): Matcher<View> {
        return object : BoundedMatcher<View, Toolbar>(Toolbar::class.java) {
            public override fun matchesSafely(toolbar: Toolbar): Boolean {
                return textMatcher.matches(toolbar.title)
            }

            override fun describeTo(description: Description?) {
                description?.appendText("with toolbar title: ")
                textMatcher.describeTo(description)
            }
        }
    } // withToolbarTitle()*/

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }

}