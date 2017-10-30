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
import com.example.dm.zhbit.beans.Student;
import adapter.StudentAdapter;


import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/4/13.
 */

public class Adm_StuControlAty extends Activity implements View.OnClickListener {
    private EditText ainsert_sid, ainsert_sname, ainsert_ssex, ainsert_smajor, ainsert_sclass, a_sid;
    private Button adm_sinsert_btn, adm_srest_btn, adm_supdate_btn, adm_sselect_btn, adm_sdelete_btn;
    private ListView adm_stu_lv;
    private ArrayList<Student> datas = new ArrayList<>();
    private Student student;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm_stu_control);
        adapter = new StudentAdapter(Adm_StuControlAty.this, R.layout.student_item, datas);
        initView();
        Deal();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        ainsert_sid = (EditText) findViewById(R.id.ainsert_sid);
        ainsert_sname = (EditText) findViewById(R.id.ainsert_sname);
        ainsert_ssex = (EditText) findViewById(R.id.ainsert_ssex);
        ainsert_smajor = (EditText) findViewById(R.id.ainsert_smajor);
        ainsert_sclass = (EditText) findViewById(R.id.ainsert_sclass);
        a_sid = (EditText) findViewById(R.id.a_sid);

        // 曾删改查
        adm_sinsert_btn = (Button) findViewById(R.id.adm_sinsert_btn);
        adm_srest_btn = (Button) findViewById(R.id.adm_srest_btn);
        adm_supdate_btn = (Button) findViewById(R.id.adm_supdate_btn);
        adm_sselect_btn = (Button) findViewById(R.id.adm_sselect_btn);
        adm_sdelete_btn = (Button) findViewById(R.id.adm_sdelete_btn);
        adm_stu_lv = (ListView) findViewById(R.id.adm_stu_lv);
    }

    private void Deal() {
        adm_sinsert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });

        adm_sselect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                queryAllStudent();
            }
        });
        adm_srest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
        adm_sdelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
        adm_supdate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatedata();
            }
        });
    }


    @Override
    public void onClick(View v) {
        queryAllStudent();
        adapter.notifyDataSetChanged();
    }

    private void insert() {
        String SidStr = ainsert_sid.getText().toString().trim();
        String SnameStr = ainsert_sname.getText().toString().trim();
        String SsexStr = ainsert_ssex.getText().toString().trim();
        String SmajorStr = ainsert_smajor.getText().toString().trim();
        String SclassStr = ainsert_sclass.getText().toString().trim();
        if ("".equals(SidStr) || SidStr == null) {
            Toast.makeText(Adm_StuControlAty.this, "请填写学号", Toast.LENGTH_SHORT).show();
        } else if ("".equals(SnameStr) || SnameStr == null) {
            Toast.makeText(Adm_StuControlAty.this, "请填写名字", Toast.LENGTH_SHORT).show();
        } else if ("".equals(SsexStr) || SsexStr == null) {
            Toast.makeText(Adm_StuControlAty.this, "请填写性别", Toast.LENGTH_SHORT).show();
        } else if ("".equals(SmajorStr) || SmajorStr == null) {
            Toast.makeText(Adm_StuControlAty.this, "请填写专业", Toast.LENGTH_SHORT).show();
        } else if ("".equals(SclassStr) || SclassStr == null) {
            Toast.makeText(Adm_StuControlAty.this, "请填写班级", Toast.LENGTH_SHORT).show();
        } else {
            student = new Student(SidStr,SnameStr,SsexStr,SmajorStr,SclassStr);
            student.setStunum(SidStr);
            student.setStuname(SnameStr);
            student.setStusex(SsexStr);
            student.setStumajor(SmajorStr);
            student.setStuclass(SclassStr);
            student.save(new SaveListener<String>() {
                @Override
                public void done(String teacherMessage, BmobException e) {
                    if (e == null) {
                        Toast.makeText(Adm_StuControlAty.this, "添加成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Adm_StuControlAty.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void reset() {
        ainsert_sid.setText("");
        ainsert_sname.setText("");
        ainsert_ssex.setText("");
        ainsert_smajor.setText("");
        ainsert_sclass.setText("");
    }


    // // 删除某一行需要填写id
    private void delete() {
        BmobQuery<Student> bmobQuery = new BmobQuery();
        String a_sidStr = a_sid.getText().toString().trim();
        if ("".equals(a_sidStr) || a_sidStr == null) {
            Toast.makeText(Adm_StuControlAty.this, "请填写要删除的学号", Toast.LENGTH_SHORT).show();
        }
        bmobQuery.addWhereEqualTo("stunum", a_sidStr);
        bmobQuery.findObjects(new FindListener<Student>() {
            @Override
            public void done(List<Student> list, BmobException e) {
                if (e == null) {
                    for (Student stu : list) {
                        String ObjectIdStr = stu.getObjectId();
                        Student student = new Student();
                        student.delete(ObjectIdStr, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Toast.makeText(Adm_StuControlAty.this, "删除成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Adm_StuControlAty.this, "删除失败", Toast.LENGTH_SHORT).show();
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
        BmobQuery<Student> bmobQuery = new BmobQuery();
        String a_sidStr = a_sid.getText().toString().trim();
        final String SidStr = ainsert_sid.getText().toString().trim();
        final String SnameStr = ainsert_sname.getText().toString().trim();
        final String SsexStr = ainsert_ssex.getText().toString().trim();
        final String SmajorStr = ainsert_smajor.getText().toString().trim();
        final String SclassStr = ainsert_sclass.getText().toString().trim();
        if ("".equals(a_sidStr) || a_sidStr == null) {
            Toast.makeText(Adm_StuControlAty.this, "请填写修改的学号", Toast.LENGTH_SHORT).show();
        } else {
            bmobQuery.addWhereEqualTo("stunum", a_sidStr);
            bmobQuery.findObjects(new FindListener<Student>() {
                @Override
                public void done(List<Student> list, BmobException e) {
                    if (e == null) {
                        for (Student stu : list) {
                            String ObjectIdStr = stu.getObjectId();
                            Log.i("修改Id",ObjectIdStr);
                            Student student = new Student();
                            student.setStunum(SidStr);
                            student.setStuname(SnameStr);
                            student.setStusex(SsexStr);
                            student.setStumajor(SmajorStr);
                            student.setStuclass(SclassStr);
                            Log.i("修改Id",SidStr+","+SnameStr+","+SsexStr+","+SmajorStr+","+SclassStr);
                            student.update(ObjectIdStr, new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        Toast.makeText(Adm_StuControlAty.this, "更新成功", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Adm_StuControlAty.this, "更新失败", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    } else {
                        Toast.makeText(Adm_StuControlAty.this, "查询失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    // 显示所有数据
    private void queryAllStudent() {
        datas.clear();
        final BmobQuery<Student> query = new BmobQuery<Student>();
        query.findObjects(new FindListener<Student>() {
            @Override
            public void done(List<Student> list, BmobException e) {
                if (e == null) {
                   // for (Student stu : list)
                    for(int i=0;i<list.size();i++){
                        Student stu=list.get(i);
                        stu.getStunum();
                        stu.getStuname();
                        stu.getStusex();
                        stu.getStumajor();
                        stu.getStuclass();
                        datas.add(stu);
                        Toast.makeText(Adm_StuControlAty.this, "成功查询", Toast.LENGTH_SHORT).show();
                    }

                    adapter.notifyDataSetChanged();
                    adm_stu_lv.setAdapter(adapter);



                } else {
                    Toast.makeText(Adm_StuControlAty.this, "查询失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

