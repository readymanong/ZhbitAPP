package com.example.dm.zhbit.ZhiHu;

import android.os.AsyncTask;
import android.util.Log;

import com.example.dm.zhbit.beans.News;
import adapter.NewsAdapter;
import com.example.dm.zhbit.utiltools.HttpUtil;
import com.example.dm.zhbit.utiltools.JsonHelper;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;


public class ZhiHuLoadNewsTask extends AsyncTask<Void, Void, List<News>> {
    private NewsAdapter adapter;
    private onFinishListener listener;

    public ZhiHuLoadNewsTask(NewsAdapter adapter) {
        super();
        this.adapter = adapter;
    }

    public ZhiHuLoadNewsTask(NewsAdapter adapter, onFinishListener listener) {
        super();
        this.adapter = adapter;
        this.listener = listener;
    }

    @Override
    protected List<News> doInBackground(Void... params) {
        List<News> newsList = null;
        try {
            newsList = JsonHelper.parseJsonToList(HttpUtil.getLastNewsList());
        } catch (IOException | JSONException e) {
            Log.i("TAG", e.getMessage());
        } finally {
            return newsList;
        }
    }

    @Override
    protected void onPostExecute(List<News> newsList) {
        adapter.refreshNewsList(newsList);
        if (listener != null) {
            listener.afterTaskFinish();
        }

    }

    public interface onFinishListener {
        public void afterTaskFinish();
    }
}
