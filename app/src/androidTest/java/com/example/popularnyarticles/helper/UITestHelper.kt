package com.example.popularnyarticles.helper

import android.content.Context
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import com.example.popularnyarticles.R
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Description
import org.hamcrest.Matcher
import java.io.IOException
import java.nio.charset.StandardCharsets

class UITestHelper {

    companion object{
        fun enqueueRequestWithJsonAndStatusCode(
            server: MockWebServer?, statusCode: Int,
            fileName: String?, context: Context
        ) {
            val mockResponse = MockResponse()
                .setResponseCode(statusCode)
            if (fileName != null) {
                mockResponse.setBody(loadJSONFromAsset(context, fileName)!!)
            }
            server?.enqueue(mockResponse)
        }

         fun loadJSONFromAsset(context: Context, fileName: String): String? {
            val json: String
            json = try {
                val `is` = context.resources.assets.open(fileName)
                val size = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                String(buffer, StandardCharsets.UTF_8)
            } catch (ex: IOException) {
                ex.printStackTrace()
                return null
            }
            return json
        }


        fun atPosition(position: Int, @NonNull itemMatcher: Matcher<View?>): Matcher<View?>? {
            checkNotNull(itemMatcher)
            return object : BoundedMatcher<View?, RecyclerView?>(RecyclerView::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("has item at position $position: ")
                    itemMatcher.describeTo(description)
                }

                override fun matchesSafely(view: RecyclerView?): Boolean {
                    val viewHolder: RecyclerView.ViewHolder =
                        view?.findViewHolderForAdapterPosition(position)
                            ?: // has no item on such position
                            return false
                    return itemMatcher.matches(viewHolder.itemView.findViewById(R.id.articleDate))
                }
            }
        }
    }



}