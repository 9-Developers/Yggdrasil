package tech.ninecraft.server.model;

/**
 * Is web authentication enabled response.
 *
 * @param isEnabled {@code true} if authentication is enabled, otherwise {@code false}.
 * @since 1.0.0
 */
public record EnabledResponse(boolean isEnabled) {
}
