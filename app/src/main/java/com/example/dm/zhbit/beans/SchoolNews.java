package com.example.dm.zhbit.beans;

/**
 * Created by Administrator on 2017/4/24.
 */

public class SchoolNews {
    private String newsTitle;   //新闻标题
    private String newsUrl;     //新闻链接地址
    private String newsTime;    //新闻时间与来源

    public SchoolNews(String newsTitle,String newsUrl,String newsTime) {
        this.newsTitle = newsTitle;
        this.newsUrl = newsUrl;
        this.newsTime = newsTime;
    }



    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }
}
