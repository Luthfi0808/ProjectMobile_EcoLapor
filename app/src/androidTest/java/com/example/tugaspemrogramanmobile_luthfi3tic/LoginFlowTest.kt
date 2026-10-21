package com.example.tugaspemrogramanmobile_luthfi3tic

import android.app.Activity
import android.content.Intent
import android.os.SystemClock
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFlowTest {
    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val context = instrumentation.targetContext
    private val preferences = context.getSharedPreferences("ecolapor_session", android.content.Context.MODE_PRIVATE)

    @Before fun resetSession() {
        preferences.edit().putBoolean("isLogin", false).apply()
    }

    private fun openSplash() {
        var previousActivities: Set<Activity> = emptySet()
        instrumentation.runOnMainSync {
            previousActivities = ActivityLifecycleMonitorRegistry.getInstance()
                .getActivitiesInStage(Stage.RESUMED).toSet()
        }
        val expected = if (preferences.getBoolean("isLogin", false)) MainActivity::class.java else LoginActivity::class.java
        context.startActivity(Intent(context, SplashActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
        // Tunggu activity tujuan BARU siap menerima sentuhan, bukan root task lama.
        val deadline = SystemClock.uptimeMillis() + 8000
        while (SystemClock.uptimeMillis() < deadline) {
            var ready = false
            instrumentation.runOnMainSync {
                ready = ActivityLifecycleMonitorRegistry.getInstance()
                    .getActivitiesInStage(Stage.RESUMED).any {
                        expected.isInstance(it) && it !in previousActivities &&
                            it.window.decorView.hasWindowFocus()
                    }
            }
            if (ready) {
                instrumentation.waitForIdleSync()
                return
            }
            SystemClock.sleep(50)
        }
        throw AssertionError("Activity tujuan splash belum siap")
    }

    @Test fun splashWithoutSessionOpensLoginAndRejectsInvalidInput() {
        openSplash()
        onView(withId(R.id.button)).perform(scrollTo(), click())
        onView(withId(R.id.username)).check(matches(hasErrorText(context.getString(R.string.username_required))))
        assertFalse(preferences.getBoolean("isLogin", false))

        onView(withId(R.id.username)).perform(replaceText("ecolapor"), closeSoftKeyboard())
        onView(withId(R.id.button)).perform(scrollTo(), click())
        onView(withId(R.id.password)).check(matches(hasErrorText(context.getString(R.string.password_required))))

        onView(withId(R.id.password)).perform(replaceText("wrong"), closeSoftKeyboard())
        onView(withId(R.id.button)).perform(scrollTo(), click())
        onView(withId(R.id.password)).check(matches(hasErrorText(context.getString(R.string.invalid_credentials))))
        assertFalse(preferences.getBoolean("isLogin", false))
    }

    @Test fun successfulLoginPersistsAcrossSplashAndLogoutClearsSession() {
        openSplash()
        onView(withId(R.id.username)).perform(replaceText("ecolapor"), closeSoftKeyboard())
        onView(withId(R.id.password)).perform(replaceText("eco123"), closeSoftKeyboard())
        onView(withId(R.id.button)).perform(scrollTo(), click())
        onView(withId(R.id.btnDetail)).check(matches(isDisplayed()))
        assertTrue(preferences.getBoolean("isLogin", false))

        // Activity/task baru membaca kembali preferensi yang telah disimpan.
        openSplash()
        onView(withId(R.id.btnDetail)).perform(scrollTo(), click())
        onView(withId(R.id.btnKembali)).perform(scrollTo(), click())
        onView(withId(R.id.btnLogout)).perform(scrollTo(), click())
        onView(withText(R.string.no)).perform(click())
        onView(withId(R.id.btnDetail)).check(matches(isDisplayed()))
        assertTrue(preferences.getBoolean("isLogin", false))

        onView(withId(R.id.btnLogout)).perform(scrollTo(), click())
        onView(withText(R.string.yes)).perform(click())
        onView(withId(R.id.username)).check(matches(isDisplayed()))
        assertFalse(preferences.getBoolean("isLogin", false))

        openSplash()
        onView(withId(R.id.username)).check(matches(isDisplayed()))
    }
}
