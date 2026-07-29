package tech.ninecraft.server.api.exception;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Controller (API) exception.
 *
 * @since 1.0.0
 */
public abstract sealed class ControllerException extends Exception
    permits BadRequestException, FailedDependencyException, ForbiddenException, InternalServerException,
    NotFoundException, ServiceUnavailableException, UnauthorizedException {
    /** Detailed error message. */
    @Nullable
    private final String details;

    /**
     * Constructor.
     *
     * @param message Error message.
     */
    public ControllerException(@NonNull final String message) {
        this(message, (String) null);
    }

    /**
     * Constructor.
     *
     * @param message Error message.
     * @param details (Optional) Detailed error message.
     */
    public ControllerException(@NonNull final String message, @Nullable final String details) {
        super(message);

        this.details = details;
    }

    /**
     * Constructor.
     *
     * @param message Error message.
     * @param cause   Error cause (wrapped exception).
     */
    public ControllerException(@NonNull final String message, @NonNull final Throwable cause) {
        this(message, null, cause);
    }

    /**
     * Constructor.
     *
     * @param message Error message.
     * @param details (Optional) Detailed error message.
     * @param cause   Error cause (wrapped exception).
     */
    public ControllerException(
        @NonNull final String message,
        @Nullable final String details,
        @NonNull final Throwable cause) {
        super(message, cause);

        this.details = details;
    }

    /**
     * Get detailed error message.
     *
     * @return Detailed error message or {@code null}.
     */
    @Nullable
    public String getDetails() {
        return details;
    }
}
