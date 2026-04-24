package tech.ninecraft.map.error;

/**
 * Unrecognized NBT tag type error.
 *
 * @since 1.0.0
 */
public final class UnrecognizedTypeIDError extends NBTError {
    /**
     * Constructor.
     *
     * @param typeID Unrecognized tag type ID.
     */
    public UnrecognizedTypeIDError(final byte typeID) {
        super("Unrecognized NBT tag type " + typeID);
    }
}
