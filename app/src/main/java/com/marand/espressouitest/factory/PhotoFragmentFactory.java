package com.marand.espressouitest.factory;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import com.marand.espressouitest.ui.fragment.PhotoDetailFragment;
import com.marand.espressouitest.ui.fragment.PhotoListFragment;

public class PhotoFragmentFactory extends FragmentFactory {
    private static final String TAG = PhotoFragmentFactory.class.getSimpleName();

    @NonNull
    @Override
    public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
        if (PhotoListFragment.class.getCanonicalName().equals(className)) {
            return new PhotoListFragment();
        } else if (PhotoDetailFragment.class.getCanonicalName().equals(className)) {
            return new PhotoDetailFragment();
        }  else {
            return super.instantiate(classLoader, className);
        }
    }
}
