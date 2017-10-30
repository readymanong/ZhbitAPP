package com.example.dm.zhbit.teacher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.AEvaluation;
import com.example.dm.zhbit.beans.AppUser;
import com.example.dm.zhbit.beans.Evaluation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;


/**
 * Created by Administrator on 2017/3/20.
 */

public class SearchevaluationAty extends Activity implements View.OnClickListener {
    TextView search_course_name, search_score1_tv, search_score2_tv, search_score3_tv, search_score4_tv;
    Button search_evaluate_btn;
    ImageButton tea_searevl_next;
    private String course;
    private Integer score1;
    private Integer score2;
    private Integer score3;
    private Integer score4;
    private Spinner se_sp;
    private TextView search_score;
    private TextView search_elv_num,search_eva_rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tea_search_evaluate);
        initView();
        Deal();
    }

    private void initView() {
        search_eva_rate= (TextView) findViewById(R.id.search_eva_rate);
        search_score=(TextView)findViewById(R.id.search_course_score);
        search_elv_num=(TextView)findViewById(R.id.search_eva_num);
        search_course_name = (TextView) findViewById(R.id.search_course_name);
//        search_score1_tv = (TextView) findViewById(R.id.search_score1_tv);
//        search_score2_tv = (TextView) findViewById(R.id.search_score2_tv);
//        search_score3_tv = (TextView) findViewById(R.id.search_score3_tv);
//        search_score4_tv = (TextView) findViewById(R.id.search_score4_tv);
        search_evaluate_btn = (Button) findViewById(R.id.search_evaluate_btn);
        tea_searevl_next=(ImageButton)findViewById(R.id.tea_searevl_next);
        se_sp= (Spinner) findViewById(R.id.se_sp);

    }
    private void Deal(){
        search_evaluate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnner();
                queryEvaluation();
            }
        });
    }

    private void spinnner() {
        se_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //拿到被选择项的值
                String str = (String) se_sp.getSelectedItem();
                Log.i("信息：", str);
                search_course_name.setText(str);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void queryEvaluation() {
        final String usercourseStr=search_course_name.getText().toString().trim();
        if("".equals(usercourseStr)||usercourseStr==null){
            Toast.makeText(SearchevaluationAty.this, "请填选择课程名", Toast.LENGTH_SHORT).show();
        }
        BmobQuery<AEvaluation> query = new BmobQuery();
        AppUser user=AppUser.getCurrentUser(AppUser.class);
        final String usernameStr=user.getUserName();
        Log.i("信息123",usernameStr);

        Log.i("信息123",usercourseStr);
        query.addWhereEqualTo("evaluate_tea_name", usernameStr);
        query.addWhereEqualTo("evaluate_tea_course", usercourseStr);
        query.findObjects(new FindListener<AEvaluation>() {
            @Override
            public void done(List<AEvaluation> list, BmobException e) {
                if (e == null) {
                    for (AEvaluation eval : list) {
                        String eva_score=eval.getAvg_score();
                        search_score.append(eva_score);
                        String eva_num=eval.getEvl_actual_num();
                        search_elv_num.append(eva_num);
                        String eva_rate=eval.getEvl_rate();
                        search_eva_rate.append(eva_rate);
                        //course =eval.getEvaluate_tea_course();
                        //search_course_name.append(course);
//                        score1=eval.getProject1_score();
//                        search_score1_tv.append(String.valueOf(score1));
//                        aver_score();
//                        Log.i("信息123", String.valueOf(score1));
//                        score2=eval.getProject2_score();
//                        search_score2_tv.append(String.valueOf(score2));
//                        Log.i("信息123",String.valueOf(score2));
//                        score3=eval.getProject3_score();
//                        search_score3_tv.append(String.valueOf(score3));
//                        score4=eval.getProject4_score();
//                        search_score4_tv.append(String.valueOf(score4));
                        Toast.makeText(SearchevaluationAty.this,"查询成功!",Toast.LENGTH_SHORT).show();
                        tea_searevl_next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(SearchevaluationAty.this,SearchsuggestionAty.class);
                                intent.putExtra("tea",usernameStr);
                                intent.putExtra("cou",usercourseStr);
                                startActivity(intent);
                            }
                        });

                    }

                } else {
                    Toast.makeText(SearchevaluationAty.this,"查询失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private  void aver_score(){
        BmobQuery bmobQuery=new BmobQuery();
        bmobQuery.average(new String[]{"project1_score"});
        bmobQuery.findStatistics(Evaluation.class, new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {
                if(e==null){
                    JSONArray ary=jsonArray;
                    if(ary!=null){
                        try {
                            JSONObject obj=ary.getJSONObject(0);
                            int avg_score1=obj.getInt("_avgproject1_score");
                            Log.i("数据：", String.valueOf(avg_score1));
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }else {
                        Toast.makeText(SearchevaluationAty.this,"求平均失败",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
    @Override
    public void onClick(View view) {


    }
}
