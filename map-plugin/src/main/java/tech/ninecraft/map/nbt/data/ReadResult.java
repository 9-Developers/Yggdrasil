package tech.ninecraft.map.nbt.data;

import tech.ninecraft.map.error.NBTError;

import org.jspecify.annotations.NonNull;

/**
 * NBT read result.
 *
 * @param data Tag data if read was successful, otherwise {@code null}.
 * @param error Error if read was unsuccessful, otherwise {@code null}.
 * @param size Data size in bytes.
 * @param <T> Tag type.
 * @since 1.0.0
 */
public record ReadResult<T>(Data<T> data, NBTError error, int size) {
    /**
     * Get a new failed {@link ReadResult}.
     *
     * @param error Read error.
     * @param size Tag data size (bytes read before failure).
     * @param <T> Tag type.
     * @return Failed {@link ReadResult}.
     */
    public static <T> ReadResult<T> fail(@NonNull final NBTError error, final int size) {
        return new ReadResult<>(null, error, size);
    }

    /**
     * Get a new successful {@link ReadResult}.
     *
     * @param data Tag data.
     * @param size Tag data size.
     * @param <T> Tag type.
     * @return Successful {@link ReadResult}.
     */
    public static <T> ReadResult<T> success(@NonNull final Data<T> data, final int size) {
        return new ReadResult<>(data, null, size);
    }

    /**
     * Check if the result is failed.
     *
     * @return {@code true} if {@link ReadResult#error} is not null, otherwise {@code false}.
     */
    public boolean isFailed() {
        return error != null;
    }

    /**
     * Check if the result is successful.
     *
     * @return {@code true} if {@link ReadResult#data} is not null, otherwise {@code false}.
     */
    public boolean isSuccess() {
        return data != null;
    }
}
