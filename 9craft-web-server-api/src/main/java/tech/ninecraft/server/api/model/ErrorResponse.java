package tech.ninecraft.server.api.model;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Controller (API) error response.
 *
 * @param details (Optional) Detailed error message.
 * @param message Error message.
 * @since 1.0.0
 */
public record ErrorResponse(@Nullable String details, @NonNull String message) {
}
