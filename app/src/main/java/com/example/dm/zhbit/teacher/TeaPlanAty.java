package com.example.dm.zhbit.teacher;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.TeaPlan;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by Administrator on 2017/4/11.
 */

public class TeaPlanAty extends Activity implements View.OnClickListener{
    private Button btn_saveplan,btn_uploadfile,btn_downloadfile;
    private EditText tv,et_teacourse,et_teaplan,et_teaname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teaplan);
        initView();
        Deal();
    }
private void initView(){
    btn_saveplan= (Button) findViewById(R.id.btn_saveplan);
    btn_uploadfile= (Button) findViewById(R.id.btn_uploadfile);
    btn_downloadfile= (Button) findViewById(R.id.btn_downloadfile);

    et_teacourse= (EditText) findViewById(R.id.et_teacourse);
    et_teaplan= (EditText) findViewById(R.id.et_teaplan);
    et_teaname= (EditText) findViewById(R.id.et_teaname);
}
    private void Deal(){
        btn_saveplan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String teanameStr =  et_teaname.getText().toString().trim();
                String teacourseStr =  et_teacourse.getText().toString().trim();
                String teaplanStr = et_teaplan.getText().toString().trim();
                if ("".equals(teanameStr) || teanameStr == null) {
                    Toast.makeText(TeaPlanAty.this, "请填写教课老师", Toast.LENGTH_SHORT).show();
                } else if ("".equals(teacourseStr) || teacourseStr == null) {
                    Toast.makeText(TeaPlanAty.this, "请填写课程名字", Toast.LENGTH_SHORT).show();
                } else if ("".equals(teaplanStr) || teaplanStr == null) {
                    Toast.makeText(TeaPlanAty.this, "请填写教学方案", Toast.LENGTH_SHORT).show();
                }  else {
                    TeaPlan tp= new TeaPlan();
                    tp.setTea_name(teanameStr);
                    tp.setTeacourse_name(teacourseStr);
                    tp.setTeaplan(teaplanStr);
                    tp.save(new SaveListener<String>() {
                        @Override
                        public void done(String tp, BmobException e) {
                            if (e == null) {
                                Toast.makeText(TeaPlanAty.this, "提交成功!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(TeaPlanAty.this, "提交失败!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
            }
        });
        btn_uploadfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload();
            }
        });

    }
    private void upload(){
//String txtPath="file:///android_asset//TeaPlan.txt";
//        File path = Environment.getExternalStorageDirectory() ; //获得SDCard目录
//        String txtPath=Environment.getExternalStorageDirectory().toString()+"/DCIM/TeaPlan.txt";
//        final BmobFile bmobFile = new BmobFile(new File(txtPath));
//        bmobFile.uploadblock(new UploadFileListener() {
//
//            @Override
//            public void done(BmobException e) {
//                if(e==null){
//                    //bmobFile.getFileUrl()--返回的上传文件的完整地址
//                    Toast.makeText(TeaPlanAty.this,"上传文件成功:"+ bmobFile.getFileUrl(),Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(TeaPlanAty.this,"上传文件失败:"+ e.getMessage(),Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onProgress(Integer value) {
//                // 返回的上传进度（百分比）
//            }
//        });
    }


                    @Override
                    public void onClick (View view){

                    }
                }
