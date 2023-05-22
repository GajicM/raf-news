package raf.webProgramiranje.repositories.specifications;

import raf.webProgramiranje.entities.Category;
import raf.webProgramiranje.entities.News;
import raf.webProgramiranje.entities.Tag;

import java.util.List;

public interface NewsRepository {
    News createNews(News news);
    List<News> getAllNews();
    News getNewsById(Integer id);
    News changeNews(News news);
    News deleteNews(News news);
    News addTagToNews(News news, Tag tag);
    News removeTagFromNews(News news,Tag tag);
    List<News> getNewsByTag(Tag tag);
    List<News> getNewsByCategory(Category category);
}
