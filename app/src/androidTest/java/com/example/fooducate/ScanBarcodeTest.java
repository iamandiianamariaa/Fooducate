package com.example.fooducate;

import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class ScanBarcodeTest {
    @Rule
    public ActivityTestRule<MainActivity> mLoginActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);
    @Test
    public void searchBarcode() {
        onView(withId(R.id.fab)).perform(ViewActions.click());

    }
}
