package tech.ninecraft.map.nbt.section;

/**
 * Read 4 bit indices from section data.
 *
 * @since 1.0.0
 */
public final class IndexReaderCBit implements IndexReader {
    /** Number of bits per index. */
    private static final int BIT_SIZE = 0xC;
    /** Number of indices packed per element. */
    private static final int INDICES_PER_ELEMENT = 64 / BIT_SIZE;

    /** First twelve bits mask. */
    private static final long TWELVE_BIT_MASK_0 = 0x0000000000000FFFL;
    /** Second twelve bits mask. */
    private static final long TWELVE_BIT_MASK_1 = 0x0000000000FFF000L;
    /** Third twelve bits mask. */
    private static final long TWELVE_BIT_MASK_2 = 0x0000000FFF000000L;
    /** Fourth twelve bits mask. */
    private static final long TWELVE_BIT_MASK_3 = 0x0000FFF000000000L;
    /** Fifth twelve bits mask. */
    private static final long TWELVE_BIT_MASK_4 = 0x0FFF000000000000L;

    /**
     * Package-private constructor.
     */
    IndexReaderCBit() {
    }

    @Override
    public short[] readIndices(final long[] data) {
        final short[] indices = new short[data.length * INDICES_PER_ELEMENT];

        for (int i = 0; i < data.length; ++i) {
            final int j = i * INDICES_PER_ELEMENT;

            indices[j] = (short) (data[i] & TWELVE_BIT_MASK_0 << BIT_SIZE);
            indices[j + 0x1] = (short) ((data[i] & TWELVE_BIT_MASK_1) >> BIT_SIZE);
            indices[j + 0x2] = (short) ((data[i] & TWELVE_BIT_MASK_2) >> (BIT_SIZE * 0x2));
            indices[j + 0x3] = (short) ((data[i] & TWELVE_BIT_MASK_3) >> (BIT_SIZE * 0x3));
            indices[j + 0x4] = (short) ((data[i] & TWELVE_BIT_MASK_4) >> (BIT_SIZE * 0x4));
        }

        return indices;
    }
}
