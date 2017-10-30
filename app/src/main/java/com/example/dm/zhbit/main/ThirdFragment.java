package com.example.dm.zhbit.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import android.widget.SimpleAdapter;
import android.widget.Toast;


import com.example.dm.zhbit.R;
import com.example.dm.zhbit.administrator.Adm_MainAty;
import com.example.dm.zhbit.beans.AppUser;
import com.example.dm.zhbit.student.SearchcourseAty;
import com.example.dm.zhbit.student.SearchteacherAty;
import com.example.dm.zhbit.student.SelectCourseAty;
import com.example.dm.zhbit.student.StudentEvaluateAty;
import com.example.dm.zhbit.student.StudentSuggestionAty;
import com.example.dm.zhbit.teacher.SearchevaluationAty;
import com.example.dm.zhbit.teacher.SearchsuggestionAty;
import com.example.dm.zhbit.teacher.TeaPlanAty;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import cn.bmob.v3.BmobUser;

import static com.example.dm.zhbit.R.layout.fg3;


/**
 * 第三个页面
 */
public class ThirdFragment extends  Fragment implements View.OnClickListener{

    private View view;
    private Button teacher_btn;
    private Button student_btn;
    GridView gvInfo;
    private String Stu="Student";
    private String Tea="Teacher";
    private String Adm="Admin";

    public String[] titles = {"查看老师信息", "查看课程信息", "选  课", "教学评分",
            "教学建议", "查看评分", "查看建议", "改善教学方案","管理员入口"};

    public int[] images = {R.drawable.teacher,
            R.drawable.course_education,
            R.drawable.leather_book,
            R.drawable.group_prog_brack,
            R.drawable.ic_video_collection_brack600_36dp,
            R.drawable.group_prog,
            R.drawable.ic_video_collection_grey600_36dp,
            R.drawable.ic_message_grey600_24dp,
            R.drawable.adm};
            AppUser u=BmobUser.getCurrentUser(AppUser.class);
            String rolestr=u.getUserRole();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(fg3, container, false);

        if(u==null){
           // ImageView thirdImage = (ImageView) view.findViewById(R.id.third_image);
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }else{
            deal();
        }

        return view;
    }


    @Override
    public void onClick(View view) {

    }

    private void deal() {
        gvInfo = (GridView) view.findViewById(R.id.gv);
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        int length = images.length;
        for (int i = 0; i < length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImageView", images[i]);
            map.put("ItemTextView", titles[i]);
            data.add(map);
        }
        //为itme.xml添加适配器
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),
                data, R.layout.gvitem, new String[]{"ItemImageView", "ItemTextView"}, new int[]{R.id.ItemImage, R.id.ItemTile});
        gvInfo.setAdapter(simpleAdapter);
        //为mGridView添加点击事件监听器
        gvInfo.setOnItemClickListener(new GridViewItemOnClick());
    }




    //定义点击事件监听器
    public class GridViewItemOnClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
            switch (position){
                case 0:
                    if(rolestr.equals(Stu)){
                    startActivity(new Intent(getActivity(), SearchteacherAty.class));}
                    else {
                        Toast.makeText(getActivity(),"无权限进入",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 1:
                    if(rolestr.equals(Stu)){
                        startActivity(new Intent(getActivity(), SearchcourseAty.class));}
                    else {
                        Toast.makeText(getActivity(),"无权限进入",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    if(rolestr.equals(Stu)){
                        startActivity(new Intent(getActivity(), SelectCourseAty.class));}
                    else {
                        Toast.makeText(getActivity(),"无权限进入",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 3:
                    if(rolestr.equals(Stu)){
                        startActivity(new Intent(getActivity(), StudentEvaluateAty.class));}
                    else {
                        Toast.makeText(getActivity(),"无权限进入",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 4:

                    if(rolestr.equals(Stu)){
                        startActivity(new Intent(getActivity(), StudentSuggestionAty.class));}
                    else {
                        Toast.makeText(getActivity(),"无权限进入",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 5:
                    if(rolestr.equals(Tea)){
                        startActivity(new Intent(getActivity(), SearchevaluationAty.class));}
                    else {
                        Toast.makeText(getActivity(),"无权限进入",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 6:
                    if(rolestr.equals(Tea)){
//                        startActivity(new Intent(getActivity(),SearchsuggestionAty.class));
}
                    else {
                        Toast.makeText(getActivity(),"无权限进入",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 7:
                    if(rolestr.equals(Tea)){
                        startActivity(new Intent(getActivity(), TeaPlanAty.class));}
                    else {
                        Toast.makeText(getActivity(),"无权限进入",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 8:

                    if(rolestr.equals(Adm)){
                        startActivity(new Intent(getActivity(),Adm_MainAty.class));}
                    else {
                        Toast.makeText(getActivity(),"无权限进入",Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;

            }
        }
    }
}
