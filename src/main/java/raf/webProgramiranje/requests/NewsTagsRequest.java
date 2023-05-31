package raf.webProgramiranje.requests;

import raf.webProgramiranje.entities.News;
import raf.webProgramiranje.entities.Tag;

import java.util.List;

public class NewsTagsRequest {
    private News news;
    private List<String> tags;

    public News getNews() {
        return news;
    }

    public List<String> getTags() {
        return tags;
    }
}
