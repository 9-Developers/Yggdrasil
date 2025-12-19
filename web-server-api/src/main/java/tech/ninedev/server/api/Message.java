package tech.ninedev.server.api;

import org.jspecify.annotations.NonNull;
import org.slf4j.event.Level;

/**
 * Message from a resource.
 *
 * @param level   Message level.
 * @param message Message content.
 * @since 1.0.0
 */
public record Message(@NonNull Level level, @NonNull String message) {
}
