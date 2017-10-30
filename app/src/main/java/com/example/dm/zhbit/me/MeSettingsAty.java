package com.example.dm.zhbit.me;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.AppUser;
import com.example.dm.zhbit.main.MainActivity;

import cn.bmob.v3.BmobUser;

/**
 * 个人设置
 */
public class MeSettingsAty extends Activity {
    private ImageButton titleLeftImv;
    private TextView titleTv;
    private Button logoutBtn;

    private RelativeLayout mAboutRout;

    private AppUser appUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_settings_layout);

        initView();
        eventsDeal();
    }


    private void initView() {
        titleLeftImv = (ImageButton) findViewById(R.id.me_settings_title).findViewById(R.id.title_imv);
        titleTv = (TextView) findViewById(R.id.me_settings_title).findViewById(R.id.title_center_text_tv);

        mAboutRout = (RelativeLayout) findViewById(R.id.settings_about_rout);

        logoutBtn = (Button) findViewById(R.id.settings_logout_btn);

        titleTv.setText("个人设置");
        titleLeftImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MeSettingsAty.this.finish();
            }
        });

        appUser = BmobUser.getCurrentUser(AppUser.class);
        if (appUser == null) {
            logoutBtn.setBackgroundColor(Color.parseColor("#616161"));
            logoutBtn.setClickable(false);
        }
    }

    private void eventsDeal() {
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appUser != null) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MeSettingsAty.this);
                    builder.setMessage("确定要退出当前账户吗?");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AppUser.logOut();
                            startActivity(new Intent(MeSettingsAty.this, MainActivity.class));
                            MeSettingsAty.this.finish();
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.show();
                }
            }
        });


    }
}
