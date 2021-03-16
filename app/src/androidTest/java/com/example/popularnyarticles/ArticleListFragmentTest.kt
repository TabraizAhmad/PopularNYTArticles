package com.example.popularnyarticles

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import com.example.popularnyarticles.helper.FileReader
import com.example.popularnyarticles.helper.UITestHelper
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@HiltAndroidTest
@RunWith(AndroidJUnit4ClassRunner::class)
class ArticleListFragmentTest{

     var okHttpClient: OkHttpClient? = null
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)


    private val ARTICLE_LIST_JSON = "articlesSampleList.json"
    val LIST_ITEM_IN_TEST = 4


    private var server: MockWebServer? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        okHttpClient = getOkHttpClientObject()
        server = MockWebServer()
        server?.start(8607)
        OkHttp3IdlingResource.create(
            "okhttp",
                    okHttpClient!!
        )
    }
    private fun getOkHttpClientObject(): OkHttpClient? {
        return if (okHttpClient == null) {
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)
                .connectTimeout(3, TimeUnit.SECONDS)
                .build()
            this.okHttpClient = okHttpClient
            okHttpClient
        } else {
            okHttpClient
        }
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun mainActivityTest() {
        UITestHelper.enqueueRequestWithJsonAndStatusCode(
            server,
            200,
            ARTICLE_LIST_JSON,
            getInstrumentation().context
        )
        activityRule.launchActivity(null)


        onView(withId(R.id.progressBar))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.popularListRV))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.errorTextView))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))

        onView(withId(R.id.popularListRV)).check(
            matches(
                UITestHelper.atPosition(
                    0,
                    withText("2021-03-16")
                )
            )
        )

    }
    @Test
    fun testSuccessfulResponse() {
        server?.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(FileReader.readStringFromFile(ARTICLE_LIST_JSON))
            }
        }
        activityRule.launchActivity(null)
        onView(withId(R.id.progressBar))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.popularListRV))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.errorTextView))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))

    }

    @Test
    fun test_isProgressBarVisible_onAppLaunch() {
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
    }

    @After
    fun teardown() {
        server?.shutdown()
    }

}









