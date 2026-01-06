package tech.ninecraft.map.nbt.reader;

import tech.ninecraft.map.error.BufferOverflowError;
import tech.ninecraft.map.nbt.data.ByteArrayData;
import tech.ninecraft.map.nbt.data.ReadResult;

import java.util.Arrays;

import org.jspecify.annotations.NonNull;

/**
 * NBT byte array data type.
 *
 * @since 1.0.0
 */
final class ByteArrayReader implements TagReader<byte[]> {
    /**
     * Package-private constructor.
     */
    ByteArrayReader() {
    }

    @NonNull
    @Override
    public ReadResult<byte[]> readValue(final byte[] buffer, final int offset) {
        final ReadResult<Integer> length = INT_READER.readValue(buffer, offset);

        if (length.isFailed()) {
            return ReadResult.fail(length.error(), length.size());
        }

        final int size = length.size() + length.data().value();
        final int end = offset + size;

        if (buffer.length < end) {
            return ReadResult.fail(new BufferOverflowError(buffer.length, offset, size), length.size());
        }

        return ReadResult.success(
                new ByteArrayData(Arrays.copyOfRange(buffer, offset + length.size(), end)),
                length.size() + length.data().value());
    }
}
