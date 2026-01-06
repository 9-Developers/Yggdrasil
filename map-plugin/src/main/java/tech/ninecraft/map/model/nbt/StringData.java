package tech.ninecraft.map.model.nbt;

import org.jspecify.annotations.NonNull;

/**
 * NBT string data type.
 *
 * @param value Tag data.
 * @since 1.0.0
 */
public record StringData(@NonNull String value) implements Data<String> {
}
