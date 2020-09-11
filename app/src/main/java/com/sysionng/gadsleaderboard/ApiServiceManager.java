package com.sysionng.gadsleaderboard;

import retrofit2.Retrofit;

public class ApiServiceManager {
    private final static String TAG = ApiServiceManager.class.getSimpleName();

    private static ApiService service;
    private static ApiServiceManager mApiServiceManager;
    private Retrofit mRetrofit;

    private ApiServiceManager() {
        mRetrofit = RetrofitNetworkManager.getInstance();   //get instance of Retrofit
        service = mRetrofit.create(ApiService.class);   //Retrofit creates the body of interface mApiService
    }

    public static ApiService getInstance() {
        if (mApiServiceManager == null) {
            mApiServiceManager = new ApiServiceManager();
        }

        return service;
    }



}//
