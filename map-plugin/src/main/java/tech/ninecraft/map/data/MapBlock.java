package tech.ninecraft.map.data;

import org.bukkit.Material;
import org.jspecify.annotations.NonNull;

/**
 * A map section.
 *
 * @param y Y-coordinate.
 * @param material Block material.
 * @since 1.0.0
 */
public record MapBlock(int y, @NonNull Material material) {
}
