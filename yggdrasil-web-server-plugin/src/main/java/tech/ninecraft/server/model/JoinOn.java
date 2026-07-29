package tech.ninecraft.server.model;

import org.jspecify.annotations.NonNull;

/**
 * Better Auth {@code join} on config.
 *
 * @param from Column name from the main table.
 * @param to   Column name from the joined table.
 * @since 1.0.0
 */
public record JoinOn(@NonNull String from, @NonNull String to) {
}
