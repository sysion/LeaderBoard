package com.sysionng.gadsleaderboard;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GetLeaders {
    private static final String TAG = GetLeaders.class.getSimpleName();
    private Context mContext;
    private List<GadsModel> mGadsModels = new ArrayList<GadsModel>();
    private ApiService mApiService;
    private String mUrl;
    private ResultCallback mResultCallback;
    private Retrofit mRetrofit;

    public GetLeaders(Context context, String url, ResultCallback resultCallback) {
        mContext = context;
        mUrl = url;
        mResultCallback = resultCallback;

        initGetLeaders(mResultCallback);   //populates mGadsModels with result from network call
    }

    private void initGetLeaders(ResultCallback cb) {
        mRetrofit = RetrofitNetworkManager.getInstance();   //get Retrofit instance

        if (mRetrofit != null) {
            mApiService = ApiServiceManager.getInstance(); //get ApiService instance

            Call<List<GadsModel>> ItemRequest = mApiService.getLeaders(mUrl);

            ItemRequest.enqueue(new Callback<List<GadsModel>>() {

                @Override
                public void onResponse(Call<List<GadsModel>> call, Response<List<GadsModel>> response) {

                    if (response.isSuccessful()) {
                        mGadsModels = response.body();

                        if (cb != null) {
                            cb.onResultReady(mGadsModels);
                        }

                    } else {
                        Log.d(TAG, "RESPONSE FAILED, responseCode: " + response.code());
                        Toast.makeText(mContext, "Empty Leaders Result or Server error", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<List<GadsModel>> call, Throwable t) {
                    if (t instanceof IOException) {
                        Toast.makeText(mContext, "Error Getting Leaders Result Due To Network Connection", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "t.messages: " + t.getMessage());
                    } else {
                        Toast.makeText(mContext, "Error Converting Received Data", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "t.messages: " + t.getMessage());
                    }
                }
            });
        }
    }


}//
