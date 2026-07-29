package tech.ninecraft.server.api.exception;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * 404 Not Found controller exception.
 *
 * @since 1.0.0
 */
public final class NotFoundException extends ControllerException {
    /**
     * Constructor.
     *
     * @param message Error message.
     */
    public NotFoundException(@NonNull final String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param message Error message.
     * @param details (Optional) Detailed error message.
     */
    public NotFoundException(@NonNull final String message, @Nullable final String details) {
        super(message, details);
    }

    /**
     * Constructor.
     *
     * @param message Error message.
     * @param cause Error cause (wrapped exception).
     */
    public NotFoundException(@NonNull final String message, @NonNull final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor.
     *
     * @param message Error message.
     * @param details (Optional) Detailed error message.
     * @param cause   Error cause (wrapped exception).
     */
    public NotFoundException(
        @NonNull final String message,
        @Nullable final String details,
        @NonNull final Throwable cause) {
        super(message, details, cause);
    }
}
