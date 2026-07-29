package tech.ninecraft.server.mapper;

import tech.ninecraft.server.api.exception.NotFoundException;
import tech.ninecraft.server.api.model.ErrorResponse;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jspecify.annotations.NonNull;

/**
 * Map {@link NotFoundException} to {@link Response}.
 *
 * @since 1.0.0
 */
@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(@NonNull final NotFoundException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(new ErrorResponse(exception.getDetails(), exception.getMessage()))
            .build();
    }
}
