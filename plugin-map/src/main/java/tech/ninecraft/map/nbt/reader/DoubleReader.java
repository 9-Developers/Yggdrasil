package tech.ninecraft.map.nbt.reader;

import tech.ninecraft.map.error.BufferOverflowError;
import tech.ninecraft.map.model.nbt.DoubleData;
import tech.ninecraft.map.model.nbt.ReadResult;

import java.nio.ByteBuffer;

import org.jspecify.annotations.NonNull;

/**
 * NBT double data type.
 *
 * @since 1.0.0
 */
final class DoubleReader implements TagReader<Double> {
    /** Data size (number of bytes read). */
    private static final int SIZE = 8;

    /**
     * Package-private constructor.
     */
    DoubleReader() {
    }

    @NonNull
    @Override
    public ReadResult<Double> readValue(final byte[] buffer, final int offset) {
        if (buffer.length < offset + SIZE) {
            return ReadResult.fail(new BufferOverflowError(buffer.length, offset, SIZE), SIZE);
        }

        return ReadResult.success(new DoubleData(ByteBuffer.wrap(buffer, offset, SIZE).getDouble()), SIZE);
    }
}
