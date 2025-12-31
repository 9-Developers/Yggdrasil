package tech.ninedev.server.model;

import tech.ninedev.server.api.Resource;

import java.util.List;
import java.util.Map;

import org.jspecify.annotations.NonNull;

/**
 * Registered plugin.
 *
 * @param paths     Public HTTP paths.
 * @param resources Registered resources.
 * @since 1.0.0
 */
public record RegisteredPlugin(
    @NonNull List<String> paths,
    @NonNull Map<String, Resource> resources) {
}
