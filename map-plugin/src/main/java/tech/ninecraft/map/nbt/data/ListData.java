package tech.ninecraft.map.nbt.data;

import java.util.List;

import org.jspecify.annotations.NonNull;

/**
 * NBT list data type.
 *
 * @param value Tag data.
 * @since 1.0.0
 */
public record ListData(@NonNull List<Data<?>> value) implements Data<List<Data<?>>> {
}
