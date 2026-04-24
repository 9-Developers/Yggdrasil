package tech.ninecraft.server.mapper;

import tech.ninecraft.server.api.exception.UnauthorizedException;
import tech.ninecraft.server.api.model.ErrorResponse;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jspecify.annotations.NonNull;

/**
 * Map {@link UnauthorizedException} to {@link Response}.
 *
 * @since 1.0.0
 */
@Provider
public class UnauthorizedExceptionMapper implements ExceptionMapper<UnauthorizedException> {
    @Override
    public Response toResponse(@NonNull final UnauthorizedException exception) {
        return Response.status(Response.Status.UNAUTHORIZED)
            .entity(new ErrorResponse(exception.getDetails(), exception.getMessage()))
            .build();
    }
}
