package tech.ninecraft.map.nbt.data;

import org.jspecify.annotations.NonNull;

/**
 * NBT float data type.
 *
 * @param value Tag data.
 * @since 1.0.0
 */
public record FloatData(@NonNull Float value) implements Data<Float> {
}
