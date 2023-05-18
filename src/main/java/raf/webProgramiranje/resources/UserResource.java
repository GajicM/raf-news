package raf.webProgramiranje.resources;


import raf.webProgramiranje.entities.User;
import raf.webProgramiranje.services.UserService;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addUser")
    public User addUser(User user) {
        return this.userService.addUser(user);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers(){
        return this.userService.getAllUsers();
    }

    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id") int id){
        return this.userService.getUser(id);
    }
    @PATCH
    @Path("/changeUserStatus")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User changeUserStatus(User user){
        return this.userService.changeUserStatus(user);
    }

}
