package com.example.dm.zhbit.student;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
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

/**
 * Created by Administrator on 2017/3/22.
 */



public class SearchteacherAty extends Activity implements View.OnClickListener {
    private ListView listView;
    private ArrayList<TeacherMessage> datas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchteacher);
            new Thread(new Runnable() {
                @Override
            public void run() {
                queryAllTeacher();
            }
            }).start();
    }

    @Override
    public void onClick(View view) {

    }

 private void queryAllTeacher() {
        final BmobQuery<TeacherMessage> query = new BmobQuery<TeacherMessage>();
        query.findObjects(new FindListener<TeacherMessage>() {
                @Override
                public void done (List < TeacherMessage > list, BmobException e){
                    if (e == null) {
                        for (TeacherMessage tm : list) {
                            tm.getTid();
                            tm.getTname();
                            tm.getTsex();
                            tm.getTcourse();
                            tm.getTintroduce();
                            Log.i("数据",tm.getTsex()+"."+tm.getTcourse()+"."+tm.getTintroduce());
                            datas.add(tm);

                            Log.i("数据",list.toString());
                                  Toast.makeText(SearchteacherAty.this,"成功查询",Toast.LENGTH_SHORT).show();
                        }
                        listView = (ListView) findViewById(R.id.search_tea_list);
                        TeachersAdapter adapter = new TeachersAdapter(SearchteacherAty.this, R.layout.search_teamessage_item, datas);
                        listView.setAdapter(adapter);
                       adapter.notifyDataSetChanged();

                    }
                    else{
                        Toast.makeText(SearchteacherAty.this,"查询失败",Toast.LENGTH_SHORT).show();
                    }
                }
        });
    }

}
