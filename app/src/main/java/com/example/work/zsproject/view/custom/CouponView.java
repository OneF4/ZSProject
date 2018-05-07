package com.example.work.zsproject.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
/**
 * 自定义view画圆
 * */
public class CouponView extends LinearLayout {

    private Paint mPaint;       //画笔
    private float gap = 8;      //圆间距
    private float radius = 30;  //半径
    private int circleNum;      //圆数量
    private float remain;
    private float width;        //视图宽
    private float height;        //视图高


    public CouponView(Context context) {
        super(context);
    }

    public CouponView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
        mPaint.setDither(true);//设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
        mPaint.setColor(Color.parseColor("#f5f5f5"));
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        if (remain==0){
            //计算不整除的剩余部分
            remain = (int)(h-gap)%(2*radius+gap);
        }
        circleNum = 1;  //计算圆的数量
    }


    public CouponView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 上面定义了圆的半径和圆间距，同时初始化了这些值并且获取了需要画的圆数量。
     接下来只需要一个一个将圆画出来就可以了。
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //手机屏幕比值
        float  specificValue = 518 / height;
        //得到y轴坐标比值
        float y = 143 / specificValue;//计算出y轴坐标
        canvas.drawCircle(0,y,radius,mPaint);//在左边边画圆
        canvas.drawCircle(width,y,radius,mPaint);//在右边画圆
    }
}