package org.example.cassiomolin.security.api.exeptionmapper;

import org.example.cassiomolin.common.api.model.ApiErrorDetails;
import org.example.cassiomolin.security.exception.InvalidAuthenticationTokenException;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidAuthenticationExceptionMapper implements ExceptionMapper<InvalidAuthenticationTokenException> {
    @Context
    private UriInfo uriInfo;

    @Override
    public Response toResponse(InvalidAuthenticationTokenException exception) {

        Status status = Status.FORBIDDEN;

        ApiErrorDetails errorDetails = new ApiErrorDetails();
        errorDetails.setStatus(status.getStatusCode());
        errorDetails.setTitle(status.getReasonPhrase());
        errorDetails.setMessage("Invalid Authentication Token Login Again.");
        errorDetails.setPath(uriInfo.getAbsolutePath().getPath());

        return Response.status(status).entity(errorDetails).type(MediaType.APPLICATION_JSON).build();
    }

	
}
