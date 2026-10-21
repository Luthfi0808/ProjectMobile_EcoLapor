package com.example.tugaspemrogramanmobile_luthfi3tic

import android.content.Context
import android.widget.ListView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.hamcrest.Matchers.anything
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationListTest {
    private lateinit var scenario: ActivityScenario<MainActivity>
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before fun loginForTest() {
        context.getSharedPreferences("ecolapor_session", Context.MODE_PRIVATE).edit()
            .putBoolean("isLogin", true).putString("username", "ecolapor").commit()
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After fun closeActivity() {
        scenario.close()
        context.getSharedPreferences("ecolapor_session", Context.MODE_PRIVATE).edit()
            .putBoolean("isLogin", false).remove("username").commit()
    }

    @Test fun listsScrollAndCustomRowShowsSnackbar() {
        onView(withId(R.id.tvWelcome)).check(matches(withText("Halo, ecolapor")))
        onView(withId(R.id.nav_jelajah)).perform(click())
        onView(withId(R.id.listViewItems)).check(matches(isDisplayed()))
        scenario.onActivity {
            assertEquals(10, it.findViewById<ListView>(R.id.listViewItems).adapter.count)
        }
        onView(withId(R.id.fragmentContainer)).perform(swipeUp())
        onData(anything()).inAdapterView(withId(R.id.listViewItems)).atPosition(9)
            .check(matches(hasDescendant(withText("Sampah di sungai"))))
        onData(anything()).inAdapterView(withId(R.id.listViewItems)).atPosition(0)
            .check(matches(hasDescendant(withText("Tumpukan sampah di jalan atau fasilitas umum."))))

        onView(withId(R.id.nav_aktivitas)).perform(click())
        scenario.onActivity {
            assertEquals(10, it.findViewById<ListView>(R.id.listLaporanItems).adapter.count)
        }
        onView(withId(R.id.fragmentContainer)).perform(swipeUp())
        onData(anything()).inAdapterView(withId(R.id.listLaporanItems)).atPosition(9).perform(click())
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("Laporan: Sampah di sungai")))
    }

    @Test fun selectedTabSurvivesRecreationWithoutBackStack() {
        onView(withId(R.id.nav_jelajah)).perform(click())
        onView(withId(R.id.nav_aktivitas)).perform(click())
        scenario.recreate()
        onView(withId(R.id.listLaporanItems)).check(matches(isDisplayed()))
        scenario.onActivity {
            assertEquals(R.id.nav_aktivitas, it.findViewById<BottomNavigationView>(R.id.bottomNavView).selectedItemId)
            assertEquals(0, it.supportFragmentManager.backStackEntryCount)
        }
        onView(withId(R.id.nav_home)).perform(click())
        onView(withId(R.id.tvWelcome)).check(matches(withText("Halo, ecolapor")))
        onView(withId(R.id.btnDetail)).check(matches(isDisplayed()))
    }
}
