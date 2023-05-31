package raf.webProgramiranje.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class TestException extends RuntimeException implements ExceptionMapper<TestException> {

    private static final String DEFAULT_ERROR = "u fkd it"; //todo

    public TestException() {
        super(DEFAULT_ERROR);
    }

    public TestException(String message) {
        super(message);
        System.out.println(message);
    }

    @Override
    public Response toResponse(TestException exception) {
        return Response.status(500).entity(new MessageResponseObject(exception.getMessage()))
                .type("application/json").build();

    }
}
