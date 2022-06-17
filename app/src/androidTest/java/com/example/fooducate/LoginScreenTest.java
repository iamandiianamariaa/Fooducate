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

import com.example.fooducate.activities.LoginActivity;

@RunWith(AndroidJUnit4.class)
public class LoginScreenTest {

    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivityTestRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void makeLoginAction() {
        final String emailTest = "test@test.com";
        final String passTest = "test12";

        onView(withId(R.id.editTextTextEmailAddress))
                .perform(ViewActions.click())
                .perform(ViewActions.typeText(emailTest));
        closeSoftKeyboard();

        onView(withId(R.id.editTextTextPassword))
                .perform(ViewActions.click())
                .perform(ViewActions.typeText(passTest));

        closeSoftKeyboard();

        onView(withId(R.id.signin_button)).perform(ViewActions.click());
    }
}
