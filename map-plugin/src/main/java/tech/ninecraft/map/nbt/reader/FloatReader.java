package tech.ninecraft.map.nbt.reader;

import tech.ninecraft.map.error.BufferOverflowError;
import tech.ninecraft.map.nbt.data.FloatData;
import tech.ninecraft.map.nbt.data.ReadResult;

import java.nio.ByteBuffer;

import org.jspecify.annotations.NonNull;

/**
 * NBT float data type.
 *
 * @since 1.0.0
 */
final class FloatReader implements TagReader<Float> {
    /** Data size (number of bytes read). */
    private static final int SIZE = 4;

    /**
     * Package-private constructor.
     */
    FloatReader() {
    }

    @NonNull
    @Override
    public ReadResult<Float> readValue(final byte[] buffer, final int offset) {
        if (buffer.length < offset + SIZE) {
            return ReadResult.fail(new BufferOverflowError(buffer.length, offset, SIZE), SIZE);
        }

        return ReadResult.success(new FloatData(ByteBuffer.wrap(buffer, offset, SIZE).getFloat()), SIZE);
    }
}
