package com.example.work.zsproject.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.work.zsproject.R;
import com.example.work.zsproject.view.activity.SearchActivity;
import com.example.work.zsproject.view.adapter.NosecurityAdapter;
import com.example.work.zsproject.view.date.DatePickPopupWindow;
import com.example.work.zsproject.view.date.DateUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 未安检页面
 */

public class NosecurityFragment extends Fragment implements View.OnClickListener {
    private XRecyclerView mNosecurityXrecycleView;
    private NosecurityAdapter nosecurityAdapter;
    private List<String> list;
    /**
     * 车辆（125）
     */
    private TextView mTitleVehicle;
    private ImageView mTitleSearch;
    /**
     * 2017-1-25
     */
    private TextView mTitleDateBegin;
    /**
     * 2017-2-25
     */
    private TextView mTitleDateFinish;
    private RelativeLayout mTitleRelativeLayout;
    private View parentView;
    private DatePickPopupWindow datePickPopupWindow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.nosercurity_layout, container, false);

        initView(view);
        return view;

    }

    private void initView(View view) {
        parentView = (LinearLayout) view.findViewById(R.id.parentView);
        mNosecurityXrecycleView = (XRecyclerView) view.findViewById(R.id.nosecurity_xrecycleView);
        mTitleVehicle = (TextView) view.findViewById(R.id.title_vehicle);
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

        list = new ArrayList<>();

        nosecurityAdapter = new NosecurityAdapter(getContext());

        mNosecurityXrecycleView.setLayoutManager(new LinearLayoutManager(getContext()));

        mNosecurityXrecycleView.setAdapter(nosecurityAdapter);

        for (int i = 0; i < 10; i++) {
            list.add("条目" + i);
        }

        nosecurityAdapter.setList(list);

        mTitleVehicle.setText("车辆（" + list.size() + "）");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.title_search:
                //跳转到搜索页面
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
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
