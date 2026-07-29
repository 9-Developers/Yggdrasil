package tech.ninecraft.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Better Auth {@code where} operator.
 *
 * @since 1.0.0
 */
public enum WhereOperator {
    /** String contains. */
    @JsonProperty("contains")
    CONTAINS,
    /** String ends with. */
    @JsonProperty("ends_with")
    ENDS_WITH,
    /** Equal to (default). */
    @JsonProperty("eq")
    EQ,
    /** Greater than. */
    @JsonProperty("gt")
    GT,
    /** Greater than or equal to. */
    @JsonProperty("gte")
    GTE,
    /** In array. */
    @JsonProperty("in")
    IN,
    /** Less than. */
    @JsonProperty("lt")
    LT,
    /** Less than or equal to. */
    @JsonProperty("lte")
    LTE,
    /** Not equal to. */
    @JsonProperty("ne")
    NE,
    /** Not in array. */
    @JsonProperty("not_in")
    NOT_IN,
    /** String starts with. */
    @JsonProperty("starts_with")
    STARTS_WITH,
}
