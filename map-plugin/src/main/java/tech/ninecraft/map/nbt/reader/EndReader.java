package tech.ninecraft.map.nbt.reader;

import tech.ninecraft.map.nbt.data.EndData;
import tech.ninecraft.map.nbt.data.ReadResult;

import org.jspecify.annotations.NonNull;

/**
 * NBT end data reader.
 *
 * @since 1.0.0
 */
public final class EndReader implements TagReader<Void> {
    /**
     * Package-private constructor.
     */
    EndReader() {
    }

    @Override
    public @NonNull ReadResult<Void> readValue(final byte[] buffer, final int offset) {
        return ReadResult.success(new EndData(null), 0);
    }
}
