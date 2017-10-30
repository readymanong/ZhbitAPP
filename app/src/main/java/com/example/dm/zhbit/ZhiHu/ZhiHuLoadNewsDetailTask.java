package com.example.dm.zhbit.ZhiHu;

import android.os.AsyncTask;
import android.webkit.WebView;

import com.example.dm.zhbit.beans.ZhiHuNewsDetailBeans;
import com.example.dm.zhbit.utiltools.HttpUtil;
import com.example.dm.zhbit.utiltools.JsonHelper;

import org.json.JSONException;

import java.io.IOException;

/**
 * ZhiHuLoadNewsDetailTask
 */
public class ZhiHuLoadNewsDetailTask extends AsyncTask<Integer, Void, ZhiHuNewsDetailBeans> {
    private WebView mWebView;

    public ZhiHuLoadNewsDetailTask(WebView mWebView) {
        this.mWebView = mWebView;
    }

    @Override
    protected ZhiHuNewsDetailBeans doInBackground(Integer... params) {
        ZhiHuNewsDetailBeans mZhiHuNewsDetailBeans = null;
        try {
            mZhiHuNewsDetailBeans = JsonHelper.parseJsonToDetail(HttpUtil.getNewsDetail(params[0]));
        } catch (IOException | JSONException e) {

        } finally {
            return mZhiHuNewsDetailBeans;
        }
    }

    @Override
    protected void onPostExecute(ZhiHuNewsDetailBeans mZhiHuNewsDetailBeans) {
        String headerImage;
        if (mZhiHuNewsDetailBeans.getImage() == null || mZhiHuNewsDetailBeans.getImage() == "") {
            headerImage = "file:///android_asset/news_detail_header_image.jpg";

        } else {
            headerImage = mZhiHuNewsDetailBeans.getImage();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"img-wrap\">")
                .append("<h1 class=\"headline-title\">")
                .append(mZhiHuNewsDetailBeans.getTitle()).append("</h1>")
                .append("<span class=\"img-source\">")
                .append(mZhiHuNewsDetailBeans.getImage_source()).append("</span>")
                .append("<img src=\"").append(headerImage)
                .append("\" alt=\"\">")
                .append("<div class=\"img-mask\"></div>");
        String mNewsContent = "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_content_style.css\"/>"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_header_style.css\"/>"
                + mZhiHuNewsDetailBeans.getBody().replace("<div class=\"img-place-holder\">", sb.toString());
        mWebView.loadDataWithBaseURL("file:///android_asset/", mNewsContent, "text/html", "UTF-8", null);
    }
}
