package raf.webProgramiranje.filters;



import raf.webProgramiranje.resources.UserResource;
import raf.webProgramiranje.services.UserService;

import javax.inject.Inject;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

@Provider
public class AuthFilter implements ContainerRequestFilter {

    @Inject
    UserService userService;
        ///JA MISLIM DA OVO RADI LMAO VEOMA JE AHHHHHHH
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        if (!this.isAuthRequiredAdmin(requestContext)) {
            if (!this.isAuthRequired(requestContext)) {

                return;
            } else {
              isAuthorizedCheck(requestContext,false);
            }
        } else {

            isAuthorizedCheck(requestContext,true);

        }

    }
    private void isAuthorizedCheck(ContainerRequestContext requestContext,boolean admin){
        try {
            String token = requestContext.getHeaderString("Authorization");
            if(token != null && token.startsWith("Bearer ")) {
                token = token.replace("Bearer ", "");
            }

            if (!this.userService.isAuthorizedAdmin(token) && admin) {

                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            } else if (!this.userService.isAuthorized(token) && !admin) {

                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private boolean isAuthRequired(ContainerRequestContext req) {
        if (req.getUriInfo().getPath().contains("login")) {
            return false;
        }
        if(req.getMethod().equalsIgnoreCase("OPTIONS"))
            return false;
        List<Object> matchedResources = req.getUriInfo().getMatchedResources();
        for (Object matchedResource : matchedResources) {
            if (matchedResource instanceof UserResource)
                return true;
           //TODO vidi koje resurse treba da Auth-ujes
        }
        if(req.getMethod().equalsIgnoreCase("GET")){

            return false;
        }else{

            return true;
        }

    }
    private boolean isAuthRequiredAdmin(ContainerRequestContext req){
        if (req.getUriInfo().getPath().contains("login")) {
            return false;
        }
        if(req.getMethod().equalsIgnoreCase("OPTIONS"))
            return false;
        List<Object> matchedResources = req.getUriInfo().getMatchedResources();
        for (Object matchedResource : matchedResources) {
            if (matchedResource instanceof UserResource)
                return true;

        }

        return false;
    }
}
