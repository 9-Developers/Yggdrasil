package tech.ninecraft.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Better Auth join relation.
 *
 * @since 1.0.0
 */
public enum JoinRelation {
    /** Many-to-many relation. */
    @JsonProperty("many-to-many")
    MANY_TO_MANY,
    /** One-to-many relation. */
    @JsonProperty("one-to-many")
    ONE_TO_MANY,
    /** One-to-one relation. */
    @JsonProperty("one-to-one")
    ONE_TO_ONE,
}
