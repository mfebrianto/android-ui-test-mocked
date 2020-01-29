package com.example.mock

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.core.StringContains
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private val mockWebServer = MockWebServer()

    @Rule @JvmField var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    @Throws(IOException::class, InterruptedException::class)
    fun setup() {
        mockWebServer.enqueue(MockResponse().setBody("Wed, 29 Jan 2020 23:19:51 +1000"))
        mockWebServer.start(8080)
    }

    @After
    @Throws(IOException::class)
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun callAPI() {
        onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())

        //todo: sleep can avoided with many different way like timeout
        Thread.sleep(5000)

        onView(ViewMatchers.withId(R.id.hello_world))
            .check(ViewAssertions.matches(ViewMatchers.withText(StringContains.containsString("+1000"))))

    }
}
