package com.example.bakingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.bakingapp.UI.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);




    @Test
    public void mainActivityTest() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.main_recycler_view)).perform(scrollToPosition(0)).check(matches(isDisplayed()));
        onView(withId(R.id.main_recycler_view)).perform(actionOnItemAtPosition(2, click()));
        onView(withId(R.id.ingredient_recycler_view)).perform(scrollToPosition(2)).check(matches(isDisplayed()));

        onView(withId(R.id.add_to_widget)).perform(click()).check(matches(isDisplayed()));

        onView(withId(R.id.steps_recycler_view)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.videoView)).check(matches(isDisplayed()));

        onView(withId(R.id.next)).perform(click()).check(matches(isDisplayed()));
    }

}
