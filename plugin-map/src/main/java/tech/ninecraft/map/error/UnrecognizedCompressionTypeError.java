package tech.ninecraft.map.error;

/**
 * Unrecognized compression type error.
 *
 * @since 1.0.0
 */
public final class UnrecognizedCompressionTypeError extends NBTError {
    /**
     * Constructor.
     *
     * @param compressionType Unrecognized compression type.
     */
    public UnrecognizedCompressionTypeError(final byte compressionType) {
        super("Unrecognized compression type " + compressionType);
    }
}
