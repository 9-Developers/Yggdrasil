package tech.ninecraft.map.nbt.section;

/**
 * Read 4 bit indices from section data.
 *
 * @since 1.0.0
 */
public final class IndexReaderABit implements IndexReader {
    /** Number of bits per index. */
    private static final int BIT_SIZE = 0xA;
    /** Number of indices packed per element. */
    private static final int INDICES_PER_ELEMENT = 64 / BIT_SIZE;

    /** First ten bits mask. */
    private static final long TEN_BIT_MASK_0 = 0x00000000000003FFL;
    /** Second ten bits mask. */
    private static final long TEN_BIT_MASK_1 = 0x00000000000FFC00L;
    /** Third ten bits mask. */
    private static final long TEN_BIT_MASK_2 = 0x000000003FF00000L;
    /** Fourth ten bits mask. */
    private static final long TEN_BIT_MASK_3 = 0x000000FFC0000000L;
    /** Fifth ten bits mask. */
    private static final long TEN_BIT_MASK_4 = 0x0003FF0000000000L;
    /** Sixth ten bits mask. */
    private static final long TEN_BIT_MASK_5 = 0x0FFC000000000000L;

    /**
     * Package-private constructor.
     */
    IndexReaderABit() {
    }

    @Override
    public short[] readIndices(final long[] data) {
        final short[] indices = new short[data.length * INDICES_PER_ELEMENT];

        for (int i = 0; i < data.length; ++i) {
            final int j = i * INDICES_PER_ELEMENT;

            indices[j] = (short) (data[i] & TEN_BIT_MASK_0 << BIT_SIZE);
            indices[j + 0x1] = (short) ((data[i] & TEN_BIT_MASK_1) >> BIT_SIZE);
            indices[j + 0x2] = (short) ((data[i] & TEN_BIT_MASK_2) >> (BIT_SIZE * 0x2));
            indices[j + 0x3] = (short) ((data[i] & TEN_BIT_MASK_3) >> (BIT_SIZE * 0x3));
            indices[j + 0x4] = (short) ((data[i] & TEN_BIT_MASK_4) >> (BIT_SIZE * 0x4));
            indices[j + 0x5] = (short) ((data[i] & TEN_BIT_MASK_5) >> (BIT_SIZE * 0x5));
        }

        return indices;
    }
}
