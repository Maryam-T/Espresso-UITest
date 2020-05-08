package com.marand.espressouitest;

import androidx.test.core.app.ActivityScenario;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import com.marand.espressouitest.ui.activity.DialogActivity;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.marand.espressouitest.ui.activity.DialogActivity.buildToastMessage;

@RunWith(AndroidJUnit4ClassRunner.class)
public class DialogActivityTest {

    private static final String NAME = "Maryam-T";

    @Test
    public void testShowDialog_captureNameInput() {
        ActivityScenario activityScenario = ActivityScenario.launch(DialogActivity.class);

        onView(withId(R.id.button_launch_dialog)).perform(click());
        onView(withText(R.string.text_enter_name)).check(matches(isDisplayed()));
        onView(withText(R.string.text_ok)).perform(click());

        onView(withText(R.string.text_enter_name)).check(matches(isDisplayed()));

        onView(withId(R.id.md_input_message)).perform(typeText(NAME));
        onView(withText(R.string.text_ok)).perform(click());

        onView(withText(R.string.text_enter_name)).check(doesNotExist());
        onView(withId(R.id.text_name)).check(matches(withText(NAME)));

        onView(withText(buildToastMessage(NAME))).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

}