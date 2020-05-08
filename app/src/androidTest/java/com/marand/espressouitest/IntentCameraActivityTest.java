package com.marand.espressouitest;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import com.marand.espressouitest.ui.activity.IntentCameraActivity;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4ClassRunner.class)
public class IntentCameraActivityTest {

    @Rule
    public IntentsTestRule intentsTestRule = new IntentsTestRule(IntentCameraActivity.class);

    @Test
    public void test_cameraIntent() {
        Instrumentation.ActivityResult activityResult = createImageCaptureActivityResultStub();
        Matcher<Intent> expectedIntent = hasAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intending(expectedIntent).respondWith(activityResult);

        onView(withId(R.id.image)).check(matches(not(hasImage())));
        onView(withId(R.id.button_launch_camera)).perform(click());
        intended(expectedIntent);
        onView(withId(R.id.image)).check(matches(hasImage()));
    }

    private Instrumentation.ActivityResult createImageCaptureActivityResultStub() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(IntentCameraActivity.KEY_IMAGE_DATA, BitmapFactory.decodeResource(
                intentsTestRule.getActivity().getResources(), R.drawable.ic_launcher_background));

        Intent resultData = new Intent();
        resultData.putExtras(bundle);

        return new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
    }

// -------------------------------------------------------------------------------------------------

    private BoundedMatcher<View, ImageView> hasImage() {
        return new BoundedMatcher<View, ImageView>(ImageView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has image");
            }

            @Override
            public boolean matchesSafely(ImageView imageView) {
                return imageView.getDrawable() != null;
            }
        };
    }
}