package com.marand.espressouitest.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import com.marand.espressouitest.model.Photo;
import com.marand.espressouitest.network.ApiClient;
import com.marand.espressouitest.util.Resource;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MyRepository {
    private static MyRepository instance;
    private MediatorLiveData<Resource<List<Photo>>> photos;

    public static MyRepository getInstance() {
        if (instance == null)
            instance = new MyRepository();

        return instance;
    }

    public LiveData<Resource<List<Photo>>> makeReactiveQuery() {
        if (photos == null) {
            photos = new MediatorLiveData<>();
            photos.setValue(Resource.loading((List<Photo>)null));
            final LiveData<Resource<List<Photo>>> source = LiveDataReactiveStreams.fromPublisher(
                    ApiClient.getApiRequest().getPhotos().onErrorReturn(throwable -> {
                        Photo photo = new Photo();
                        photo.setId(-1);
                        ArrayList<Photo> photos = new ArrayList<>();
                        photos.add(photo);
                        return photos;
                    }).map((Function<List<Photo>, Resource<List<Photo>>>) photos -> {
                        if (photos.size() > 0) {
                            if (photos.get(0).getId() == -1) {
                                return Resource.error("Some error happened!", null);
                            }
                        }
                        return Resource.success(photos);
                    }).subscribeOn(Schedulers.io())
            );
            photos.addSource(source, listResource -> {
                photos.setValue(listResource);
                photos.removeSource(source);
            });
        }
        return photos;
    }
}
