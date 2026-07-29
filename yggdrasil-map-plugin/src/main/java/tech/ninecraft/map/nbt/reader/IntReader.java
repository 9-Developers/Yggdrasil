package tech.ninecraft.map.nbt.reader;

import tech.ninecraft.map.error.BufferOverflowError;
import tech.ninecraft.map.model.nbt.IntData;
import tech.ninecraft.map.model.nbt.ReadResult;

import java.nio.ByteBuffer;

import org.jspecify.annotations.NonNull;

/**
 * NBT integer data type.
 *
 * @since 1.0.0
 */
final class IntReader implements TagReader<Integer> {
    /** Data size (number of bytes read). */
    private static final int SIZE = 4;

    /**
     * Package-private constructor.
     */
    IntReader() {
    }

    @NonNull
    @Override
    public ReadResult<Integer> readValue(final byte[] buffer, final int offset) {
        if (buffer.length < offset + SIZE) {
            return ReadResult.fail(new BufferOverflowError(buffer.length, offset, SIZE), SIZE);
        }

        return ReadResult.success(new IntData(ByteBuffer.wrap(buffer, offset, SIZE).getInt()), SIZE);
    }
}
