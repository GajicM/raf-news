package raf.webProgramiranje.requests;

import raf.webProgramiranje.entities.*;
public class NewsTagRequest {
    private News news;
    private Tag tag;

    public News getNews() {
        return news;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
