package tech.ninecraft.map.data;

import java.util.List;

import org.jspecify.annotations.NonNull;

/**
 * A chunk to process in a plugin task.
 *
 * @param location The X/Z coordinates of the chunk.
 * @param blocks Blocks in the chunk to update.
 * @since 1.0.0
 */
public record TaskChunk(@NonNull Location2 location, @NonNull List<TaskBlock> blocks) {
}
