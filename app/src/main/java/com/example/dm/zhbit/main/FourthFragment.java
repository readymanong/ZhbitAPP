package com.example.dm.zhbit.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;



import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.AppUser;

import com.example.dm.zhbit.me.MeSettingsAty;

import cn.bmob.v3.BmobUser;



/**
 * 第四个页面
 */
public class FourthFragment extends Fragment {
    private static final String LOG = "LOG";
    private static final int REQUEST_CODE = 1;

    private RelativeLayout meInfoRout;
    private ImageView meAvatarImv;
    private TextView NameTv;
    private TextView meMessageTv;


    private RelativeLayout meSettingsRout;


    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg4, container, false);

        initView();  // 控件初始化
        fillDatas();
        eventsDeal();  // 事件处理

        return view;
    }

    private void fillDatas() {
        AppUser appUser = BmobUser.getCurrentUser(AppUser.class);
        if (appUser == null) {
            NameTv.setText("未登录, 请点击登录");
            meAvatarImv.setImageResource(R.drawable.app_icon);
        } else {
            NameTv.setText(appUser.getUserName());

        }
    }





    /**
     * 初始化
     */
    private void initView() {
        meInfoRout = (RelativeLayout) view.findViewById(R.id.me_info_rout);
        meAvatarImv = (ImageView) view.findViewById(R.id.me_avatar_imv);
        NameTv = (TextView) view.findViewById(R.id.me_name_tv);
       //meMessageTv = (TextView) view.findViewById(R.id.me_message_tv);



        meSettingsRout = (RelativeLayout) view.findViewById(R.id.me_settings_rout);
    }

    /**
     * 界面控件事件处理
     */
    private void eventsDeal() {
        meInfoRout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStr = NameTv.getText().toString();
                //String messageStr = meMessageTv.getText().toString();

                AppUser appUser = BmobUser.getCurrentUser(AppUser.class);
                if (appUser == null) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("FourthFragment.name", nameStr);
                    //intent.putExtra("FourthFragment.messageStr", messageStr);
                    //FourthFragment.this.startActivityForResult(intent, REQUEST_CODE);
                }
            }
        });

        meSettingsRout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MeSettingsAty.class));
            }
        });
    }



}
