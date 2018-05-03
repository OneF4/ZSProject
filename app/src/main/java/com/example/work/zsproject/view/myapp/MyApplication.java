package com.example.work.zsproject.view.myapp;

import android.app.Application;
import android.content.res.Configuration;
import android.graphics.Point;
import android.view.WindowManager;

import com.example.work.zsproject.view.adaptive.DensityHelper;

/**
 * 初始化
 * */
public class MyApplication extends Application {

    public final static float DESIGN_WIDTH = 750; //绘制页面时参照的设计图宽度

    @Override
    public void onCreate() {
        super.onCreate();
        //引用工具类
        new DensityHelper(this, DESIGN_WIDTH).activate();
    }
}
