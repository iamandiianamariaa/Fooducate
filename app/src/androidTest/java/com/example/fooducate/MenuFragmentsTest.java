package com.example.fooducate;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;


import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

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
