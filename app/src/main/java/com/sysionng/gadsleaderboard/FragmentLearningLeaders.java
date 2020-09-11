package com.sysionng.gadsleaderboard;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class FragmentLearningLeaders extends Fragment implements ResultCallback {
    private final static String TAG = FragmentLearningLeaders.class.getSimpleName();
    private static final String PARAM1 = "title";
    private static final String PARAM2 = "page";
    private String mTitle;
    private int mPage;
    private GetLeaders mGetLeaders;
    private List<GadsModel> mGadsModels = new ArrayList<GadsModel>();
    private GadsRecyclerAdapter mGadsRecyclerAdapter;
    private RecyclerView rv;
    public FragmentLearningLeaders() {} //required empty public constructor

    public static FragmentLearningLeaders newInstance(String title, int page) {
        FragmentLearningLeaders fragment = new FragmentLearningLeaders();
        Bundle bundle = new Bundle();

        bundle.putString(PARAM1, title);
        bundle.putInt(PARAM2, page);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mTitle = getArguments().getString(PARAM1);
            mPage = getArguments().getInt(PARAM2, 0);
        }

        String url = "https://gadsapi.herokuapp.com/api/hours";
        ResultCallback resultCallback = this;
        mGetLeaders = new GetLeaders(getContext(), url, resultCallback);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);
        rv = view.findViewById(R.id.rvLearningLeaders);

        return view;
    }

    @Override
    public void onResultReady(List<GadsModel> gadsModels) {

        mGadsRecyclerAdapter = new GadsRecyclerAdapter(getContext(), gadsModels);
        rv.setHasFixedSize(true);
        rv.setItemViewCacheSize(20);
        rv.setDrawingCacheEnabled(true);
        rv.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(mGadsRecyclerAdapter);

    }

}//
