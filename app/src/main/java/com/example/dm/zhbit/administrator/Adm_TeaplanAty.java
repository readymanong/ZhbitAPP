package com.example.dm.zhbit.administrator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.TeaPlan;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2017/4/12.
 */

public class Adm_TeaplanAty extends Activity implements View.OnClickListener{
    TextView tp_content;
    EditText a_tname,a_cname;
    Button adm_tpselect_btn,adm_tpdelete_btn;
    TeaPlan tp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm_teaplan_control);
        initView();
        adm_tpselect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select();
            }
        });
        Deal();
    }

    private void initView(){
        tp_content= (TextView) findViewById(R.id.tp_content);
        a_tname= (EditText) findViewById(R.id.a_tname);
        a_cname= (EditText) findViewById(R.id.a_tname);
        adm_tpselect_btn= (Button) findViewById(R.id.adm_tpselect_btn);
        adm_tpdelete_btn= (Button) findViewById(R.id.adm_tpdelete_btn);
    }
    private void Deal(){
//        adm_tpselect_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                select();
//            }
//        });
        adm_tpdelete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
    }

private void select(){
    BmobQuery<TeaPlan> bmobQuery = new BmobQuery();
    final String tnameStr=a_tname.getText().toString().trim();
    final String cnameStr=a_cname.getText().toString().trim();
    if("".equals(tnameStr)||tnameStr==null){
        Toast.makeText(Adm_TeaplanAty.this,"请输入教师名",Toast.LENGTH_SHORT).show();
    }else if("".equals(cnameStr)||cnameStr==null){
        Toast.makeText(Adm_TeaplanAty.this,"请输入课程",Toast.LENGTH_SHORT).show();
    }
    bmobQuery.addWhereEqualTo("tea_name", tnameStr );
    bmobQuery.addWhereEqualTo("teacourse_name",cnameStr);
    bmobQuery.findObjects(new FindListener<TeaPlan>() {
        @Override
        public void done(List<TeaPlan> list, BmobException e) {
            if(e==null){
                for(TeaPlan tp:list){
                   // String ObjectIdStr = tp.getObjectId();
//                for(int i=0;i<list.size();i++){
                String tpStr =tp.getTeaplan();
                    tp_content.append(tpStr);
                    Log.i("内容：",tpStr);
                    Toast.makeText(Adm_TeaplanAty.this,"成功查询",Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(Adm_TeaplanAty.this,"成功失败",Toast.LENGTH_SHORT).show();
            }
        }
    });
}
    private void delete(){
        BmobQuery<TeaPlan> bmobQuery = new BmobQuery();
        String tnameStr=a_tname.getText().toString().trim();
        String cnameStr=a_cname.getText().toString().trim();
        if("".equals(tnameStr)||tnameStr==null){
            Toast.makeText(Adm_TeaplanAty.this,"请输入教师名",Toast.LENGTH_SHORT).show();
        }else if("".equals(cnameStr)||cnameStr==null){
            Toast.makeText(Adm_TeaplanAty.this,"请输入课程",Toast.LENGTH_SHORT).show();
        }
        bmobQuery.addWhereEqualTo("tea_name", tnameStr );
        bmobQuery.addWhereEqualTo("teacourse_name",cnameStr);
        bmobQuery.findObjects(new FindListener<TeaPlan>() {
            @Override
            public void done(List<TeaPlan> list, BmobException e) {
                if(e==null){
                    for (TeaPlan c : list){
                        String ObjectIdStr =c.getObjectId();
                       TeaPlan tp=new TeaPlan();
                        tp.delete(ObjectIdStr,new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(Adm_TeaplanAty.this,"删除成功",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(Adm_TeaplanAty.this,"删除失败",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                }
            }
        });
    }
    @Override
    public void onClick(View view) {

    }
}
