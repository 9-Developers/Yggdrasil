package tech.ninecraft.map.model.nbt;

import org.jspecify.annotations.NonNull;

/**
 * NBT byte data type.
 *
 * @param value Tag data.
 * @since 1.0.0
 */
public record ByteData(@NonNull Byte value) implements Data<Byte> {
}
