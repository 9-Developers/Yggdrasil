package tech.ninedev.server.api;

import java.util.List;

import org.jspecify.annotations.NonNull;

/**
 * Minecraft server resource (generally a plugin but could be anything).
 *
 * @since 1.0.0
 */
public interface Resource {
    /**
     * Get detailed messages.
     *
     * @return Important resource messages for server administrators.
     */
    @NonNull
    List<Message> messages();

    /**
     * Get the resource's status.
     *
     * @return Status of the resource.
     */
    @NonNull
    Status status();
}
