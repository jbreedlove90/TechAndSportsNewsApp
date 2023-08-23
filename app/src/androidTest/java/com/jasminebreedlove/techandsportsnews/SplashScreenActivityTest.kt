package com.jasminebreedlove.techandsportsnews

import androidx.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import android.test.suitebuilder.annotation.LargeTest
import android.view.View
import android.view.ViewGroup

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf

@RunWith(AndroidJUnit4::class)
class SplashScreenActivityTest {

    @get:Rule
    var mActivityTestRule = ActivityTestRule(SplashScreenActivity::class.java)

    @Test
    fun splashScreenActivityTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        val appCompatButton = onView(
                allOf(withId(R.id.select_tech_btn), withText("Technology"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()))
        appCompatButton.perform(click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        /* actions after tech button is pressed on category chooser screen */

        // verify toolbar displayed
        val viewGroup = onView(
                allOf(withId(R.id.toolbar),
                        childAtPosition(
                                allOf(withId(R.id.app_bar),
                                        childAtPosition(
                                                IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                                                0)),
                                0),
                        isDisplayed()))
        viewGroup.check(matches(isDisplayed()))

        // verify recyclerview displayed after tech button is pressed
        onView(withId(R.id.article_list)).check(matches(isDisplayed()))

    }

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
