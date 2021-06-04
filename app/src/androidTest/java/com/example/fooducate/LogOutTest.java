package com.example.fooducate;

import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LogOutTest {
    @Rule
    public ActivityTestRule<MainActivity> mLogOutTestRule =
            new ActivityTestRule<>(MainActivity.class);
    @Test
    public void searchBarcode() {
        mLogOutTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
        onView(withId(R.id.bottomNavigationView)).perform(ViewActions.click());
        onView(withId(R.id.profile)).perform(ViewActions.click());
        onView(withId(R.id.sign_out)).perform(ViewActions.click());
    }
}
