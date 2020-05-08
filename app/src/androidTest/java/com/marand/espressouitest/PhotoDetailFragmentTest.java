package com.marand.espressouitest;

import android.os.Bundle;
import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import com.marand.espressouitest.factory.PhotoFragmentFactory;
import com.marand.espressouitest.ui.activity.MainActivity;
import com.marand.espressouitest.ui.fragment.PhotoDetailFragment;
import com.marand.espressouitest.util.Constants;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4ClassRunner.class)
public class PhotoDetailFragmentTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testPhotoTitle_shouldBeDisplayed() {
        FragmentFactory fragmentFactory = new PhotoFragmentFactory();
        Bundle bundle = new Bundle();
        String title = "The photo title";
        bundle.putString(Constants.PHOTO_TITLE, title);
        FragmentScenario scenario = FragmentScenario.launchInContainer(
                PhotoDetailFragment.class,
                bundle,
                fragmentFactory);

        onView(withId(R.id.photo_title)).check(matches(isDisplayed()));

        onView(withId(R.id.photo_title)).check(matches(withText(title)));
    }
}