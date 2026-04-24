package tech.ninecraft.server.model;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Better Auth {@code join} config.
 *
 * @param limit    Limit the number of rows to return.
 * @param on       The joining column names.
 * @param relation The relation type
 * @since 1.0.0
 */
public record JoinConfig(@Nullable Integer limit, @NonNull JoinOn on, @Nullable JoinRelation relation) {
}
