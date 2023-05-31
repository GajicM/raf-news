package raf.webProgramiranje.resources;

import raf.webProgramiranje.entities.Comment;
import raf.webProgramiranje.entities.News;
import raf.webProgramiranje.services.CommentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/comments")
public class CommentResource {
    @Inject
    private CommentService commentService;

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addComment")
    @POST
   public Comment addComment(Comment comment){
       return commentService.addComment(comment);
   }
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/deleteComment")
    @DELETE
    public Comment deleteComment(Comment comment){
       return  commentService.deleteComment(comment);
    }
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/getByNews/{id}")
    @GET
    public List<Comment> getAllCommentsForNews(@PathParam("id") Integer newsID){
       return commentService.getAllCommentsForNews(newsID);
    }
}
