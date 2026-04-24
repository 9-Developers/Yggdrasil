package tech.ninecraft.server.mapper;

import tech.ninecraft.server.api.exception.ServiceUnavailableException;
import tech.ninecraft.server.api.model.ErrorResponse;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jspecify.annotations.NonNull;

/**
 * Map {@link ServiceUnavailableException} to {@link Response}.
 *
 * @since 1.0.0
 */
@Provider
public class ServiceUnavailableExceptionMapper implements ExceptionMapper<ServiceUnavailableException> {
    @Override
    public Response toResponse(@NonNull final ServiceUnavailableException exception) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE)
            .entity(new ErrorResponse(exception.getDetails(), exception.getMessage()))
            .build();
    }
}
