package tech.ninecraft.map.nbt.reader;

import tech.ninecraft.map.error.BufferOverflowError;
import tech.ninecraft.map.nbt.data.ReadResult;
import tech.ninecraft.map.nbt.data.StringData;

import java.nio.charset.StandardCharsets;

import org.jspecify.annotations.NonNull;

/**
 * NBT string data type.
 *
 * @since 1.0.0
 */
final class StringReader implements TagReader<String> {
    /**
     * Package-private constructor.
     */
    StringReader() {
    }

    @NonNull
    @Override
    public ReadResult<String> readValue(final byte[] buffer, final int offset) {
        final ReadResult<Short> length = SHORT_READER.readValue(buffer, offset);

        if (length.isFailed()) {
            return ReadResult.fail(length.error(), length.size());
        }

        final int expectedSize = length.size() + length.data().value();
        final int end = offset + expectedSize;

        if (buffer.length < end) {
            return ReadResult.fail(new BufferOverflowError(buffer.length, offset, expectedSize), length.size());
        }

        return ReadResult.success(
                new StringData(new String(
                        buffer,
                        offset + length.size(),
                        length.data().value(),
                        StandardCharsets.UTF_8)),
                length.size() + length.data().value());
    }
}
