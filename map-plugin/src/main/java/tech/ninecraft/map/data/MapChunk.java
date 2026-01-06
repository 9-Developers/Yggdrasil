package tech.ninecraft.map.data;

import java.util.List;

import org.jspecify.annotations.NonNull;

/**
 * Map of a single chunk.
 *
 * <p>
 *     The expected format of {@code blocks} is outer list z-coordinate, inner list x-coordinate.
 *     This means to get a section at given x/z coordinate you would access:
 *     <code>
 *         blocks.get(z).get(x);
 *     </code>
 * </p>
 *
 * @param blocks Map of section locations to their material.
 * @since 1.0.0
 */
public record MapChunk(@NonNull List<List<MapBlock>> blocks) {
}
