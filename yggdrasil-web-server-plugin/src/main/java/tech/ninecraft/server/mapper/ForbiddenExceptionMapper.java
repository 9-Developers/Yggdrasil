package tech.ninecraft.server.mapper;

import tech.ninecraft.server.api.exception.ForbiddenException;
import tech.ninecraft.server.api.model.ErrorResponse;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jspecify.annotations.NonNull;

/**
 * Map {@link ForbiddenException} to {@link Response}.
 *
 * @since 1.0.0
 */
@Provider
public class ForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException> {
    @Override
    public Response toResponse(@NonNull final ForbiddenException exception) {
        return Response.status(Response.Status.FORBIDDEN)
            .entity(new ErrorResponse(exception.getDetails(), exception.getMessage()))
            .build();
    }
}
