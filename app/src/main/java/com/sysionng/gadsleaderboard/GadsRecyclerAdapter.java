package com.sysionng.gadsleaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class GadsRecyclerAdapter extends RecyclerView.Adapter<GadsRecyclerAdapter.GadsViewHolder> {
    private static final String TAG = GadsRecyclerAdapter.class.getSimpleName();
    private final Context mContext;
    private LayoutInflater mLayoutInflater = null;
    private final List<GadsModel> mGadsModels;
    private GadsModel mGadsModel;
    private String name;
    private String hour;
    private String score;
    private String country;
    private String badgeUrl;
    private String hourText;
    private String scoreText;

    public GadsRecyclerAdapter(Context context, List<GadsModel> GadsModels) {
        mContext = context;
        mGadsModels = GadsModels;

        if (mContext != null)
            mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public GadsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.row_layout, parent, false);
        return new GadsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GadsViewHolder vHolder, int position) {
        mGadsModel = mGadsModels.get(position);

        name = mGadsModel.getName();
        hour = mGadsModel.getHours();
        score = mGadsModel.getScore();
        country = mGadsModel.getCountry();
        badgeUrl = mGadsModel.getBadgeUrl();

        hourText = hour + " learning hours, " + country;
        scoreText = score + " skill IQ score, " + country;

        vHolder.tvName.setText(name);

        if (NotEmpty(hour) && ! NotEmpty(score)) {
            vHolder.tvHourScore.setText(hourText);
        } else if (! NotEmpty(hour) && NotEmpty(score)) {
            vHolder.tvHourScore.setText(scoreText);
        }

        Picasso.with(mContext)
                .load(badgeUrl)
                .placeholder(R.drawable.gads_placeholder)
                .resize(120,120)
                .into(vHolder.ivImage);
    }

    @Override
    public int getItemCount() {
        return mGadsModels.size();
    }

    public class GadsViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvHourScore;
        public ImageView ivImage;

        public GadsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.row_name_textView);
            tvHourScore = itemView.findViewById(R.id.row_hour_score_textView);
            ivImage = itemView.findViewById(R.id.row_imageView);
        }
    }

    public static boolean NotEmpty(String strInput) {
        if (strInput != null && ! strInput.trim().isEmpty() && strInput.length() != 0) {
            return true;
        } else {
            return false;
        }
    }



}//
