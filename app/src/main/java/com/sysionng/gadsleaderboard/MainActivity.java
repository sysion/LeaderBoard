package com.sysionng.gadsleaderboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    private Context mContext;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabItem mTabItemLearningLeader;
    private TabItem mTabItemSkilIQLeader;
    private GadsPagerAdapter mGadsPagerAdapter;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        mTabLayout = findViewById(R.id.main_tablayout);
        mViewPager = findViewById(R.id.main_vpager);
        mTabItemLearningLeader = findViewById(R.id.learning_leaders);
        mTabItemSkilIQLeader = findViewById(R.id.skill_iq_leaders);
        submitButton = findViewById(R.id.toolbar_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ProjectSubmission.class);
                startActivity(intent);
            }
        });

        mGadsPagerAdapter = new GadsPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mGadsPagerAdapter);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

    }//

    public static class GadsPagerAdapter extends FragmentPagerAdapter {
        private int tabCount;

        public GadsPagerAdapter(FragmentManager fragmentManager, int tabCount) {
            super(fragmentManager);
            this.tabCount = tabCount;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return FragmentLearningLeaders.newInstance("Learning Leaders", 0);
                case 1:
                    return FragmentSkillIQLeaders.newInstance("Skill IQ Leaders", 1);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return tabCount;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }


    }//public static class GadsPagerAdapter extends FragmentPagerAdapter

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}//
