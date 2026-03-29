package tech.ninecraft.map.model.nbt;

import java.util.Map;

import org.jspecify.annotations.NonNull;

/**
 * NBT compound (struct) data type.
 *
 * @param value Tag data.
 * @since 1.0.0
 */
public record CompoundData(@NonNull Map<String, Data<?>> value) implements Data<Map<String, Data<?>>> {
}
