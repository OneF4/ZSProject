package com.example.work.zsproject.view.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work.zsproject.R;
import com.example.work.zsproject.view.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 请输入手机号
     */
    private EditText mLoginEditMobile;
    /**
     * 请输入密码
     */
    private EditText mLoginEditPass;
    /**
     * 登录
     */
    private TextView mLoginTextView;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mLoginEditMobile = (EditText) findViewById(R.id.login_edit_mobile);
        mLoginEditPass = (EditText) findViewById(R.id.login_edit_pass);
        mLoginTextView = (TextView) findViewById(R.id.login_text_view);
        mLoginTextView.setOnClickListener(this);
        //创建timer对象
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //判断输入框是否为空
                if (!"".equals(mLoginEditMobile.getText().toString().trim()) && !"".equals(mLoginEditPass.getText().toString().trim())) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //设置登录按钮可以点击
                            mLoginTextView.setEnabled(true);
                            //通过动画设置透明度显示100%
                            ObjectAnimator anim = ObjectAnimator.ofFloat(mLoginTextView, "alpha", 1.0f);
                            // 动画持续时间
                            anim.setDuration(100);
                            //开启动画
                            anim.start();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //设置登录按钮不能点击
                            mLoginTextView.setEnabled(false);
                            //通过动画设置透明度显示50%
                            ObjectAnimator anim = ObjectAnimator.ofFloat(mLoginTextView, "alpha", 0.5f);
                            // 动画持续时间
                            anim.setDuration(100);
                            //开启动画
                            anim.start();
                        }
                    });
                }
            }
        }, 0, 100);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login_text_view:
                Toast.makeText(this, "-.-", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
