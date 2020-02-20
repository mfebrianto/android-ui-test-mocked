package com.example.mock

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.core.StringContains
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Rule @JvmField var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun callAPI() {
        onView(ViewMatchers.withId(R.id.button)).perform(ViewActions.click())

        //todo: sleep can avoided with many different way like timeout
        Thread.sleep(5000)

        onView(ViewMatchers.withId(R.id.hello_world))
            .check(ViewAssertions.matches(ViewMatchers.withText(StringContains.containsString("Geralt of Rivia"))))

    }
}
