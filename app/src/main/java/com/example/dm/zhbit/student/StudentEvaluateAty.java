package com.example.dm.zhbit.student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.AppUser;
import com.example.dm.zhbit.beans.Evaluation;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.List;


/**
 * Created by Administrator on 2017/3/16.
 */

public class StudentEvaluateAty extends Activity implements View.OnClickListener {
    private ImageButton stitle_bnext;
    private Evaluation eval;
    private TextView tea;
    private TextView course;
    private EditText evaluate_tea_name;
    private EditText evaluate_tea_course;
    private TextView evaluate_project1;
    private EditText project1_score;
    private TextView evaluate_project2;
    private EditText project2_score;
    private TextView evaluate_project3;
    private EditText project3_score;
    private TextView evaluate_project4;
    private EditText project4_score;
    private Button stu_evaluate_next_btn;
    private Spinner tea_sp,course_sp;
    private ArrayAdapter adapter;
    private String str1,str2;
    private TextView tv_tea,tv_course;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_evaluate);
        initViews();
        spinnner();
        eventsDeal();
    }
    private void initViews() {
        //mRelativeLayout = (RelativeLayout) view.findViewById(R.id.student_title_rout);
       // TextView titleTextTv = (TextView) view.findViewById(R.id.title_function_tv);
        TextView tea = (TextView) findViewById(R.id.tea);
        evaluate_tea_name = (EditText) findViewById(R.id.tea_name);
        tea_sp= (Spinner) findViewById(R.id.tea_sp);


        TextView course = (TextView) findViewById(R.id.course);
        evaluate_tea_course = (EditText) findViewById(R.id.course_name);
        course_sp = (Spinner) findViewById(R.id.course_sp);
        evaluate_project1 = (TextView)findViewById(R.id.evaluate_et1);
        evaluate_project2 = (TextView) findViewById(R.id.evaluate_et2);
        evaluate_project3 = (TextView) findViewById(R.id.evaluate_et3);
        evaluate_project4 = (TextView) findViewById(R.id.evaluate_et4);
        project1_score = (EditText) findViewById(R.id.et_score1);
        project2_score = (EditText) findViewById(R.id.et_score2);
        project3_score = (EditText) findViewById(R.id.et_score3);
        project4_score = (EditText) findViewById(R.id.et_score4);
        stu_evaluate_next_btn = (Button) findViewById(R.id.stu_evaluate_next_btn);

        tv_tea= (TextView) findViewById(R.id.tv_tea);
        tv_course= (TextView) findViewById(R.id.tv_course);

    }

    private void spinnner() {
        str1 = (String) tea_sp.getSelectedItem();
        tea_sp.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //拿到被选择项的值
                str1 = (String) tea_sp.getSelectedItem();

                Log.i("信息：", str1);
                evaluate_tea_name.setText(str1);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        course_sp.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //拿到被选择项的值
                str2 = (String) course_sp.getSelectedItem();
                Log.i("信息：", str2);
                evaluate_tea_course.setText(str2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void eventsDeal() {
        stu_evaluate_next_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String teanameStr = evaluate_tea_name.getText().toString().trim();
                String courseStr = evaluate_tea_course.getText().toString().trim();
                String project1Str = evaluate_project1.getText().toString().trim();
                String score1Str = project1_score.getText().toString().trim();
                String project2Str = evaluate_project2.getText().toString().trim();
                String score2Str = project2_score.getText().toString().trim();
                String project3Str = evaluate_project3.getText().toString().trim();
                String score3Str = project3_score.getText().toString().trim();
                String project4Str = evaluate_project4.getText().toString().trim();
                String score4Str = project4_score.getText().toString().trim();


                //String elvateStr[]={teanameStr,courseStr,project1Str,score1Str,project2Str,score2Str,project3Str,score3Str,project4Str,score4Str};
                if ("".equals(teanameStr) || teanameStr== null) {
                    Toast.makeText(StudentEvaluateAty.this, "请填写教师名字", Toast.LENGTH_SHORT).show();
                } else if ("".equals(courseStr) || courseStr == null) {
                    Toast.makeText(StudentEvaluateAty.this, "请填写教师名字", Toast.LENGTH_SHORT).show();
                } else if ("".equals(score1Str) || score1Str == null) {
                    Toast.makeText(StudentEvaluateAty.this, "请填写分数", Toast.LENGTH_SHORT).show();
                }else if ("".equals(score1Str) || score1Str == null) {
                    Toast.makeText(StudentEvaluateAty.this, "请填写分数", Toast.LENGTH_SHORT).show();
                } else if ("".equals(score2Str) || score2Str == null) {
                    Toast.makeText(StudentEvaluateAty.this, "请填写分数", Toast.LENGTH_SHORT).show();
                } else if ("".equals(score3Str) || score3Str == null) {
                    Toast.makeText(StudentEvaluateAty.this, "请填写分数", Toast.LENGTH_SHORT).show();
                } else if ("".equals(score4Str) || score4Str == null) {
                    Toast.makeText(StudentEvaluateAty.this, "请填写分数", Toast.LENGTH_SHORT).show();

                } else {

                    AppUser user= BmobUser.getCurrentUser(AppUser.class);
                    eval = new Evaluation();
                   eval.setCommentuser(user);
                    eval.setEvaluate_tea_name(teanameStr);
                    eval.setEvaluate_tea_course(courseStr);
                    eval.setEvaluate_project1(project1Str);
                    eval.setProject1_score(Integer.parseInt(score1Str));
                    eval.setEvaluate_project2(project2Str);
                    eval.setProject2_score(Integer.parseInt(score2Str));
                    eval.setEvaluate_project3(project3Str);
                    eval.setProject3_score(Integer.parseInt(score3Str));
                    eval.setEvaluate_project4(project4Str);
                    eval.setProject4_score(Integer.parseInt(score4Str));
                    eval.setTotalscore((Integer.parseInt(score1Str)+Integer.parseInt(score2Str)+Integer.parseInt(score3Str)
                    +Integer.parseInt(score4Str))*5);
                    eval.save(new SaveListener<String>() {
                        @Override
                        public void done(String eval, BmobException e) {
                            if (e == null) {
                                Toast.makeText(StudentEvaluateAty.this, "数据已保存!", Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(StudentEvaluateAty.this, StudentSuggestionAty.class);
                                intent.putExtra("tv_tea",evaluate_tea_name.getText().toString());
                                intent.putExtra("tv_course",evaluate_tea_course.getText().toString());
                                startActivity(intent);

                            } else {
                                Toast.makeText(StudentEvaluateAty.this, "提交失败!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }

            }
        });
    }
    private void getTeaCour(){
        BmobQuery<Evaluation> query = new BmobQuery<Evaluation>();
        query.findObjects(new FindListener<Evaluation>() {
            @Override
            public void done(List<Evaluation> list, BmobException e) {
                if (e == null) {
                    for (Evaluation eval : list) {
                        String teaStr =eval.getEvaluate_tea_name();
                        tv_tea.append(teaStr);

                        Toast.makeText(StudentEvaluateAty.this,"成功",Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Log.i("Bmob", "失败" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }
    @Override
    public void onClick(View view) {

    }
}


