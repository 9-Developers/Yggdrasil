package tech.ninecraft.map.model.nbt;

/**
 * NBT int array data type.
 *
 * @param value Tag data.
 * @since 1.0.0
 */
public record IntArrayData(int[] value) implements Data<int[]> {
}
