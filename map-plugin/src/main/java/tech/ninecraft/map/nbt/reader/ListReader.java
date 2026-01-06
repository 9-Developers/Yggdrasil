package tech.ninecraft.map.nbt.reader;

import tech.ninecraft.map.error.UnrecognizedTypeIDError;
import tech.ninecraft.map.nbt.data.Data;
import tech.ninecraft.map.nbt.data.ListData;
import tech.ninecraft.map.nbt.data.ReadResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.NonNull;

/**
 * NBT list data type.
 *
 * @since 1.0.0
 */
public final class ListReader implements TagReader<List<Data<?>>> {
    /**
     * Package-private constructor.
     */
    ListReader() {
    }

    @NonNull
    @Override
    public ReadResult<List<Data<?>>> readValue(final byte[] buffer, final int offset) {
        final ReadResult<Byte> typeID = BYTE_READER.readValue(buffer, offset);

        if (typeID.isFailed()) {
            return ReadResult.fail(typeID.error(), typeID.size());
        }

        int size = typeID.size();
        final ReadResult<Integer> length = INT_READER.readValue(buffer, offset + size);

        if (length.isFailed()) {
            return ReadResult.fail(length.error(), length.size());
        }

        size += length.size();
        final Optional<TagReader<?>> elementReader = TagReader.getReader(typeID.data().value());
        final List<Data<?>> value = new ArrayList<>(length.data().value());

        if (elementReader.isEmpty()) {
            return ReadResult.fail(new UnrecognizedTypeIDError(typeID.data().value()), size);
        }

        for (int index = 0; index < length.data().value(); ++index) {
            final ReadResult<?> element = elementReader.get().readValue(buffer, offset + size);
            size += element.size();

            if (element.isFailed()) {
                return ReadResult.fail(element.error(), size);
            }

            value.add(element.data());
        }

        return ReadResult.success(new ListData(Collections.unmodifiableList(value)), size);
    }
}
