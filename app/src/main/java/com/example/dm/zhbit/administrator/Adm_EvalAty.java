package com.example.dm.zhbit.administrator;

import android.app.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import android.widget.Toast;

import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.Course;
import com.example.dm.zhbit.beans.Evaluation;
import com.example.dm.zhbit.beans.SelectCourse;
import com.example.dm.zhbit.main.MainActivity;


import adapter.EvaluationAdpter;


import java.util.ArrayList;



import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;

import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/4/21.
 */

public class Adm_EvalAty extends Activity implements View.OnClickListener{
    private ListView evalist;
    String ObjectIdStr;
//    Button analy_btn;
    private List<Evaluation> datas = new ArrayList<Evaluation>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm_evaluate_control);
        iniView();
                queryAllEvaluation();

//       analy_btn.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View view) {
//               startActivity(new Intent(Adm_EvalAty.this, Adm_Eval_AnalyAty.class));
//           }
//       });

    }

    @Override
    public void onClick(View v) {

    }
    private void iniView(){
//        analy_btn= (Button) findViewById(R.id.analy_btn);
    }
    private void queryAllEvaluation() {
        BmobQuery<Evaluation> query = new BmobQuery();
        query.findObjects(new FindListener<Evaluation>() {
            @Override
            public void done (List< Evaluation> list, BmobException e){
                if (e == null) {
                    for (Evaluation eva : list) {

                            eva.getEvaluate_tea_name();
                            eva.getEvaluate_tea_course();
                        datas.add(eva);
                        Log.i("集合：", String.valueOf(datas));
                            Toast.makeText(Adm_EvalAty.this, "成功查询", Toast.LENGTH_SHORT).show();
                        }
                        evalist = (ListView) findViewById(R.id.a_eva_list);
                        EvaluationAdpter adapter = new EvaluationAdpter(Adm_EvalAty.this, R.layout.adm_eva_item, (ArrayList<Evaluation>) datas);
                        evalist.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        evalist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                final Evaluation ela = datas.get(i);
                                final BmobQuery<Evaluation> bmobQuery = new BmobQuery();

                                ObjectIdStr =String.valueOf(i+"");

                                bmobQuery.addWhereEqualTo("evaluate_tea_course", ela.getEvaluate_tea_course());
                                bmobQuery.addWhereEqualTo("evaluate_tea_name", ela.getEvaluate_tea_name());
                                bmobQuery.findObjects(new FindListener<Evaluation>() {
                                    @Override
                                    public void done(List<Evaluation> list, BmobException e) {
                                        if (e == null) {bmobQuery.count(Evaluation.class, new CountListener() {
                                            @Override
                                            public void done(final Integer integer, BmobException e) {
                                                if(e==null){

                                                    Log.i("总数", String.valueOf(integer));

                                                        Log.i("数目：", "姓名：" + ela.getEvaluate_tea_name() + "课程：" + ela.getEvaluate_tea_course());
//
                                                        AlertDialog.Builder builder = new AlertDialog.Builder(Adm_EvalAty.this);
                                                        builder.setTitle("详细信息");
                                                        builder.setItems(new String[]{"姓名：" + ela.getEvaluate_tea_name(),
                                                                "课程：" + ela.getEvaluate_tea_course(),
//
                                                        }, null);

                                            builder.setPositiveButton("查看", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {


                                                    BmobQuery<SelectCourse> bq1=new BmobQuery<>();
                                                    bq1.addWhereEqualTo("select_tea",ela.getEvaluate_tea_name());
                                                    bq1.addWhereEqualTo("select_cou",ela.getEvaluate_tea_course());
                                                    bq1.count(SelectCourse.class, new CountListener() {
                                                        @Override
                                                        public void done(Integer planNum, BmobException e) {
                                                            Log.i("计划数：", String.valueOf(planNum));
                                                            //String planNumStr=String.valueOf(planNum);
                                                            if (e==null) {
                                                                Intent intent = new Intent(Adm_EvalAty.this, Adm_Eval_AnalyAty.class);
                                                                intent.putExtra("e_tea", ela.getEvaluate_tea_name());
                                                                intent.putExtra("e_course", ela.getEvaluate_tea_course());
                                                                intent.putExtra("e_atual_num", String.valueOf(integer));
                                                                intent.putExtra("e_planNum", String.valueOf(planNum));
                                                                startActivity(intent);
                                                            }
                                                        }
                                                    });


                                                }
                                            }).setNegativeButton("删除", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {
//                                                            dialogInterface.dismiss();

                                                             ela.delete(ObjectIdStr, new UpdateListener() {
                                                                @Override
                                                                public void done(BmobException e) {
                                                                    if(e==null){
                                                                        Toast.makeText(Adm_EvalAty.this,"删除成功",Toast.LENGTH_SHORT).show();
                                                                    }
                                                                    else{
                                                                        Toast.makeText(Adm_EvalAty.this,"删除失败",Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }
                                                            });
                                                            //Adm_EvalAty.this.finish();
                                                        }
                                                    });
                                                    builder.create().show();
                                        }
                                    }
                                });  }
                                                }
                                            });

                                        }
                        });
                }
                else{
                    Toast.makeText(Adm_EvalAty.this,"查询失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
