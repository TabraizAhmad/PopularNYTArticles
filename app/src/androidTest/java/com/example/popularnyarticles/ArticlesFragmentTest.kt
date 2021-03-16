package com.example.popularnyarticles

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.example.popularnyarticles.helper.UITestHelper
import com.example.popularnyarticles.ui.article.articlelist.adapter.ArticleRVAdapter
import com.example.popularnyarticles.utils.EspressoIdlingResource
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ArticlesFragmentTest{



    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private val ARTICLE_LIST_JSON = "articlesSampleList.json"

    val LIST_ITEM_IN_TEST = 0

    private var server: MockWebServer? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        server = MockWebServer()
        server?.start(8607)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)

    }

    @Test
    @Throws(java.lang.Exception::class)
    fun testSuccessfulListResponse() {
        UITestHelper.enqueueRequestWithJsonAndStatusCode(
                server,
                200,
                ARTICLE_LIST_JSON,
                getInstrumentation().context
        )


        onView(withId(R.id.progressBar))
                .check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.popularListRV))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.errorTextView))
                .check(matches(withEffectiveVisibility(Visibility.GONE)))
        //match ui with the mocked response
        onView(withId(R.id.popularListRV)).check(
                matches(
                        UITestHelper.atPosition(
                                0,
                                withText("2021-03-15")
                        )
                )
        )

    }

    @Test
    @Throws(java.lang.Exception::class)
    fun testFailedResponse() {
        UITestHelper.enqueueRequestWithJsonAndStatusCode(
                server,
                404,
                ARTICLE_LIST_JSON,
                getInstrumentation().context
        )

        onView(withId(R.id.progressBar))
                .check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.popularListRV))
                .check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.errorTextView))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

    }

    @Test
    fun test_selectListItem_isDetailFragmentVisible() {

        UITestHelper.enqueueRequestWithJsonAndStatusCode(
                server,
                200,
                ARTICLE_LIST_JSON,
                getInstrumentation().context
        )

        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.popularListRV))
                .perform(actionOnItemAtPosition<ArticleRVAdapter.ArticleViewHolder>(LIST_ITEM_IN_TEST, ViewActions.click()))

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.updatedDate)).check(matches(withText("Updated date :2021-03-09 17:50:43")))
    }

    @Test
    fun test_backNavigation_toListFragment() {

        UITestHelper.enqueueRequestWithJsonAndStatusCode(
                server,
                200,
                ARTICLE_LIST_JSON,
                getInstrumentation().context
        )

        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.popularListRV))
                .perform(actionOnItemAtPosition<ArticleRVAdapter.ArticleViewHolder>(LIST_ITEM_IN_TEST, ViewActions.click()))

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.updatedDate)).check(matches(withText("Updated date :2021-03-09 17:50:43")))

        Espresso.pressBack()

        // Confirm ListFragment in view
        onView(withId(R.id.popularListRV)).check(matches(isDisplayed()))
    }

    @After
    fun teardown() {
        server?.shutdown()
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)

    }

}









