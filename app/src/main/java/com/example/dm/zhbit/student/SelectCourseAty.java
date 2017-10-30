package com.example.dm.zhbit.student;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.AppUser;
import com.example.dm.zhbit.beans.Evaluation;
import com.example.dm.zhbit.beans.SelectCourse;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class SelectCourseAty extends Activity {
    private Spinner spinnerCourse;
    private Spinner spinnerTea;
    private ArrayAdapter adapter; //存放数据
    private  EditText select_course;
    private  EditText select_tea;
    private String str1,str2;
    private Button select_course_btn,empty_course_btn;
//    private SelectCourse sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course_aty);
        initView();
        spinnner();
        Deal();
    }

    private void initView() {
        select_course = (EditText) findViewById(R.id.select_course_name);
        select_tea= (EditText) findViewById(R.id.select_tea_name);
        spinnerCourse= (Spinner) findViewById(R.id.selet_course_sp);
        spinnerTea = (Spinner) findViewById(R.id.select_tea_sp);

        select_course_btn= (Button) findViewById(R.id.select_course_btn);
        empty_course_btn= (Button) findViewById(R.id.empty_course_btn);

    }

    private void spinnner() {
        str1 = (String) spinnerTea.getSelectedItem();
        spinnerTea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //拿到被选择项的值
                str1 = (String) spinnerTea.getSelectedItem();

                Log.i("信息：", str1);
                select_tea.setText(str1);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //拿到被选择项的值
                str2 = (String) spinnerCourse.getSelectedItem();
                Log.i("信息：", str2);
                select_course.setText(str2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void Deal() {
        select_course_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCourse();
            }
        });
        empty_course_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emptyStr();
            }
        });
    }
    private void saveCourse(){
        String teanameStr = select_tea.getText().toString().trim();
        String courseStr = select_course.getText().toString().trim();
        AppUser user= BmobUser.getCurrentUser(AppUser.class);
        SelectCourse sc = new SelectCourse();
        sc.setSelect_user(user);
        sc.setSelect_tea(teanameStr);
        sc.setSelect_cou(courseStr);
        sc.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    Toast.makeText(SelectCourseAty.this, "选课成功!", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(SelectCourseAty.this, "选课失败!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void emptyStr(){
        select_course.setText("");
        select_tea.setText("");
    }
}
