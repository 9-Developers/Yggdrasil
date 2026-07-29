package tech.ninecraft.server.api.model;

/**
 * Resource status.
 *
 * @since 1.0.0
 */
public enum StatusCode {
    /** Resource is healthy. */
    OK,
    /** Resource is running, but functionality may be limited. */
    WARNING,
    /** Resource is not healthy. */
    ERROR,
}
