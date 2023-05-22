package raf.webProgramiranje.resources;

import raf.webProgramiranje.entities.Category;
import raf.webProgramiranje.entities.News;
import raf.webProgramiranje.entities.Tag;
import raf.webProgramiranje.services.NewsService;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/news")
public class NewsResource {

    @Inject
    NewsService newsService;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addNews")
    public News createNews(News news){
        return newsService.createNews(news);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> getAllNews(){
        System.out.println("ulazi ovde");
        return newsService.getAllNews();

    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public News getNewsById(@PathParam("id") Integer id){
        return newsService.getNewsById(id);
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/changeNews")
    public News changeNews(News news){
        return newsService.changeNews(news);
    }

    @DELETE
    @Path("/deleteNews")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public News deleteNews(News news){
        return newsService.deleteNews(news);
    }



    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addTagToNews")
    public News addTagToNews(News news, Tag tag){
        return newsService.addTagToNews(news,tag);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/removeTagFromNews")
    public News removeTagFromNews(News news,Tag tag){
        return newsService.removeTagFromNews(news,tag);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/getNewsByTag")
    public List<News> getNewsByTag(Tag tag){
        return newsService.getNewsByTag(tag);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/getNewsByCategory")
    public List<News> getNewsByCategory(Category category){
        return newsService.getNewsByCategory(category);
    }

}
