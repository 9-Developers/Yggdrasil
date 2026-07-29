package tech.ninecraft.data.api;

/**
 * Cross-table relationship.
 *
 * @since 1.0.0
 */
public enum Relationship {
    /** Many-to-many relation. */
    MANY_TO_MANY,
    /** One-to-many relation. */
    ONE_TO_MANY,
    /** One-to-one relation. */
    ONE_TO_ONE,
}
