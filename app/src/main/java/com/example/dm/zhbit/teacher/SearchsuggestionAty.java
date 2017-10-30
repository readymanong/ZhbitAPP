package com.example.dm.zhbit.teacher;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dm.zhbit.R;
import com.example.dm.zhbit.administrator.Adm_SuggestAty;
import com.example.dm.zhbit.beans.Suggestion;
import com.example.dm.zhbit.beans.TeacherMessage;

import java.util.ArrayList;
import java.util.List;

import adapter.SeachSgAdapter;
import adapter.SuggestionAdapter;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


/**
 * Created by Administrator on 2017/3/21.
 */

public class SearchsuggestionAty extends Activity implements View.OnClickListener {
    private View view;
    private Button search_suggestion_btn;
    private ImageButton title_imv;
    private TextView search_suggestion,sg_tea,sg_course;
    private String suggest;
    private ListView lv;
    SeachSgAdapter adapter;
    private ArrayList<Suggestion> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tea_search_suggestion);
        initView();
        search_suggestion_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter=new SeachSgAdapter(SearchsuggestionAty.this,R.layout.tea_search_sg_item,datas);
                querySuggestion();
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    private void initView() {

        lv= (ListView) findViewById(R.id.tea_search_sg_list);
        sg_tea= (TextView) findViewById(R.id.sg_tea);
        sg_course= (TextView) findViewById(R.id.sg_course);
        search_suggestion_btn = (Button) findViewById(R.id.search_suggestion_btn);
        title_imv = (ImageButton) findViewById(R.id.title_imv);
//        search_suggestion = (TextView) findViewById(R.id.search_suggestion);
        String courseStr=getIntent().getStringExtra("cou");
        sg_course.append(courseStr);
    }



   private void querySuggestion() {
       datas.clear();
       final String teaStr=getIntent().getStringExtra("tea");

       final String courseStr=getIntent().getStringExtra("cou");Log.i("数据传递",teaStr+","+courseStr);

       final BmobQuery<Suggestion> query = new BmobQuery();
       query.addWhereEqualTo("comment_course",courseStr);
       query.addWhereEqualTo("comment_tea",teaStr);
       query.findObjects(new FindListener<Suggestion>() {
           @Override
           public void done (List< Suggestion> list, BmobException e){
               if (e == null) {
                       for(int i=0;i<list.size();i++) {
                           Suggestion sg=list.get(i);
                           datas.add(sg);
Log.i("数据", String.valueOf(sg));
                       Toast.makeText(SearchsuggestionAty.this,"成功查询",Toast.LENGTH_SHORT).show();;
               }
                   lv= (ListView) findViewById(R.id.tea_search_sg_list);
                   final SeachSgAdapter adapter = new SeachSgAdapter(SearchsuggestionAty.this, R.layout.tea_search_sg_item, datas);
                   lv.setAdapter(adapter);
                   adapter.notifyDataSetChanged();
//        BmobQuery<Suggestion> query = new BmobQuery<Suggestion>();
//        query.findObjects(new FindListener<Suggestion>() {
//            @Override
//            public void done(List<Suggestion> list, BmobException e) {
//                if (e == null) {
////                    Message message=handler.obtainMessage();
////                    message.what=0;
////                    message.obj=list;
//                    for (Suggestion suggestion : list) {
//                        for(int i=0;i<list.size();i++) {
//                            Suggestion sg=list.get(i);
////                            suggest = suggestion.getSuggestionContent();
////                            search_suggestion.append(suggest);
//                            sg_course.append(courseStr);
//                            sg_tea.append(teaStr);
//                           datas.add(sg);
//                        }suggestionAdapter.notifyDataSetChanged();
//                        lv.setAdapter(suggestionAdapter);
                        Toast.makeText(SearchsuggestionAty.this,"查询成功：共"+list.size()+"条数据。",Toast.LENGTH_SHORT).show();



                } else {
                    Toast.makeText(SearchsuggestionAty.this,"查询失败！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
