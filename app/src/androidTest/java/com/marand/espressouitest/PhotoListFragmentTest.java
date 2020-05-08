package com.marand.espressouitest;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import com.marand.espressouitest.ui.activity.MainActivity;
import com.marand.espressouitest.ui.fragment.PhotoDetailFragment;
import com.marand.espressouitest.util.EspressoIdlingResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4ClassRunner.class)
public class PhotoListFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    private static final int LIST_ITEM_IN_TEST = 1;

    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource);
    }

    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource);
    }

    @Test
    public void testPhotoList_shouldBeDisplayed() {
        onView(withId(R.id.photo_recycler_view)).check(matches(isDisplayed()));

        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())));
    }

    @Test
    public void testSelectListItem_shouldOpenPhotoDetailFragment() {
        onView(withId(R.id.photo_recycler_view))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(LIST_ITEM_IN_TEST, click()));

        onView(withId(R.id.photo_title)).check(matches(withText(PhotoDetailFragment.getPhotoTile())));
    }

    @Test
    public void testBackTo_photoListFragment() {
        onView(withId(R.id.photo_recycler_view))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(LIST_ITEM_IN_TEST, click()));

        onView(withId(R.id.photo_title)).check(matches(withText(PhotoDetailFragment.getPhotoTile())));

        pressBack();

        onView(withId(R.id.photo_recycler_view)).check(matches(isDisplayed()));
    }

    @Test
    public void testPhotoList_scroll() {
        RecyclerView recyclerView = activityTestRule.getActivity().findViewById(R.id.photo_recycler_view);
        int itemCount = recyclerView.getAdapter().getItemCount();

        onView(withId(R.id.photo_recycler_view)).perform(RecyclerViewActions.scrollToPosition(itemCount - 1));
    }
}