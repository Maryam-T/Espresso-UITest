package com.marand.espressouitest.network;

import com.marand.espressouitest.model.Photo;
import java.util.List;
import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ApiRequest {

    @GET("albums/1/photos")
    Flowable<List<Photo>> getPhotos();
}
