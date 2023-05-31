package raf.webProgramiranje.entities;

import java.util.List;

public class NewsTag {
    private Integer id;
    private Tag tags;
    private News news;
    private int newsId;
    private int tagId;
    public NewsTag(int newsId,int tagId){
        this.newsId=newsId;
        this.tagId=tagId;
    }
    public NewsTag(){}
}
