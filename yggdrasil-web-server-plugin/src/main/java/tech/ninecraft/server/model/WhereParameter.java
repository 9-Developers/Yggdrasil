package tech.ninecraft.server.model;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Better Auth {@code where} query parameter.
 *
 * @param connector (Optional) Clause connector.
 * @param field     Field/column name.
 * @param mode      (Optional) Case sensitivity mode.
 * @param operator  (Optional) Clause operator.
 * @param value     Clause comparison value.
 * @since 1.0.0
 */
public record WhereParameter(
    @Nullable WhereConnector connector,
    @NonNull String field,
    @Nullable WhereCaseSensitive mode,
    @Nullable WhereOperator operator,
    @NonNull String value) {
}
