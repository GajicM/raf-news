package raf.webProgramiranje.resources;

import raf.webProgramiranje.entities.Category;
import raf.webProgramiranje.services.CategoryService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/category")
public class CategoryResource {
    @Inject
    CategoryService categoryService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addCategory")
   public Category addCategory(Category category){
       return this.categoryService.addCategory(category);
   }

    @DELETE
    @Path("/removeCategory/{id}")
    public boolean deleteCategory(@PathParam("id") Integer categoryID){

       return this.categoryService.deleteCategory(categoryID);

    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllCategories")
    public List<Category> getAllCategories(){
       return this.categoryService.getAllCategories();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/changeCategory")
    public Category changeCategory(Category category){
        System.out.println("ULAZI OVDE"+ category);
        return this.categoryService.changeCategory(category);
    }
}
