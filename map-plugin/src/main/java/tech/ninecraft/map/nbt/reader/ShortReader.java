package tech.ninecraft.map.nbt.reader;

import tech.ninecraft.map.error.BufferOverflowError;
import tech.ninecraft.map.nbt.data.ReadResult;
import tech.ninecraft.map.nbt.data.ShortData;

import java.nio.ByteBuffer;

import org.jspecify.annotations.NonNull;

/**
 * NBT short data type.
 *
 * @since 1.0.0
 */
final class ShortReader implements TagReader<Short> {
    /** Data size (number of bytes read). */
    private static final int SIZE = 2;

    /**
     * Package-private constructor.
     */
    ShortReader() {
    }

    @NonNull
    @Override
    public ReadResult<Short> readValue(final byte[] buffer, final int offset) {
        if (buffer.length < offset + SIZE) {
            return ReadResult.fail(new BufferOverflowError(buffer.length, offset, SIZE), SIZE);
        }

        return ReadResult.success(new ShortData(ByteBuffer.wrap(buffer, offset, SIZE).getShort()), SIZE);
    }
}
