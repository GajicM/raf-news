package raf.webProgramiranje.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserInactiveException extends RuntimeException implements ExceptionMapper<UserInactiveException> {

    private static final String DEFAULT_ERROR = "User is inactive";

    public UserInactiveException() {
        super(DEFAULT_ERROR);
    }

    public UserInactiveException(String message) {
        super(message);
        System.out.println(message);
    }

    @Override
    public Response toResponse(UserInactiveException exception) {
        return Response.status(423).entity(new MessageResponseObject(exception.getMessage()))
                .type("application/json").build();

    }
}