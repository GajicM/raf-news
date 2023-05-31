package raf.webProgramiranje.repositories.specifications;

import raf.webProgramiranje.entities.News;
import raf.webProgramiranje.entities.Tag;

import java.util.List;

public interface NewsRepository {
    News createNews(News news);
    List<News> getAllNews();
    News getNewsById(Integer id);
    News changeNews(News news);
    boolean deleteNews(Integer newsID);
    News addTagToNews(News news, Tag tag);
    News removeTagFromNews(News news,Tag tag);

    News changeTagInNews(News news, List<Tag> tags);

    List<News> getNewsByTag(Integer tagID);
    List<News> getNewsByCategory(Integer category);

    List<News> getMostReadNews();

    List<News> getRecentNews();
}
