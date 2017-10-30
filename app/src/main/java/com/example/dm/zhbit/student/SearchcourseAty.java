package com.example.dm.zhbit.student;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
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

/**
 * Created by Administrator on 2017/3/22.
 */

public class SearchcourseAty extends Activity implements View.OnClickListener {
    private ListView listView;
    private ArrayList<Course> datas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_course);
        new Thread(new Runnable() {
            @Override
            public void run() {
                queryAllCourse();
            }
        }).start();
    }

    @Override
    public void onClick(View view) {

    }

    private void queryAllCourse() {
        final BmobQuery<Course> query = new BmobQuery<Course>();
        query.findObjects(new FindListener<Course>() {
            @Override
            public void done (List< Course > list, BmobException e){
                if (e == null) {
                    for (Course c : list) {
                        c.getCname();
                        c.getCtea();
                        c.getCcontent();
                        datas.add(c);
                        //Log.i("数据",list.toString());
                        Toast.makeText(SearchcourseAty.this,"成功查询",Toast.LENGTH_SHORT).show();
                    }
                    listView = (ListView) findViewById(R.id.course_list);
                   CourseAdapter adapter = new CourseAdapter(SearchcourseAty.this, R.layout.search_course_item, datas);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(SearchcourseAty.this,"查询失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}


