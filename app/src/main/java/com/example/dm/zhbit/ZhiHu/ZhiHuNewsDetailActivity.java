package com.example.dm.zhbit.ZhiHu;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.example.dm.zhbit.R;
import com.example.dm.zhbit.beans.News;
import com.example.dm.zhbit.utiltools.SystemUtils;


public class ZhiHuNewsDetailActivity extends Activity {
    private RelativeLayout mBackRout;
    private WebView mWebView;
    private News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_news_detail);

        initView();
        eventDeal();
    }

    private void initView() {
        mBackRout = (RelativeLayout) findViewById(R.id.home_detail_back_rout);
        mWebView = (WebView) findViewById(R.id.webview);
        setWebView(mWebView);
        news = (News) getIntent().getSerializableExtra("news");
        new ZhiHuLoadNewsDetailTask(mWebView).execute(news.getId());
    }

    private void eventDeal() {
        mBackRout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZhiHuNewsDetailActivity.this.finish();
            }
        });
    }

    private void setWebView(WebView mWebView) {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
    }

    public static void startActivity(Context context, News news) {
        if (SystemUtils.checkNetworkConnection(context)) {
            Intent i = new Intent(context, ZhiHuNewsDetailActivity.class);
            i.putExtra("news", news);
            context.startActivity(i);
        } else {
            SystemUtils.noNetworkAlert(context);
        }
    }
}