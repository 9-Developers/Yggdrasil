package tech.ninecraft.map.nbt.data;

import org.jspecify.annotations.NonNull;

/**
 * NBT double data type.
 *
 * @param value Tag data.
 * @since 1.0.0
 */
public record DoubleData(@NonNull Double value) implements Data<Double> {
}
