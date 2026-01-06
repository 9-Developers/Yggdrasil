package tech.ninecraft.map.nbt.reader;

import tech.ninecraft.map.nbt.data.Data;
import tech.ninecraft.map.nbt.data.ReadResult;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jspecify.annotations.NonNull;

/**
 * NBT tag interface.
 *
 * <p>
 *     I really hate this code.
 *     If anyone has a better way to implement this please tell me or submit a PR.
 * </p>
 *
 * @param <T> Tag value type.
 * @since 1.0.0
 */
public sealed interface TagReader<T>
        permits ByteReader, ByteArrayReader, CompoundReader, DoubleReader, EndReader, FloatReader, IntReader,
        IntArrayReader, ListReader, LongReader, LongArrayReader, ShortReader, StringReader {
    /** NBT data end byte. */
    byte TAG_END_ID = 0;
    /** NBT byte data ID. */
    byte TAG_BYTE_ID = 1;
    /** NBT short data ID. */
    byte TAG_SHORT_ID = 2;
    /** NBT int data ID. */
    byte TAG_INT_ID = 3;
    /** NBT long data ID. */
    byte TAG_LONG_ID = 4;
    /** NBT float data ID. */
    byte TAG_FLOAT_ID = 5;
    /** NBT double data ID. */
    byte TAG_DOUBLE_ID = 6;
    /** NBT byte array data ID. */
    byte TAG_BYTE_ARRAY_ID = 7;
    /** NBT string data ID. */
    byte TAG_STRING_ID = 8;
    /** NBT list data ID. */
    byte TAG_LIST_ID = 9;
    /** NBT struct data ID. */
    byte TAG_COMPOUND_ID = 10;
    /** NBT int array data ID. */
    byte TAG_INT_ARRAY_ID = 11;
    /** NBT long array data ID. */
    byte TAG_LONG_ARRAY_ID = 12;

    /** NBT end reader. */
    TagReader<Void> END_READER = new EndReader();
    /** NBT byte reader. */
    TagReader<Byte> BYTE_READER = new ByteReader();
    /** NBT short reader. */
    TagReader<Short> SHORT_READER = new ShortReader();
    /** NBT integer reader. */
    TagReader<Integer> INT_READER = new IntReader();
    /** NBT long reader. */
    TagReader<Long> LONG_READER = new LongReader();
    /** NBT float reader. */
    TagReader<Float> FLOAT_READER = new FloatReader();
    /** NBT double reader. */
    TagReader<Double> DOUBLE_READER = new DoubleReader();
    /** NBT byte reader. */
    TagReader<byte[]> BYTE_ARRAY_READER = new ByteArrayReader();
    /** NBT string reader. */
    TagReader<String> STRING_READER = new StringReader();
    /** NBT list reader. */
    TagReader<List<Data<?>>> LIST_READER = new ListReader();
    /** NBT compound reader. */
    TagReader<Map<String, Data<?>>> COMPOUND_READER = new CompoundReader();
    /** NBT integer array reader. */
    TagReader<int[]> INT_ARRAY_READER = new IntArrayReader();
    /** NBT long array reader. */
    TagReader<long[]> LONG_ARRAY_READER = new LongArrayReader();

    /* ***************************************** Static utility methods ***************************************** */

    /**
     * Get reader by type ID.
     *
     * @param typeID Tag type ID.
     * @return Reader instance.
     */
    static Optional<TagReader<?>> getReader(final byte typeID) {
        switch (typeID) {
            case TAG_END_ID -> {
                return Optional.of(END_READER);
            }
            case TAG_BYTE_ID -> {
                return Optional.of(BYTE_READER);
            }
            case TAG_SHORT_ID -> {
                return Optional.of(SHORT_READER);
            }
            case TAG_INT_ID -> {
                return Optional.of(INT_READER);
            }
            case TAG_LONG_ID -> {
                return Optional.of(LONG_READER);
            }
            case TAG_FLOAT_ID -> {
                return Optional.of(FLOAT_READER);
            }
            case TAG_DOUBLE_ID -> {
                return Optional.of(DOUBLE_READER);
            }
            case TAG_BYTE_ARRAY_ID -> {
                return Optional.of(BYTE_ARRAY_READER);
            }
            case TAG_STRING_ID -> {
                return Optional.of(STRING_READER);
            }
            case TAG_LIST_ID -> {
                return Optional.of(LIST_READER);
            }
            case TAG_COMPOUND_ID -> {
                return Optional.of(COMPOUND_READER);
            }
            case TAG_INT_ARRAY_ID -> {
                return Optional.of(INT_ARRAY_READER);
            }
            case TAG_LONG_ARRAY_ID -> {
                return Optional.of(LONG_ARRAY_READER);
            }
            default -> {
                return Optional.empty();
            }
        }
    }

    /* ******************************************** Abstract methods ******************************************** */

    /**
     * Get tag data.
     *
     * @param buffer Raw NBT data buffer.
     * @param offset Offset in the buffer to start reading from.
     * @return Tag data.
     */
    @NonNull
    ReadResult<T> readValue(byte[] buffer, int offset);
}
