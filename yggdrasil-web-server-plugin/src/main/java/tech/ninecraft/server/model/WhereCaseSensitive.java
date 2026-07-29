package tech.ninecraft.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Better Auth {@code where} clause case sensitivity.
 *
 * @since 1.0.0
 */
public enum WhereCaseSensitive {
    /** Case insensitive. */
    @JsonProperty("insensitive")
    INSENSITIVE,
    /** Case sensitive. */
    @JsonProperty("sensitive")
    SENSITIVE,
}
