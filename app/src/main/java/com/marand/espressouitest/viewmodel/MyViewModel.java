package com.marand.espressouitest.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.marand.espressouitest.model.Photo;
import com.marand.espressouitest.repository.MyRepository;
import com.marand.espressouitest.util.Resource;

import java.util.List;

public class MyViewModel extends ViewModel {
    private MyRepository myRepository;

    public MyViewModel() {
        myRepository = MyRepository.getInstance();
    }

    public LiveData<Resource<List<Photo>>> getPhotos() {
        return myRepository.makeReactiveQuery();
    }
}
