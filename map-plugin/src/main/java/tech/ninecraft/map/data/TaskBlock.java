package tech.ninecraft.map.data;

import org.bukkit.Material;
import org.jspecify.annotations.NonNull;

/**
 * A section to process in a plugin task.
 *
 * @param x X-coordinate in the chunk.
 * @param y Y-coordinate in the chunk.
 * @param z Z-coordinate in the chunk.
 * @param material Block material.
 * @since 1.0.0
 */
public record TaskBlock(int x, int y, int z, @NonNull Material material) {
}
