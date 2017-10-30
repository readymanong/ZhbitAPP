package com.example.dm.zhbit.administrator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.TeacherMessage;
import adapter.TeachersAdapter;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class Adm_TeaControlAty extends Activity implements View.OnClickListener{
private EditText insert_tid,insert_tname,insert_tsex,insert_tcourse,insert_tintroduce,a_tid;
    private Button adm_insert_teacher,adm_update_teacher,adm_select_teacher,adm_delete_teacher,adm_rest;
    private ListView adm_tea_lv;
    private ArrayList<TeacherMessage> datas = new ArrayList<>();
    private TeacherMessage teacherMessage;
    private TeachersAdapter adapter;
     private TeachersAdapter adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm_tea_control);
        initView();
        adapter1 = new TeachersAdapter(Adm_TeaControlAty.this, R.layout.search_teamessage_item, datas);
        Deal();


    }

    /**
     * 初始化控件
     */
 private void initView() {
     insert_tid = (EditText) findViewById(R.id.ainsert_tid);
     insert_tname = (EditText) findViewById(R.id.ainsert_tname);
     insert_tsex = (EditText) findViewById(R.id.ainsert_tsex);
     insert_tcourse = (EditText) findViewById(R.id.ainsert_tcourse);
     insert_tintroduce = (EditText) findViewById(R.id.ainsert_tintroduce);
     a_tid = (EditText) findViewById(R.id.a_tid );

        // 曾删改查
     adm_insert_teacher= (Button) findViewById(R.id.adm_insert_btn);
     adm_update_teacher= (Button) findViewById(R.id.adm_update_btn);
     adm_select_teacher = (Button) findViewById(R.id.adm_select_btn);
     adm_delete_teacher= (Button) findViewById(R.id.adm_delete_btn);
     adm_rest = (Button) findViewById(R.id.adm_rest_btn);

     adm_tea_lv = (ListView) findViewById(R.id.adm_tea_lv);

    }

private void Deal(){
    adm_insert_teacher.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            insert();
        }
    });

    adm_select_teacher.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                queryAllTeacher();
        }
    });
    adm_rest.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            reset();
        }
    });
    adm_delete_teacher.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            delete();
        }
    });
    adm_update_teacher.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            updatedata();
        }
    });
}


    @Override
    public void onClick(View v) {

    }
private void insert(){
    String TidStr=insert_tid.getText().toString().trim();
    String TnameStr=insert_tname.getText().toString().trim();
    String TsexStr=insert_tsex.getText().toString().trim();
    String TcourseStr=insert_tcourse.getText().toString().trim();
    String TintroduceStr=insert_tintroduce.getText().toString().trim();
    if ("".equals(TidStr) || TidStr == null) {
        Toast.makeText(Adm_TeaControlAty.this, "请填写工号", Toast.LENGTH_SHORT).show();
    } else if ("".equals(TnameStr) || TnameStr == null) {
        Toast.makeText(Adm_TeaControlAty.this, "请填写教师名字", Toast.LENGTH_SHORT).show();
    } else if ("".equals(TsexStr) || TsexStr == null) {
        Toast.makeText(Adm_TeaControlAty.this, "请填写性别", Toast.LENGTH_SHORT).show();
    }else if ("".equals(TcourseStr) || TcourseStr == null) {
        Toast.makeText(Adm_TeaControlAty.this, "请填写教授课程", Toast.LENGTH_SHORT).show();
    } else if ("".equals(TintroduceStr) || TintroduceStr == null) {
        Toast.makeText(Adm_TeaControlAty.this, "请填写简介", Toast.LENGTH_SHORT).show();
    }  else {
        teacherMessage = new TeacherMessage();
        teacherMessage.setTid(TidStr);
        teacherMessage.setTname(TnameStr);
        teacherMessage.setTsex(TsexStr);
        teacherMessage.setTcourse(TcourseStr);
        teacherMessage.setTintroduce(TintroduceStr);
        teacherMessage.save(new SaveListener<String>() {
            @Override
            public void done(String  teacherMessage, BmobException e) {
                if (e==null){
                    Toast.makeText(Adm_TeaControlAty.this,"添加成功",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Adm_TeaControlAty.this,"添加失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

    private void reset() {
        insert_tid.setText("");
        insert_tname.setText("");
       insert_tsex.setText("");
        insert_tcourse.setText("");
        insert_tintroduce.setText("");
    }


    // // 删除某一行需要填写id
    private void delete() {
       BmobQuery<TeacherMessage> bmobQuery = new BmobQuery();
        String a_tidStr = a_tid.getText().toString().trim();
        if ("".equals(a_tidStr) || a_tidStr == null) {
            Toast.makeText(Adm_TeaControlAty.this, "请填写要删除的工号", Toast.LENGTH_SHORT).show();
        }
        bmobQuery.addWhereEqualTo("tid", a_tidStr);
       bmobQuery.findObjects(new FindListener<TeacherMessage>() {
           @Override
           public void done(List<TeacherMessage> list, BmobException e) {
               if(e==null){
                   for (TeacherMessage tm : list){
                       String ObjectIdStr =tm.getObjectId();
                       TeacherMessage teacherMessage=new TeacherMessage();
                       teacherMessage.delete(ObjectIdStr,new UpdateListener() {
                           @Override
                           public void done(BmobException e) {
                               if(e==null){
                                   Toast.makeText(Adm_TeaControlAty.this,"删除成功",Toast.LENGTH_SHORT).show();
                               }else{
                                   Toast.makeText(Adm_TeaControlAty.this,"删除失败",Toast.LENGTH_SHORT).show();
                               }
                           }
                       });

                   }
               }
           }
       });
    }

    // // 修改操作需要按顺序填写各个字段的内容并用，隔开并且最后要填写修改某一行的id
    private void updatedata() {
        BmobQuery<TeacherMessage> bmobQuery = new BmobQuery();
        String a_tidStr = a_tid.getText().toString().trim();
        final String TidStr = insert_tid.getText().toString().trim();
        final String TnameStr = insert_tname.getText().toString().trim();
        final String TsexStr = insert_tsex.getText().toString().trim();
        final String TcourseStr = insert_tcourse.getText().toString().trim();
        final String TintroduceStr = insert_tintroduce.getText().toString().trim();
        if ("".equals(a_tidStr) || a_tidStr == null) {
            Toast.makeText(Adm_TeaControlAty.this, "请填写修改的工号", Toast.LENGTH_SHORT).show();
        } else {
            bmobQuery.addWhereEqualTo("tid", a_tidStr);
            bmobQuery.findObjects(new FindListener<TeacherMessage>() {
                @Override
                public void done(List<TeacherMessage> list, BmobException e) {
                    if (e == null) {
                        for (TeacherMessage tm : list) {
                            String ObjectIdStr = tm.getObjectId();
                            TeacherMessage teacherMessage = new TeacherMessage();
                            // teacherMessage.setObjectId(ObjectIdStr);
                            teacherMessage.setTid(TidStr);
//                            Log.i("数据:", TidStr);
                            teacherMessage.setTname(TnameStr);
//                            Log.i("数据:", TnameStr);
                            teacherMessage.setTsex(TsexStr);
//                            Log.i("数据:", TsexStr);
                            teacherMessage.setTcourse(TcourseStr);
//                            Log.i("数据:", TcourseStr);
                            teacherMessage.setTintroduce(TintroduceStr);
//                            Log.i("数据:", TintroduceStr);
                            teacherMessage.update(ObjectIdStr, new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        Toast.makeText(Adm_TeaControlAty.this, "更新成功", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Adm_TeaControlAty.this, "更新失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    } else {
                        Toast.makeText(Adm_TeaControlAty.this, "查询失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
/*
    // 按id查询某一行
    private void query() {
        BmobQuery<TeacherMessage> bmobQuery = new BmobQuery();
        String a_tidStr = a_tid.getText().toString().trim();
        if ("".equals(a_tidStr) || a_tidStr == null) {
            Toast.makeText(Adm_TeaControlAty.this, "请填写查询的工号", Toast.LENGTH_SHORT).show();
        }
        bmobQuery.addWhereEqualTo("tid",a_tidStr);
        bmobQuery.findObjects(new FindListener<TeacherMessage>() {
            @Override
            public void done(List<TeacherMessage> list, BmobException e) {
                if (e == null) {
                    for (TeacherMessage tm : list) {
                        tm.getTid();
                        tm.getTname();
                        tm.getTsex();
                        tm.getTcourse();
                        tm.getTintroduce();
                        datas.add(tm);
                        Toast.makeText(Adm_TeaControlAty.this, "成功查询", Toast.LENGTH_SHORT).show();
                    }//
                    adm_tea_lv = (ListView) findViewById(R.id.adm_tea_lv);
                    adapter = new TeachersAdapter(Adm_TeaControlAty.this, R.layout.search_teamessage_item, datas);
                    adm_tea_lv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(Adm_TeaControlAty.this, "查询失败", Toast.LENGTH_SHORT).show();
                }adapter.clear();
            }

        });

    }
*/


    // 显示所有数据
    private void queryAllTeacher() {
        datas.clear();
        final BmobQuery<TeacherMessage> query = new BmobQuery();
        query.findObjects(new FindListener<TeacherMessage>() {
            @Override
            public void done (List< TeacherMessage > list, BmobException e){
                if (e == null) {
                    for (int i =0 ;i<list.size();i++)
                    {
                        TeacherMessage tm = list.get(i);
                        datas.add(tm);
                    }
                    adapter1.notifyDataSetChanged();
                    adm_tea_lv.setAdapter(adapter1);
                }
                else{
                    Toast.makeText(Adm_TeaControlAty.this,"查询失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
