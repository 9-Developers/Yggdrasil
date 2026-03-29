package tech.ninecraft.map.nbt.reader;

import tech.ninecraft.map.error.BufferOverflowError;
import tech.ninecraft.map.model.nbt.ByteData;
import tech.ninecraft.map.model.nbt.ReadResult;

import org.jspecify.annotations.NonNull;

/**
 * NBT byte data type.
 *
 * @since 1.0.0
 */
final class ByteReader implements TagReader<Byte> {
    /** Data size (number of bytes read). */
    private static final int SIZE = 1;

    /**
     * Package-private constructor.
     */
    ByteReader() {
    }

    @NonNull
    @Override
    public ReadResult<Byte> readValue(final byte[] buffer, final int offset) {
        if (buffer.length < offset + SIZE) {
            return ReadResult.fail(new BufferOverflowError(buffer.length, offset, SIZE), SIZE);
        }

        return ReadResult.success(new ByteData(buffer[offset]), SIZE);
    }
}
