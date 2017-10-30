package com.example.dm.zhbit.administrator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.Suggestion;
import adapter.SuggestionAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2017/4/21.
 */

public class Adm_SuggestAty extends Activity implements View.OnClickListener{
    private ListView sglist;
    private ArrayList<Suggestion> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm_suggestion_control);

        queryAllSuggestion();
    }

    @Override
    public void onClick(View view) {

    }

    private void queryAllSuggestion() {
        final BmobQuery<Suggestion> query = new BmobQuery();
                    query.findObjects(new FindListener<Suggestion>() {
                        @Override
                        public void done (List< Suggestion> list, BmobException e){
                            if (e == null) {
                                for (Suggestion sg: list) {
                                    sg.getComment_tea();
                                    sg.getComment_course();
                                    sg.getSuggestionContent();
                                    datas.add(sg);
                                    Toast.makeText(Adm_SuggestAty.this,"成功查询",Toast.LENGTH_SHORT).show();;
                                }
                                sglist= (ListView) findViewById(R.id.a_suggest_list);
                                final SuggestionAdapter adapter = new SuggestionAdapter(Adm_SuggestAty.this, R.layout.adm_suggest_item, datas);
                                sglist.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
//                    sglist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//                        @Override
//                        public boolean onItemLongClick(AdapterView<?> parent, View view,
//                                                       final int position, long id) {
//                            //定义AlertDialog.Builder对象，当长按列表项的时候弹出确认删除对话框
//                            AlertDialog.Builder builder=new AlertDialog.Builder(Adm_SuggestAty.this);
//                            builder.setMessage("确定删除?");
//                            builder.setTitle("提示");
//
//                            //添加AlertDialog.Builder对象的setPositiveButton()方法
//                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    if( list.remove() !=null){
//                                        System.out.println("success");
//                                    }else {
//                                        System.out.println("failed");
//                                    }
//                                    adapter.notifyDataSetChanged();
//                                    Toast.makeText(getBaseContext(), "删除列表项", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//
//                            //添加AlertDialog.Builder对象的setNegativeButton()方法
//                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//
//                                }
//                            });
//
//                            builder.create().show();
//                            return false;
//                        }
//                    });

                }
                else{
                    Toast.makeText(Adm_SuggestAty.this,"查询失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
