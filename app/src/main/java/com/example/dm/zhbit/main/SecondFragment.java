package com.example.dm.zhbit.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.SchoolNews;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.SchoolNewAdapter;


/**
 * 第二个页面
 */
public class SecondFragment extends Fragment {
    private View view;
    private List<SchoolNews> newsList;
    private SchoolNewAdapter adapter;
    private Handler handler;
    private ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg2, container, false);
        newsList = new ArrayList<>();
        lv = (ListView) view.findViewById(R.id.schoolnews_lv);
        getSchoolNews();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 1){
                    adapter = new SchoolNewAdapter(getActivity(),newsList);
                    lv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            SchoolNews news = newsList.get(position);
                            Intent intent = new Intent(getActivity(),SecondSchoolNewDisplayAty.class);
                            intent.putExtra("news_url",news.getNewsUrl());
                            startActivity(intent);
                        }
                    });
                }
            }
        };
        return view;
    }

    private void getSchoolNews(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for(int i = 1;i<=20;i++) {
                        Document doc = Jsoup.connect("http://computer.xy.zhbit.com/xwzx/xyxw/" ).get();
                        Elements titleLinks = doc.getElementsByAttributeValue("class","list_right_middle");    //解析来获取每条新闻的标题与链接地址
                        for(Element listEle:titleLinks) {
                            Elements listName = listEle.getElementsByTag("li");
                            for (Element element : listName) {
                                String title = element.getElementsByTag("a").attr("title");
                                Log.i("数据：", title);
                                String uri1= element.getElementsByTag("a").attr("href");
                                String uri="http://computer.xy.zhbit.com"+ uri1;
                                Log.i("数据：", uri);
                                String time = element.select("span").text();
                                Log.i("数据：",time);
                                SchoolNews news = new SchoolNews(title, uri,time);
                                newsList.add(news);

                            }
                        }
                    }
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

}

