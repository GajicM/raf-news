package raf.webProgramiranje.services.implementations;

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
    public boolean deleteNews(Integer newsID) {
        return newsRepository.deleteNews(newsID);
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
    public List<News> getNewsByTag(Integer tagID) {
        return newsRepository.getNewsByTag(tagID);
    }

    @Override
    public List<News> getNewsByCategory(Integer category) {
        return newsRepository.getNewsByCategory(category);
    }

    @Override
    public List<News> getMostReadNews() {
        return newsRepository.getMostReadNews();
    }
    @Override
   public List<News> getRecentNews(){
        return newsRepository.getRecentNews();
   }
@Override
   public News changeTagInNews(News news, List<Tag> tags){
        return newsRepository.changeTagInNews(news,tags);
   }
   @Override
   public List<News> getAllNews(int offset, int limit){return newsRepository.getAllNews(offset,limit);}
}
