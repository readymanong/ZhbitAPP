package com.example.dm.zhbit.student;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.Suggestion;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2017/3/18.
 */

public class StudentSuggestionAty extends Activity implements View.OnClickListener {
    private ImageButton title_imv;
    private EditText student_suggestion_et;
    private TextView student_suggestion_hasnum_tv;
    private Button student_suggestion_submit_btn;
    int maxLength = 300;
    private TextView tv_tea,tv_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_suggestion);

        initView();

        setUpListener();
    }


    private void initView() {
        title_imv = (ImageButton) findViewById(R.id.title_imv);
        student_suggestion_et = (EditText) findViewById(R.id.student_suggestion_et);
        student_suggestion_hasnum_tv = (TextView) findViewById(R.id.student_suggestion_hasnum_tv);
        student_suggestion_submit_btn = (Button) findViewById(R.id.student_suggestion_submit_btn);

        tv_tea= (TextView) findViewById(R.id.tv_tea);
        tv_course= (TextView) findViewById(R.id.tv_course);
        String teaStr=getIntent().getStringExtra("tv_tea");
        String courseStr=getIntent().getStringExtra("tv_course");
        tv_tea.setText(teaStr);
        tv_course.setText(courseStr);
    }

    private void setUpListener() {
        title_imv.setOnClickListener(StudentSuggestionAty.this);
        student_suggestion_et.addTextChangedListener(mTextWatcher);
        student_suggestion_et.setSelection(student_suggestion_et.length());
        student_suggestion_submit_btn.setOnClickListener(StudentSuggestionAty.this);
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        private int editStart;
        private int editEnd;
        private CharSequence charSequence;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            charSequence = s;
        }

        @Override
        public void afterTextChanged(Editable s) {
            int number = maxLength - s.length();
            student_suggestion_hasnum_tv.setText("" + number);
            editStart = student_suggestion_et.getSelectionStart();
            editEnd = student_suggestion_et.getSelectionEnd();

            if (charSequence.length() > maxLength) {
                s.delete(editStart - 1, editEnd);
                int tempSelection = editEnd;
                student_suggestion_et.setText(s);
                student_suggestion_et.setSelection(tempSelection);
            }
        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_imv:
                StudentSuggestionAty.this.finish();
                break;
            case R.id.student_suggestion_submit_btn:
                String SuggestStr = student_suggestion_et.getText().toString().trim();
                if ("".equals(SuggestStr) || SuggestStr == null) {
                    Toast.makeText(StudentSuggestionAty.this, "请填写你的建议", Toast.LENGTH_SHORT).show();
                } else {
                    updatedata();
                }
                break;
            default:
                break;
        }
    }

    private void updatedata() {
        String SuggestStr = student_suggestion_et.getText().toString().trim();
        String teaStr=getIntent().getStringExtra("tv_tea");
        String courseStr=getIntent().getStringExtra("tv_course");
        tv_tea.setText(teaStr);
        tv_course.setText(courseStr);
        Suggestion suggestion=new Suggestion();
        suggestion.setSuggestionContent(SuggestStr);
        suggestion.setComment_tea(teaStr);
        suggestion.setComment_course(courseStr);
        suggestion.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                        Toast.makeText(StudentSuggestionAty.this, "数据上传成功, 感谢你的建议!",
                                Toast.LENGTH_SHORT).show();
                        StudentSuggestionAty.this.finish();
            }
                else {
                    Toast.makeText(StudentSuggestionAty.this, "上传数据失败, 请稍后再试!",
                                Toast.LENGTH_SHORT).show();
                }
            }
        });

        }
}
//            suggestion.setSuggestionContent(feedbackStr);
//            suggestion.save(new SaveListener<String>() {
//                @Override
//                public void done(String s, BmobException e) {
//                    if (e == null) {
//                        Toast.makeText(StudentSuggestionAty.this, "数据上传成功, 感谢你的建议!",
//                                Toast.LENGTH_SHORT).show();
//                        StudentSuggestionAty.this.finish();
//                    } else {
//                        Toast.makeText(StudentSuggestionAty.this, "上传数据失败, 请稍后再试!",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });




