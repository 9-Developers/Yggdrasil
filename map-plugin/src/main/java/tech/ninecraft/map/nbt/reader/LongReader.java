package tech.ninecraft.map.nbt.reader;

import tech.ninecraft.map.error.BufferOverflowError;
import tech.ninecraft.map.nbt.data.LongData;
import tech.ninecraft.map.nbt.data.ReadResult;

import java.nio.ByteBuffer;

import org.jspecify.annotations.NonNull;

/**
 * NBT long data type.
 *
 * @since 1.0.0
 */
final class LongReader implements TagReader<Long> {
    /** Data size (number of bytes read). */
    private static final int SIZE = 8;

    /**
     * Package-private constructor.
     */
    LongReader() {
    }

    @NonNull
    @Override
    public ReadResult<Long> readValue(final byte[] buffer, final int offset) {
        if (buffer.length < offset + SIZE) {
            return ReadResult.fail(new BufferOverflowError(buffer.length, offset, SIZE), SIZE);
        }

        return ReadResult.success(new LongData(ByteBuffer.wrap(buffer, offset, SIZE).getLong()), SIZE);
    }
}
