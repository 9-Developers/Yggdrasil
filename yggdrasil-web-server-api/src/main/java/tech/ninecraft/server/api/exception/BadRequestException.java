package tech.ninecraft.server.api.exception;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * 400 Bad Request controller exception.
 *
 * @since 1.0.0
 */
public final class BadRequestException extends ControllerException {
    /**
     * Constructor.
     *
     * @param message Error message.
     */
    public BadRequestException(@NonNull final String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param message Error message.
     * @param details (Optional) Detailed error message.
     */
    public BadRequestException(@NonNull final String message, @Nullable final String details) {
        super(message, details);
    }

    /**
     * Constructor.
     *
     * @param message Error message.
     * @param cause   Error cause (wrapped exception).
     */
    public BadRequestException(@NonNull final String message, @NonNull final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor.
     *
     * @param message Error message.
     * @param details (Optional) Detailed error message.
     * @param cause   Error cause (wrapped exception).
     */
    public BadRequestException(
        @NonNull final String message,
        @Nullable final String details,
        @NonNull final Throwable cause) {
        super(message, details, cause);
    }
}
