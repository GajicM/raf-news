package raf.webProgramiranje.exceptions;

import org.jetbrains.annotations.NotNull;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class ResourceNotChangeableException extends RuntimeException implements ExceptionMapper<ResourceNotChangeableException> {

    private static final String DEFAULT_ERROR = "u fkd it"; //todo

    public ResourceNotChangeableException() {
        super(DEFAULT_ERROR);
    }

    public ResourceNotChangeableException(String message) {
        super(message);
        System.out.println(message);
    }

    @Override
    public Response toResponse(@NotNull ResourceNotChangeableException exception) {
        System.out.println("ulazi");
        return Response.status(423).entity(new MessageResponseObject(exception.getMessage()))
                .type("application/json").build();

    }
}
