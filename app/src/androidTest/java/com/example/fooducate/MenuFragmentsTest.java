package com.example.fooducate;

import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;


import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import com.example.fooducate.activities.MainActivity;

public class MenuFragmentsTest {

    @Rule
    public ActivityTestRule<MainActivity> mSearchBarcodeTestRule =
            new ActivityTestRule<>(MainActivity.class);
    @Test
    public void searchBarcode() {
        String barcode = "6009802351031";
        mSearchBarcodeTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
        onView(withId(R.id.search_bar)).perform(ViewActions.click())
                .perform(ViewActions.typeText(barcode));
        onView(withId(R.id.sbutton)).perform(ViewActions.click());

    }
}
