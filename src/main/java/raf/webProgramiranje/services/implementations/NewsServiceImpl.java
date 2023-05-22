package raf.webProgramiranje.services.implementations;

import raf.webProgramiranje.entities.Category;
import raf.webProgramiranje.entities.News;
import raf.webProgramiranje.entities.Tag;
import raf.webProgramiranje.repositories.specifications.NewsRepository;
import raf.webProgramiranje.services.NewsService;

import javax.inject.Inject;
import java.util.List;

public class NewsServiceImpl implements NewsService {
    @Inject
    NewsRepository newsRepository;
    @Override
    public News createNews(News news) {
        return newsRepository.createNews(news);
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.getAllNews();
    }

    @Override
    public News getNewsById(Integer id) {
        return newsRepository.getNewsById(id);
    }

    @Override
    public News changeNews(News news) {
        return newsRepository.changeNews(news);
    }

    @Override
    public News deleteNews(News news) {
        return newsRepository.deleteNews(news);
    }

    @Override
    public News addTagToNews(News news, Tag tag) {
        return newsRepository.addTagToNews(news,tag);
    }

    @Override
    public News removeTagFromNews(News news, Tag tag) {
        return newsRepository.removeTagFromNews(news,tag);
    }

    @Override
    public List<News> getNewsByTag(Tag tag) {
        return newsRepository.getNewsByTag(tag);
    }

    @Override
    public List<News> getNewsByCategory(Category category) {
        return newsRepository.getNewsByCategory(category);
    }
}
