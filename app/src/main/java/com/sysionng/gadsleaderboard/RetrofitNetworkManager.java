package com.sysionng.gadsleaderboard;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNetworkManager {
    private final static String TAG = RetrofitNetworkManager.class.getSimpleName();
    private OkHttpClient mOkHttpClient = null;
    private HttpLoggingInterceptor logger = null;
    private static  Retrofit retrofitClient = null;
    private static RetrofitNetworkManager mRetrofitNetworkManager = null;
    private final String BASE_URL = "https://gadsapi.herokuapp.com";

    private RetrofitNetworkManager () {
        if (mOkHttpClient == null) {
            initOkHttpClient();
        }

        //Gson gson = new GsonBuilder().create();
        GsonBuilder builder = new GsonBuilder();
        builder.serializeNulls();
        Gson gson = builder.create();

        retrofitClient = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

    public static Retrofit getInstance() {
        if (retrofitClient == null) {
            mRetrofitNetworkManager = new RetrofitNetworkManager();
        }

        return retrofitClient;
    }

    private void initOkHttpClient() {
        int REQUEST_TIMEOUT = 10;

        if (logger == null) {
            initOkHttpLogging();
        }

        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logger)
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    private void initOkHttpLogging() {
        logger = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public  <S> S retrofitService(Class<S> serviceType) {
        return retrofitClient.create(serviceType);
    }







}//
