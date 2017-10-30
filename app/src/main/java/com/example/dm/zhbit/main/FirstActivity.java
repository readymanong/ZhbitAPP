package com.example.dm.zhbit.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.example.dm.zhbit.R;

import cn.bmob.v3.Bmob;

/**
 * 启动画面
 */
public class FirstActivity extends Activity {
    private final static String BMOB_APP_ID = "cfea24eddda5cf0a7cc265ea5995ec42";
    private String aMapSHA1Value = "";
    private TextView launchTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化 Bmob SDK
        // 使用时请将第二个参数Application ID替换成你在Bmob服务器端创建的Application ID
        Bmob.initialize(this, BMOB_APP_ID);

        setContentView(R.layout.launch_layout);

        initViews();



        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1, 1, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);//以某个点为中心缩放
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0.1f);//控制透明度
        animationSet.setDuration(3000);//动画持续时间
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);

        launchTv.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(FirstActivity.this, MainActivity.class));
                FirstActivity.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initViews() {
        launchTv = (TextView) findViewById(R.id.launch_tv);
    }
}
