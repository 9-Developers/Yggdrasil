package tech.ninecraft.map.error;

import org.jspecify.annotations.NonNull;

/**
 * NBT error.
 *
 * @since 1.0.0
 */
public abstract sealed class NBTError extends Exception
        permits BufferOverflowError, UnrecognizedCompressionTypeError, UnrecognizedTypeIDError {
    /**
     * Error message constructor.
     *
     * @param message Error message.
     */
    public NBTError(@NonNull final String message) {
        super(message);
    }

    /**
     * Cause constructor.
     *
     * @param message Error message.
     * @param cause   Error cause.
     */
    public NBTError(@NonNull final String message, @NonNull final Exception cause) {
        super(message, cause);
    }
}
