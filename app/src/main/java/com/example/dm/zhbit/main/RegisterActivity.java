package com.example.dm.zhbit.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.AppUser;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 注册页面
 */
public class RegisterActivity extends Activity {
    private static final String LOG_MSG = "RegisterActivity";


    private boolean isHidden = true;

    private AppUser appUser;

    private ImageButton titleImv;
    private TextView titleCenterTv;
    private TextView titleRightTv;

    // 注册界面输入框控件
    private EditText loginnumEt;
    private EditText emailAddressEt;
    private EditText passwordEt;
    private EditText nameEt;
    private ImageView loginnumWarnImv;
    private ImageView nameWarnImv;
    private ImageView emailWarnImv;
    private ImageView passwordEyeImv;
    private Button registerBtn;

    private RadioButton StudentRegistBtn;
    private RadioButton TeacherRegistBtn;
    private RadioButton AdminRegistBtn;
    private RadioGroup Role;

    // 注册信息
    private String loginnumStr;
    private String emailAddressStr;
    private String passwordStr;
    private String nameStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        initView(); // 初始化界面控件

        userRegister();  // 用户注册

    }

    /**
     * 注册页面的初始化
     */
    private void initView() {
        titleImv = (ImageButton) findViewById(R.id.title_imv);
        titleImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });

        titleCenterTv = (TextView) findViewById(R.id.title_center_text_tv);
        titleCenterTv.setText("用户注册");

        titleRightTv = (TextView) findViewById(R.id.title_right_text_tv);
        titleRightTv.setVisibility(View.GONE);

        loginnumEt = (EditText) findViewById(R.id.loginnum_et);
        emailAddressEt = (EditText) findViewById(R.id.email_et);
        passwordEt = (EditText) findViewById(R.id.passwd_et);
        nameEt= (EditText) findViewById(R.id.name_et);

        loginnumWarnImv = (ImageView) findViewById(R.id.reg_loginnum_warning_imv);
        emailWarnImv = (ImageView) findViewById(R.id.reg_email_warning_imv);
        passwordEyeImv = (ImageView) findViewById(R.id.reg_password_view_off_imv);
        nameWarnImv= (ImageView) findViewById(R.id.reg_name_warning_imv);

        registerBtn = (Button) findViewById(R.id.register_btn);
        Role = (RadioGroup) findViewById(R.id.role_rg);
        TeacherRegistBtn = (RadioButton) findViewById(R.id.teacher_rb);
        StudentRegistBtn = (RadioButton) findViewById(R.id.student_rb);

    }

    /**
     * 用户注册
     */
    private void userRegister() {
        // 密码是否可见控件的处理逻辑
        passwordEyeImv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHidden) {
                    // 当需要可见密码时
                    passwordEyeImv.setImageResource(R.drawable.ic_visibility_grey600_18dp);
                    passwordEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // 当需要密码不可见时
                    passwordEyeImv.setImageResource(R.drawable.ic_visibility_off_grey600_18dp);
                    passwordEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

                isHidden = !isHidden;
                passwordEt.postInvalidate();

                // 切换后将passwordEt光标置于末尾
                CharSequence charSequence = passwordEt.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spannable = (Spannable) charSequence;
                    Selection.setSelection(spannable, charSequence.length());
                }
            }
        });

        // 注册按钮控件的处理逻辑
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginnumStr = loginnumEt.getText().toString().trim();
                emailAddressStr = emailAddressEt.getText().toString().trim();
                passwordStr = passwordEt.getText().toString().trim();
                nameStr=nameEt.getText().toString().trim();
                appUser = new AppUser();
                int len = loginnumStr.length();
                // 输入框的内容的简单校验
                if (loginnumStr.equals("")) {
                    loginnumWarnImv.setVisibility(View.VISIBLE);
                    Toast.makeText(RegisterActivity.this, "请输入工号或学号!", Toast.LENGTH_LONG).show();
                    }else if ("".equals(nameStr)) {
                    nameWarnImv.setVisibility(View.VISIBLE);
                    Toast.makeText(RegisterActivity.this, "请输入姓名!", Toast.LENGTH_LONG).show();
                }
                else if ("".equals(emailAddressStr)) {
                    emailWarnImv.setVisibility(View.VISIBLE);
                    Toast.makeText(RegisterActivity.this, "请输入注册邮箱!", Toast.LENGTH_LONG).show();
                } else if ("".equals(passwordStr)) {
                    Toast.makeText(RegisterActivity.this, "请输入密码!", Toast.LENGTH_LONG).show();
                } else if (len!=4&&len!=12) {
                    Toast.makeText(RegisterActivity.this, "账号错误", Toast.LENGTH_LONG).show();

                }else if (len==12 && TeacherRegistBtn.isChecked()) {
                    Toast.makeText(RegisterActivity.this, "选择错误，请重新选择", Toast.LENGTH_LONG).show();
                }
                else if (len==4 && StudentRegistBtn.isChecked()) {
                    Toast.makeText(RegisterActivity.this, "选择错误，请重新选择", Toast.LENGTH_LONG).show();
                } else if (!"".equals(loginnumStr) && !"".equals(emailAddressStr) && !"".equals(passwordStr)) {
                            loginnumWarnImv.setVisibility(View.GONE);
                            emailWarnImv.setVisibility(View.GONE);
                            nameWarnImv.setVisibility(View.GONE);

                            addRole();
                            appUser.setUserLoginnum(loginnumStr);
                            appUser.setUserName(nameStr);
                            appUser.setUsername(loginnumStr);
                            appUser.setPassword(passwordStr);
                            appUser.setEmail(emailAddressStr);
                            appUser.signUp(new SaveListener<AppUser>() {
                                @Override
                                public void done(AppUser appUser, BmobException e) {
                                    if (e == null) {
                                        Log.i(LOG_MSG, "$$$$$$: 注册成功");
                                        Toast.makeText(RegisterActivity.this, "注册成功!", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "注册失败! " + e.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
            }

        });
    }



    private void addRole() {
            if ( StudentRegistBtn.isChecked()) {
                appUser.setUserRole("Student");
            }
            else if(TeacherRegistBtn.isChecked()) {
                    appUser.setUserRole("Teacher");
                }
            }


    }

