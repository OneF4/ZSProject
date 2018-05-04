package com.example.work.zsproject.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.work.zsproject.R;
import com.example.work.zsproject.view.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;
/**
 * 启动页面
 * */
public class MainActivity extends BaseActivity {

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建timer对象
        timer = new Timer();
        //通过timer进行2秒跳转
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //跳转
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
