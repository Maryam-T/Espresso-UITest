package com.marand.espressouitest.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import com.marand.espressouitest.R;
import com.marand.espressouitest.factory.PhotoFragmentFactory;
import com.marand.espressouitest.ui.fragment.PhotoListFragment;
import com.marand.espressouitest.ui.UILoadingListener;

public class MainActivity extends AppCompatActivity implements UILoadingListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ProgressBar mProgress_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportFragmentManager().setFragmentFactory(new PhotoFragmentFactory());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setupFragment();
    }

// -------------------------------------------------------------------------------------------------

    private void initView() {
        mProgress_bar = findViewById(R.id.progress_bar);
    }

    private void setupFragment() {
        if (getSupportFragmentManager().getFragments().size() == 0) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, PhotoListFragment.class, null)
                    .commit();
        }
    }

// -------------------------------------------------------------------------------------------------

    @Override
    public void loading(boolean isLoading) {
        if (isLoading) {
            mProgress_bar.setVisibility(View.VISIBLE);
        } else {
            mProgress_bar.setVisibility(View.INVISIBLE);
        }
    }
}
