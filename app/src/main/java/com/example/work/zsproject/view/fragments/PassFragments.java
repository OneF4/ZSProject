package com.example.work.zsproject.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.work.zsproject.R;
import com.example.work.zsproject.view.date.DatePickPopupWindow;
import com.example.work.zsproject.view.date.DateUtils;

/**
 * 通过安检页面
 */

public class PassFragments extends Fragment implements View.OnClickListener {
    private ImageView mTitleSearch;
    private TextView mTitleDateBegin;
    private TextView mTitleDateFinish;
    private RelativeLayout mTitleRelativeLayout;
    private View parentView;
    private DatePickPopupWindow datePickPopupWindow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pass_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        parentView = (LinearLayout) view.findViewById(R.id.parentView);
        mTitleSearch = (ImageView) view.findViewById(R.id.title_search);
        mTitleSearch.setOnClickListener(this);
        mTitleDateBegin = (TextView) view.findViewById(R.id.title_date_begin);
        mTitleDateFinish = (TextView) view.findViewById(R.id.title_date_finish);
        mTitleRelativeLayout = (RelativeLayout) view.findViewById(R.id.title_relative_Layout);
        mTitleRelativeLayout.setOnClickListener(this);
        //获取到当前日期
        String date = DateUtils.getTodayDate();
        //赋初始值
        mTitleDateBegin.setText(date);
        mTitleDateFinish.setText(date);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.title_search:
                break;
            case R.id.title_relative_Layout:
                showDatePop();
                break;
        }
    }

    private void showDatePop() {
        if(null == datePickPopupWindow){
            datePickPopupWindow = new DatePickPopupWindow(getContext());
            datePickPopupWindow.setDateSelectListener(new DatePickPopupWindow.OnDateSelectListener() {
                @Override
                public void onDateSelect(String start, String end) {
                    mTitleDateBegin.setText(start);
                    mTitleDateFinish.setText(end);
                }
            });
        }
        datePickPopupWindow.setTab(0);
        datePickPopupWindow.showAtLocation(parentView, Gravity.BOTTOM,0,0);
    }
}
