package com.marand.espressouitest.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.marand.espressouitest.R;
import com.marand.espressouitest.util.Constants;

public class PhotoDetailFragment extends Fragment {
    private static final String TAG = PhotoDetailFragment.class.getSimpleName();
    private static TextView mPhoto_title;
    private String mTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle = getArguments().getString(Constants.PHOTO_TITLE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        setPhotoDetail();
    }

// -------------------------------------------------------------------------------------------------

    private void initView(View view) {
        mPhoto_title = view.findViewById(R.id.photo_title);
    }

    private void setPhotoDetail() {
        mPhoto_title.setText(mTitle);
    }

    public static String getPhotoTile() {
        return mPhoto_title.getText().toString();
    }
}
