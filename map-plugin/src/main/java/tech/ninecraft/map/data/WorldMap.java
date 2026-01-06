package tech.ninecraft.map.data;

import java.util.Map;

import org.jspecify.annotations.NonNull;

/**
 * Map of a single world.
 *
 * @param chunks Map of chunks in the world.
 * @since 1.0.0
 */
public record WorldMap(@NonNull Map<Location2, MapChunk> chunks) {
}
