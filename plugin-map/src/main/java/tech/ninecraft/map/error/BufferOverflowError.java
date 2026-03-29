package tech.ninecraft.map.error;

/**
 * Backing buffer overflow error.
 *
 * <p>
 *     This error is returned when there is an attempt to read more data than is present in the uncompressed buffer.
 * </p>
 *
 * @since 1.0.0
 */
public final class BufferOverflowError extends NBTError {
    /**
     * Constructor.
     *
     * @param bufferSize Size of the uncompressed backing buffer.
     * @param offset Starting offset to read from.
     * @param size Size of the data being read.
     */
    public BufferOverflowError(final int bufferSize, final int offset, final int size) {
        super("Overflow on buffer of size " + bufferSize + " reading from " + offset + " bytes " + size);
    }
}
