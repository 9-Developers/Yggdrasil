package tech.ninecraft.server.model;

import java.util.List;

import org.jspecify.annotations.NonNull;

/**
 * Registered plugin response.
 *
 * @param paths     Registered HTTP paths.
 * @param resources Registered resources.
 * @since 1.0.0
 */
public record PluginResponse(@NonNull List<String> paths, @NonNull List<String> resources) {
}
