package tech.ninecraft.map.model;

import org.jspecify.annotations.NonNull;

/**
 * A simple message.
 *
 * @param message Message.
 * @since 1.0.0
 */
public record Message(@NonNull String message) {
}
