package com.example.dm.zhbit.administrator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;


import com.example.dm.zhbit.R;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by Administrator on 2017/4/11.
 */

public class Adm_MainAty extends Activity implements View.OnClickListener{

    private View view;
    private Button teacher_btn;
    private Button student_btn;
    GridView agvInfo;
    public String[] titles = {"老师信息", "学生信息","课程信息",  "教学评分",
            "教学建议"};

    public int[] images = {R.drawable.teacher,
            R.drawable.ic_accessibility_grey600_36dp,
            R.drawable.course_education,
            R.drawable.ic_open_with_grey600_36dp,
            R.drawable.ic_video_collection_grey600_36dp,
           };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main);
        deal();
    }

    @Override
    public void onClick(View view) {

    }

    private void deal() {
        agvInfo = (GridView)findViewById(R.id.adm_gv);
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        int length = images.length;
        for (int i = 0; i < length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("AItemImageView", images[i]);
            map.put("AItemTextView", titles[i]);
            data.add(map);
        }
        //为itme.xml添加适配器
        SimpleAdapter simpleAdapter = new SimpleAdapter(Adm_MainAty.this,
                data, R.layout.admin_main_gvitem, new String[]{"AItemImageView", "AItemTextView"}, new int[]{R.id.AItemImage, R.id.AItemTile});
        agvInfo.setAdapter(simpleAdapter);
        simpleAdapter.notifyDataSetChanged();
        //为mGridView添加点击事件监听器
        agvInfo.setOnItemClickListener(new GridViewItemOnClick());
    }
    //定义点击事件监听器
    public class GridViewItemOnClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
            switch (position){
                case 0:
                            startActivity(new Intent(Adm_MainAty.this, Adm_TeaControlAty.class));
                    break;
                case 1:
                    startActivity(new Intent(Adm_MainAty.this, Adm_StuControlAty.class));
                    break;
                case 2:
                    startActivity(new Intent(Adm_MainAty.this, Adm_CourseControlAty.class));
                    break;
                case 3:
                    startActivity(new Intent(Adm_MainAty.this, Adm_EvalAty.class));
                    break;
                case 4:
                    startActivity(new Intent(Adm_MainAty.this, Adm_SuggestAty.class));
                    break;

                case 5:
                   //startActivity(new Intent(Adm_MainAty.this, Adm_TeaplanAty.class));
                    break;
                default:
                    break;

            }
        }
    }
}