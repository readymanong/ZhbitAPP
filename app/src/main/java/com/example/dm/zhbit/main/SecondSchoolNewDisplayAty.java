package com.example.dm.zhbit.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.dm.zhbit.R;

/**
 *
 */

public class SecondSchoolNewDisplayAty extends AppCompatActivity {

    private String newsUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schoolnews_display);
        newsUrl = getIntent().getStringExtra("news_url");
        WebView webView = (WebView) findViewById(R.id.sweb_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setInitialScale(100);
        webView.loadUrl(newsUrl);


    }
}
