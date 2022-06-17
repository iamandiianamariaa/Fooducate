package com.example.fooducate;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import com.example.fooducate.activities.RegisterActivity;

@RunWith(AndroidJUnit4.class)
public class RegisterScreenTest {

        @Rule
        public ActivityTestRule<RegisterActivity> mRegisterActivityTestRule =
                new ActivityTestRule<>(RegisterActivity.class);

        @Test
        public void clickRegisterButton() throws Exception {
            String fakeEmail = "test@test.com";
            String fakePass = "test12";

            onView(withId(R.id.editTextTextEmailAddress)).perform(ViewActions.click())
                    .perform(ViewActions.typeText(fakeEmail));
            closeSoftKeyboard();

            onView(withId(R.id.editTextTextPassword)).perform(ViewActions.click())
                    .perform(ViewActions.typeText(fakePass));

            closeSoftKeyboard();

            onView(withId(R.id.register_button)).perform(ViewActions.click());
        }

    }
