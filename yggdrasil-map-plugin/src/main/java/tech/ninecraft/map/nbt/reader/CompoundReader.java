package tech.ninecraft.map.nbt.reader;

import tech.ninecraft.map.error.UnrecognizedTypeIDError;
import tech.ninecraft.map.model.nbt.CompoundData;
import tech.ninecraft.map.model.nbt.Data;
import tech.ninecraft.map.model.nbt.ReadResult;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.jspecify.annotations.NonNull;

/**
 * NBT compound (struct) data reader.
 *
 * @since 1.0.0
 */
final class CompoundReader implements TagReader<Map<String, Data<?>>> {
    /**
     * Package-private constructor.
     */
    CompoundReader() {
    }

    @NonNull
    @Override
    public ReadResult<Map<String, Data<?>>> readValue(final byte[] buffer, final int offset) {
        final Map<String, Data<?>> value = new HashMap<>();
        int size = 0;

        while ((offset + size) < buffer.length) {
            final byte typeID = buffer[offset + size];
            ++size;

            if (typeID == TAG_END_ID) {
                break;
            }

            final Optional<TagReader<?>> reader = TagReader.getReader(typeID);

            if (reader.isEmpty()) {
                return ReadResult.fail(new UnrecognizedTypeIDError(typeID), size);
            }

            final ReadResult<String> tagName = STRING_READER.readValue(buffer, offset + size);
            size += tagName.size();

            if (tagName.isFailed()) {
                return ReadResult.fail(tagName.error(), size);
            }

            final ReadResult<?> tagValue = reader.get().readValue(buffer, offset + size);
            size += tagValue.size();

            if (tagValue.isFailed()) {
                return ReadResult.fail(tagValue.error(), size);
            }

            value.put(tagName.data().value(), tagValue.data());
        }

        return ReadResult.success(new CompoundData(Collections.unmodifiableMap(value)), size);
    }
}
