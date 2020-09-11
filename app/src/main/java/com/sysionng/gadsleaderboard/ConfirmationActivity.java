package com.sysionng.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class ConfirmationActivity extends AppCompatActivity {
    private final static String TAG = ConfirmationActivity.class.getSimpleName();
    private Dialog mConfirmationDialog;
    private Dialog mSuccessDialog;
    private Dialog mFailDialog;
    private Context mContext;

    private PostForm mPostForm;
    private String BaseUrl = "https://docs.google.com/forms/d/e/";
    private String FormID = "1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";
    private String FullURL= BaseUrl + FormID;

    private String mFirstname;
    private String mLastname;
    private String mEmailAddress;
    private String mGitLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        mContext = this;
        
        ImageView backArrow = findViewById(R.id.back_arrow_imageView);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ProjectSubmission.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        int code = intent.getIntExtra(ProjectSubmission.CONFIRMATION_TEXT, -1);

        if (code == ProjectSubmission.CONFIRMATION_REQUEST_CODE) {
            mFirstname = intent.getStringExtra(ProjectSubmission.FIRSTNAME_TEXT);
            mLastname = intent.getStringExtra(ProjectSubmission.LASTNAME_TEXT);
            mEmailAddress = intent.getStringExtra(ProjectSubmission.EMAIL_TEXT);
            mGitLink = intent.getStringExtra(ProjectSubmission.GITLINK_TEXT);

            confirmationDialog();
        } else if (code == PostForm.SUCCESS_REQUEST_CODE) {
            successDialog();
        } else if (code == PostForm.FAILURE_REQUEST_CODE) {
            failDialog();
        }


    }

    protected void confirmationDialog() {
        mConfirmationDialog = new Dialog(mContext);
        mConfirmationDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mConfirmationDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);  //remove shadow from dialog box
        mConfirmationDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);  //remove shadow from dialog box
        mConfirmationDialog.setCanceledOnTouchOutside(false);   //prevent dialog box from getting dismissed on outside touch
        mConfirmationDialog.setContentView(R.layout.submit_confirm);
        Button confirmButton = mConfirmationDialog.findViewById(R.id.confirm_submit);
        ImageView cancelImgView = mConfirmationDialog.findViewById(R.id.cancel_dialog_imageView);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPostForm = new PostForm(mContext, FullURL, mFirstname, mLastname, mEmailAddress, mGitLink);

                mConfirmationDialog.dismiss();

            }
        });

        cancelImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mConfirmationDialog.isShowing()) {
                    mConfirmationDialog.dismiss();
                }
            }
        });

        mConfirmationDialog.show();
    }

    protected void successDialog() {
        mSuccessDialog = new Dialog(mContext);
        mSuccessDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mSuccessDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);  //remove shadow from dialog box
        mSuccessDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);  //remove shadow from dialog box
        mSuccessDialog.setContentView(R.layout.submit_pass);

        mSuccessDialog.show();
    }

    protected void failDialog() {
        mFailDialog = new Dialog(mContext);
        mFailDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mFailDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); //remove shadow from dialog box
        mFailDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent); //remove shadow from dialog box
        mFailDialog.setContentView(R.layout.submit_fail);

        mFailDialog.show();
    }


}//
