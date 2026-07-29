package tech.ninecraft.server.mapper;

import tech.ninecraft.server.api.exception.InternalServerException;
import tech.ninecraft.server.api.model.ErrorResponse;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jspecify.annotations.NonNull;

/**
 * Map {@link InternalServerException} to {@link Response}.
 *
 * @since 1.0.0
 */
@Provider
public class InternalServerExceptionMapper implements ExceptionMapper<InternalServerException> {
    @Override
    public Response toResponse(@NonNull final InternalServerException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(new ErrorResponse(exception.getDetails(), exception.getMessage()))
            .build();
    }
}
