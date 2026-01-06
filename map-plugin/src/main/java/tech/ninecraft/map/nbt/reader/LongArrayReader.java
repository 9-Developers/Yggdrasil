package tech.ninecraft.map.nbt.reader;

import tech.ninecraft.map.error.BufferOverflowError;
import tech.ninecraft.map.nbt.data.LongArrayData;
import tech.ninecraft.map.nbt.data.ReadResult;

import org.jspecify.annotations.NonNull;

/**
 * NBT long array data type.
 *
 * @since 1.0.0
 */
final class LongArrayReader implements TagReader<long[]> {
    /** Data size. */
    private static final int SIZE = 8;

    /**
     * Package-private constructor.
     */
    LongArrayReader() {
    }

    @NonNull
    @Override
    public ReadResult<long[]> readValue(final byte[] buffer, final int offset) {
        final ReadResult<Integer> length = INT_READER.readValue(buffer, offset);
        int size = length.size();

        if (length.isFailed()) {
            return ReadResult.fail(length.error(), length.size());
        }

        final int expectedSize = length.size() + (length.data().value() * SIZE);
        final int end = offset + expectedSize;

        if (buffer.length < end) {
            return ReadResult.fail(new BufferOverflowError(buffer.length, offset, expectedSize), length.size());
        }

        final long[] value = new long[length.data().value()];

        for (int index = 0; index < length.data().value(); ++index) {
            final ReadResult<Long> element = LONG_READER.readValue(buffer, offset + size);
            size += element.size();

            if (element.isFailed()) {
                return ReadResult.fail(element.error(), size);
            }

            value[index] = element.data().value();
        }

        return ReadResult.success(new LongArrayData(value), length.size() + (length.data().value() * SIZE));
    }
}
