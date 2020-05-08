package com.marand.espressouitest.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.marand.espressouitest.util.Constants.BASE_URL;

public class ApiClient {
    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static ApiRequest apiRequest = retrofit.create(ApiRequest.class);

    public static ApiRequest getApiRequest(){
        return apiRequest;
    }
}
