package tech.ninecraft.map.model.nbt;

import org.jspecify.annotations.NonNull;

/**
 * NBT long data type.
 *
 * @param value Tag data.
 * @since 1.0.0
 */
public record LongData(@NonNull Long value) implements Data<Long> {
}
