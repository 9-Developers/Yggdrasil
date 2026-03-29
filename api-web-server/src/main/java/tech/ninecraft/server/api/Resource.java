package tech.ninecraft.server.api;

import org.jspecify.annotations.NonNull;

/**
 * Minecraft server resource (generally a plugin but could be anything).
 *
 * @since 1.0.0
 */
public interface Resource {
    /**
     * Get the resource's status.
     *
     * @return Status of the resource.
     */
    @NonNull
    Status status();
}
