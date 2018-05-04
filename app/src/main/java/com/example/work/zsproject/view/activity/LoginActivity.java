package com.example.work.zsproject.view.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
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
    private String expression;

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

        //判断手机号的正则表达式
        expression = "^1[3|5|7|8|9][0-9]\\d{8}$";

        //创建timer对象
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //判断输入框是否为空
                if (!"".equals(mLoginEditMobile.getText().toString().trim()) && !"".equals(mLoginEditPass.getText().toString().trim()) && mLoginEditPass.getText().toString().trim().length() >= 6) {
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
                //判断手机号是否为11位
                if (mLoginEditMobile.getText().toString().trim().length() != 11) {
                    Toast.makeText(LoginActivity.this,"手机号必须为11位",Toast.LENGTH_LONG).show();
                }else if (!mLoginEditMobile.getText().toString().trim().matches(expression)){//判断输入的格式是否正确，是否为手机号
                    Toast.makeText(LoginActivity.this,"手机号格式不正确",Toast.LENGTH_LONG).show();
                }else {
                    //跳转到登录成功页面
                    Intent intent = new Intent(LoginActivity.this, BodyActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
