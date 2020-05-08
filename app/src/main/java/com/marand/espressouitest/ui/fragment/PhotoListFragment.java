package com.marand.espressouitest.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.marand.espressouitest.R;
import com.marand.espressouitest.ui.UILoadingListener;
import com.marand.espressouitest.ui.adapter.PhotosAdapter;
import com.marand.espressouitest.util.Constants;
import com.marand.espressouitest.util.EspressoIdlingResource;
import com.marand.espressouitest.viewmodel.MyViewModel;

public class PhotoListFragment extends Fragment implements PhotosAdapter.OnPhotoClickListener {
    private static final String TAG = PhotoListFragment.class.getSimpleName();
    private UILoadingListener mUiLoadingListener;
    private RecyclerView mPhoto_recycler_view;
    private PhotosAdapter mPhotos_adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photo_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initPhotosRecyclerView();
        getData();
    }

// -------------------------------------------------------------------------------------------------

    private void initView(View view) {
        mPhoto_recycler_view = view.findViewById(R.id.photo_recycler_view);
    }
    private void initPhotosRecyclerView() {
        mPhoto_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPhotos_adapter = new PhotosAdapter();
        mPhoto_recycler_view.setAdapter(mPhotos_adapter);
        mPhotos_adapter.setOnPhotoClickListener(this);
    }
    private void getData() {
        EspressoIdlingResource.increment();
        MyViewModel myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        myViewModel.getPhotos().observe(this, listResource -> {
            if (listResource != null) {
                switch (listResource.status) {
                    case SUCCESS: {
                        mUiLoadingListener.loading(false);
                        mPhotos_adapter.setPhotos(listResource.data);
                        EspressoIdlingResource.decrement();
                        break;
                    }
                    case ERROR: {
                        Toast.makeText(getActivity(), getString(R.string.error), Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case LOADING: {
                        mUiLoadingListener.loading(true);
                        break;
                    }
                }
            }
        });
    }

// -------------------------------------------------------------------------------------------------

    @Override
    public void onPhotoClicked(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.PHOTO_TITLE, title);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, PhotoDetailFragment.class, bundle)
                .addToBackStack("PhotoDetailFragment")
                .commit();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mUiLoadingListener = (UILoadingListener) context;
        } catch (Exception e) {
            Log.e(TAG, "onAttach: "+e.getMessage());
        }
    }
}
