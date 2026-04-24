package tech.ninecraft.map.model.nbt;

import org.jspecify.annotations.NonNull;

/**
 * NBT short data type.
 *
 * @param value Tag data.
 * @since 1.0.0
 */
public record ShortData(@NonNull Short value) implements Data<Short> {
}
