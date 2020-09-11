package com.sysionng.gadsleaderboard;

import android.content.Context;
import android.content.Intent;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PostForm {
    private static final String TAG = PostForm.class.getSimpleName();
    private Context mContext;
    protected final static int SUCCESS_REQUEST_CODE = 1505092020;
    protected final static int FAILURE_REQUEST_CODE = 1605092020;
    private Retrofit mRetrofit;
    private ApiService mApiService;
    private String mUrl;
    private String mfName;
    private String mlName;
    private String mEmail;
    private String mgLink;

    public PostForm(Context context, String url, String fName, String lName, String email, String gLink) {
        mContext = context;
        mUrl = url;
        mfName = fName;
        mlName = lName;
        mEmail = email;
        mgLink = gLink;

        initPostForm();
    }

    private void initPostForm() {
        mRetrofit = RetrofitNetworkManager.getInstance();   //get Retrofit instance

        if (mRetrofit != null) {
            mApiService = ApiServiceManager.getInstance(); //get ApiService instance

            Call<ResponseBody> postRequest = mApiService.submitProject(mUrl, mfName, mlName, mEmail, mgLink);

            postRequest.enqueue(new Callback<ResponseBody>() {

                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.isSuccessful()) {
                        Intent intent = new Intent(mContext, ConfirmationActivity.class);

                        //Log.d(TAG, "onResponse: " + response.toString());

                        intent.putExtra(ProjectSubmission.CONFIRMATION_TEXT, SUCCESS_REQUEST_CODE);
                        mContext.startActivity(intent);

                    } else {
                        Intent intent = new Intent(mContext, ConfirmationActivity.class);

                        //Log.d(TAG, "onResponse: " + response.toString());

                        intent.putExtra(ProjectSubmission.CONFIRMATION_TEXT, FAILURE_REQUEST_CODE);
                        mContext.startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Intent intent = new Intent(mContext, ConfirmationActivity.class);

                    //Log.d(TAG, "onResponse: " + t.toString());

                    intent.putExtra(ProjectSubmission.CONFIRMATION_TEXT, FAILURE_REQUEST_CODE);
                    mContext.startActivity(intent);
                }
            });

        }
    }

}//
