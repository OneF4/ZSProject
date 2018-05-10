package com.example.work.zsproject.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.work.zsproject.R;
import com.example.work.zsproject.view.base.BaseActivity;

/**
 * 未安检页面-->搜索页面
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mSearchGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);
        initView();
    }

    private void initView() {
        mSearchGoBack = (ImageView) findViewById(R.id.search_go_back);
        mSearchGoBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.search_go_back:
                finish();
                break;
        }
    }
}
