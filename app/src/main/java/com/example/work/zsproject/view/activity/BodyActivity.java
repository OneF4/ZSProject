package com.example.work.zsproject.view.activity;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.work.zsproject.R;
import com.example.work.zsproject.view.base.BaseActivity;
import com.example.work.zsproject.view.fragments.MyFragment;
import com.example.work.zsproject.view.fragments.NosecurityFragment;
import com.example.work.zsproject.view.fragments.NotpassFragment;
import com.example.work.zsproject.view.fragments.PassFragments;
import com.example.work.zsproject.view.tableutils.TabEntity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BodyActivity extends BaseActivity {

    private ViewPager mBodyViewpager;
    private CommonTabLayout mBodyTablelayout;
    private String[] mTitles = {"未安检", "未通过安检", "通过安检", "我"};
    private List<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    /*未选择时的icon*/
    private int[] mIconUnselectIds = {
            R.mipmap.weianjianweijihuo, R.mipmap.tonguoweijihuo,
            R.mipmap.nosur_weijihuo, R.mipmap.myweijihuo};
    /*选择时的icon*/
    private int[] mIconSelectIds = {
            R.mipmap.weianjianjihuo, R.mipmap.tongguojihuo,
            R.mipmap.nosur_jihuo, R.mipmap.myjihuo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

        initView();
        initData();
    }

    private void initView() {
        mBodyViewpager = (ViewPager) findViewById(R.id.body_viewpager);
        mBodyTablelayout = (CommonTabLayout) findViewById(R.id.body_tablelayout);
    }


    private void initData() {
        mFragments.add(new NosecurityFragment());
        mFragments.add(new NotpassFragment());
        mFragments.add(new PassFragments());
        mFragments.add(new MyFragment());

        for (int i = 0; i < mTitles.length; i++) {

            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));

        }

        mBodyTablelayout.setTabData(mTabEntities);

        mBodyTablelayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

                mBodyViewpager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mBodyViewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        mBodyViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                mBodyTablelayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置小红点
        //mBodyTablelayout.showMsg(3, 0);


    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {

            return mFragments.size();

        }

        @Override
        public CharSequence getPageTitle(int position) {

            return mTitles[position];

        }

        @Override
        public Fragment getItem(int position) {

            return mFragments.get(position);

        }
    }

}
