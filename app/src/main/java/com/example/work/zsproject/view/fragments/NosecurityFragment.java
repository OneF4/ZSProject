package com.example.work.zsproject.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.work.zsproject.R;
import com.example.work.zsproject.view.adapter.NosecurityAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 未安检页面
 */

public class NosecurityFragment extends Fragment {
    private View view;
    private XRecyclerView mNosecurityXrecycleView;
    private NosecurityAdapter nosecurityAdapter;
    private List<String> list;
    /**
     * 车辆（125）
     */
    private TextView mTitleVehicle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.nosercurity_layout, container, false);

        initView(view);
        return view;

    }

    private void initView(View view) {
        mNosecurityXrecycleView = (XRecyclerView) view.findViewById(R.id.nosecurity_xrecycleView);
        mTitleVehicle = (TextView) view.findViewById(R.id.title_vehicle);

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
}
