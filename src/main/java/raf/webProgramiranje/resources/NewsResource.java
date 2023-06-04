package raf.webProgramiranje.resources;

import raf.webProgramiranje.entities.Category;
import raf.webProgramiranje.entities.News;
import raf.webProgramiranje.entities.Tag;
import raf.webProgramiranje.requests.NewsTagRequest;
import raf.webProgramiranje.requests.NewsTagsRequest;
import raf.webProgramiranje.services.NewsService;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
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
    @Path("/getAllNews")
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

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/changeNews")
    public News changeNews(@Valid News news){
        return newsService.changeNews(news);
    }

    @DELETE
    @Path("/deleteNews/{id}")
    public boolean deleteNews(@PathParam("id") Integer newsID){
        return newsService.deleteNews(newsID);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getNewsByTag/{id}")
    public List<News> getNewsByTag(@PathParam("id")Integer tagID){
        return newsService.getNewsByTag(tagID);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/getNewsByCategory/{id}")
    public List<News> getNewsByCategory(@PathParam("id") Integer categoryID){
        return newsService.getNewsByCategory(categoryID);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addTagToNews")
    public Response addTagToNews(NewsTagRequest request) {
        News news = request.getNews();
        Tag tag = request.getTag();
        News updatedNews = newsService.addTagToNews(news, tag);
        return Response.ok(updatedNews).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/removeTagFromNews")
    public Response removeTagFromNews(NewsTagRequest request) {
        News news = request.getNews();
        Tag tag = request.getTag();
        News updatedNews = newsService.removeTagFromNews(news, tag);
        return Response.ok(updatedNews).build();
    }

    @Path("/getMostReadNews")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> getMostReadNews(){
        return newsService.getMostReadNews();
    }


    @Path("/getRecentNews")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> getRecentNews(){
        return newsService.getRecentNews();
    };


    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/changeTagsInNews")
   public Response changeTagInNews(NewsTagsRequest request){
        News news = request.getNews();
        List<String> tags = request.getTags();
        List<Tag> tagsx=new ArrayList<>();
        for(String s:tags){
            tagsx.add(new Tag(s));
        }
        News updatedNews = newsService.changeTagInNews(news, tagsx);
        return Response.ok(updatedNews).build();
   }

    // TODO ne koristi na frontu dok ne skontas koji je zahtev zapravo, jer ako saljes samo trenutnu stranicu, nemas paginaciju za
    //  sledecu stranicu sem ako ne posaljes zahtev za broj stranica, ili zahtev za sledecu stranicu iako ona mozda ne postoji,
    //  ali u tom slucaju ne mozes da korsitis plain bootstrapvue pagination jer se ona binduje za listu.
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllNewsPaginated")
    public List<News> getAllNews(@QueryParam("offset")@DefaultValue("0") int offset,@QueryParam("limit") @DefaultValue("6")int limit){
        System.out.println("ulazi ovde");
        return newsService.getAllNews();

    }
}
