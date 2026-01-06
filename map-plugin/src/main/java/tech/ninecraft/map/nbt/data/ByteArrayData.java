package tech.ninecraft.map.nbt.data;

/**
 * NBT byte array data type.
 *
 * @param value Tag data.
 * @since 1.0.0
 */
public record ByteArrayData(byte[] value) implements Data<byte[]> {
}
