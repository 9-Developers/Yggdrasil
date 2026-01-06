package tech.ninecraft.map.nbt.data;

/**
 * NBT long array data type.
 *
 * @param value Tag data.
 * @since 1.0.0
 */
public record LongArrayData(long[] value) implements Data<long[]> {
}
