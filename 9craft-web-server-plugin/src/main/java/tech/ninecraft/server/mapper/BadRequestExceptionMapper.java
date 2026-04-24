package tech.ninecraft.server.mapper;

import tech.ninecraft.server.api.exception.BadRequestException;
import tech.ninecraft.server.api.model.ErrorResponse;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jspecify.annotations.NonNull;

/**
 * Map {@link BadRequestException} to {@link Response}.
 *
 * @since 1.0.0
 */
@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {
    @Override
    public Response toResponse(@NonNull final BadRequestException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
            .entity(new ErrorResponse(exception.getDetails(), exception.getMessage()))
            .build();
    }
}
