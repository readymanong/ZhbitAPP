package com.example.dm.zhbit.administrator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.Course;
import adapter.CourseAdapter;


import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;


/**
 * Created by Administrator on 2017/4/12.
 */

public class Adm_CourseControlAty extends Activity implements View.OnClickListener{
    private EditText ainsert_cname,ainsert_ctea,ainsert_ccontent,a_cname;
    private Button adm_cinsert_btn,adm_crest_btn,adm_cupdate_btn,adm_cselect_btn,adm_cdelete_btn;
    private ListView adm_course_lv;
    private ArrayList<Course> datas = new ArrayList<>();
    private Course course;
    private CourseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm_course_control);
        initView();
        Deal();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        ainsert_cname = (EditText) findViewById(R.id.ainsert_cname);
        ainsert_ctea = (EditText) findViewById(R.id.ainsert_ctea);
        ainsert_ccontent = (EditText) findViewById(R.id.ainsert_ccontent);
        a_cname = (EditText) findViewById(R.id.a_cname);

        // 曾删改查
        adm_cinsert_btn= (Button) findViewById(R.id.adm_cinsert_btn);
        adm_crest_btn= (Button) findViewById(R.id.adm_crest_btn);
        adm_cupdate_btn = (Button) findViewById(R.id.adm_cupdate_btn);
        adm_cselect_btn= (Button) findViewById(R.id.adm_cselect_btn);
        adm_cdelete_btn = (Button) findViewById(R.id.adm_cdelete_btn);
        adm_course_lv = (ListView) findViewById(R.id.adm_course_lv);
    }

    private void Deal() {
       adm_cinsert_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               insert();
           }
       });
        adm_cselect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adapter = new CourseAdapter(Adm_CourseControlAty.this, R.layout.search_course_item, datas);
                queryAllTeacher();
            }
        });
        adm_cdelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
        adm_cupdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               updatedata();
            }
        });
        adm_crest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });


    }

    private void insert(){
        String CnameStr = ainsert_cname.getText().toString().trim();
         String CteaStr=ainsert_ctea.getText().toString().trim();
         String CcontentStr=ainsert_ccontent.getText().toString().trim();
        if ("".equals(CnameStr) || CnameStr == null) {
            Toast.makeText(Adm_CourseControlAty.this, "请填写课程名", Toast.LENGTH_SHORT).show();
        } else if ("".equals(CteaStr) || CteaStr == null) {
            Toast.makeText(Adm_CourseControlAty.this, "请填写教授教师", Toast.LENGTH_SHORT).show();
        } else if ("".equals(CcontentStr) || CcontentStr == null) {
            Toast.makeText(Adm_CourseControlAty.this, "请填写课程内容", Toast.LENGTH_SHORT).show();
        }
        else {
           course=new Course(CnameStr,CteaStr,CcontentStr);
            course.setCname(CnameStr);
            Log.i("数据：",CnameStr);
            course.setCtea(CteaStr);
            course.setCcontent(CcontentStr);
            course.save(new SaveListener<String>() {
                @Override
                public void done(String course, BmobException e) {
                    if(e==null){
                        Toast.makeText(Adm_CourseControlAty.this,"添加成功",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Adm_CourseControlAty.this,"添加失败",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
    @Override
    public void onClick(View v) {

    }

    private void reset() {
        ainsert_cname.setText("");
        ainsert_ctea.setText("");
        ainsert_ccontent.setText("");

    }

    // // 删除某一行需要填写id
    private void delete() {
        BmobQuery<Course> bmobQuery = new BmobQuery();
        String a_cnameStr = a_cname.getText().toString().trim();
        if ("".equals(a_cnameStr) || a_cnameStr == null) {
            Toast.makeText(Adm_CourseControlAty.this, "请填写要删除的课程名", Toast.LENGTH_SHORT).show();
        }
        bmobQuery.addWhereEqualTo("cname", a_cnameStr);
        bmobQuery.findObjects(new FindListener<Course>() {
            @Override
            public void done(List<Course> list, BmobException e) {
                if(e==null){
                    for (Course c : list){
                        String ObjectIdStr =c.getObjectId();
                        Course course=new Course();
                        course.delete(ObjectIdStr,new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(Adm_CourseControlAty.this,"删除成功",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(Adm_CourseControlAty.this,"删除失败",Toast.LENGTH_SHORT).show();
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
        BmobQuery<Course> bmobQuery = new BmobQuery();
        String a_cnameStr = a_cname.getText().toString().trim();
        final String CnameStr=ainsert_cname.getText().toString().trim();
        final String CteaStr=ainsert_ctea.getText().toString().trim();
        final String CcontentStr=ainsert_ccontent.getText().toString().trim();
        if ("".equals(a_cnameStr) || a_cnameStr == null) {
            Toast.makeText(Adm_CourseControlAty.this, "请填写修改的课程名", Toast.LENGTH_SHORT).show();
        } else {
            bmobQuery.addWhereEqualTo("cname", a_cnameStr);
            bmobQuery.findObjects(new FindListener<Course>() {
                @Override
                public void done(List<Course> list, BmobException e) {
                    if (e == null) {
                        for (Course c : list) {
                            String ObjectIdStr = c.getObjectId();
                            Course course = new Course(CnameStr,CteaStr,CcontentStr);
                            // teacherMessage.setObjectId(ObjectIdStr);
                            course.setCname(CnameStr);
                            course.setCtea(CteaStr);
                            course.setCcontent(CcontentStr);
                            course.update(ObjectIdStr, new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        Toast.makeText(Adm_CourseControlAty.this, "更新成功", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Adm_CourseControlAty.this, "更新失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    } else {
                        Toast.makeText(Adm_CourseControlAty.this, "查询失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
///*
//    // 按id查询某一行
//    private void query() {
//        BmobQuery<TeacherMessage> bmobQuery = new BmobQuery();
//        String a_tidStr = a_tid.getText().toString().trim();
//        if ("".equals(a_tidStr) || a_tidStr == null) {
//            Toast.makeText(Adm_TeaControlAty.this, "请填写查询的工号", Toast.LENGTH_SHORT).show();
//        }
//        bmobQuery.addWhereEqualTo("tid",a_tidStr);
//        bmobQuery.findObjects(new FindListener<TeacherMessage>() {
//            @Override
//            public void done(List<TeacherMessage> list, BmobException e) {
//                if (e == null) {
//                    for (TeacherMessage tm : list) {
//                        tm.getTid();
//                        tm.getTname();
//                        tm.getTsex();
//                        tm.getTcourse();
//                        tm.getTintroduce();
//                        datas.add(tm);
//                        Toast.makeText(Adm_TeaControlAty.this, "成功查询", Toast.LENGTH_SHORT).show();
//                    }//
//                    adm_tea_lv = (ListView) findViewById(R.id.adm_tea_lv);
//                    adapter = new TeachersAdapter(Adm_TeaControlAty.this, R.layout.search_teamessage_item, datas);
//                    adm_tea_lv.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
//                }
//                else {
//                    Toast.makeText(Adm_TeaControlAty.this, "查询失败", Toast.LENGTH_SHORT).show();
//                }adapter.clear();
//            }
//
//        });
//
//    }
//
//*/
//
    // 显示所有数据
    private void queryAllTeacher() {
        datas.clear();
        final BmobQuery<Course> query = new BmobQuery();
        query.findObjects(new FindListener<Course>() {
            @Override
            public void done (List< Course > list, BmobException e){
                if (e == null) {
                    for(int i=0;i<list.size();i++){
                        Course c=list.get(i);
                   // for (Course c : list) {
//                        c.getCname();
//                        c.getCtea();
//                        c.getCcontent();
                        datas.add(c);
                        Toast.makeText(Adm_CourseControlAty.this,"成功查询",Toast.LENGTH_SHORT).show();;
                    }

                    adm_course_lv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }
                else{
                    Toast.makeText(Adm_CourseControlAty.this,"查询失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}

