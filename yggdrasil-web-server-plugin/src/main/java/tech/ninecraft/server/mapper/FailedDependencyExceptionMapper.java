package tech.ninecraft.server.mapper;

import tech.ninecraft.server.api.exception.FailedDependencyException;
import tech.ninecraft.server.api.model.ErrorResponse;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jspecify.annotations.NonNull;

/**
 * Map {@link FailedDependencyException} to {@link Response}.
 *
 * @since 1.0.0
 */
@Provider
public class FailedDependencyExceptionMapper implements ExceptionMapper<FailedDependencyException> {
    @Override
    public Response toResponse(@NonNull final FailedDependencyException exception) {
        return Response.status(424)
            .entity(new ErrorResponse(exception.getDetails(), exception.getMessage()))
            .build();
    }
}
