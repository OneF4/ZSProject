package com.example.work.zsproject.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.work.zsproject.R;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * 我的页面
 */

public class MyFragment extends Fragment {
    private ImageView mOnLineUpdatingRed;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mOnLineUpdatingRed = (ImageView) view.findViewById(R.id.on_line_updating_red);

        Glide.with(this)
                .load(R.mipmap.red)
                .bitmapTransform(new RoundedCornersTransformation(getContext(), 100, 0))
                .into(mOnLineUpdatingRed);
    }
}
