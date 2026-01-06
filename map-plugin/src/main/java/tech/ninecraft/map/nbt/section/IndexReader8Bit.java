package tech.ninecraft.map.nbt.section;

/**
 * Read 4 bit indices from section data.
 *
 * @since 1.0.0
 */
public final class IndexReader8Bit implements IndexReader {
    /** Number of bits per index. */
    private static final int BIT_SIZE = 0x8;
    /** Number of indices packed per element. */
    private static final int INDICES_PER_ELEMENT = 64 / BIT_SIZE;

    /** First eight bits mask. */
    private static final long EIGHT_BIT_MASK_0 = 0x00000000000000FFL;
    /** Second eight bits mask. */
    private static final long EIGHT_BIT_MASK_1 = 0x000000000000FF00L;
    /** Third eight bits mask. */
    private static final long EIGHT_BIT_MASK_2 = 0x0000000000FF0000L;
    /** Fourth eight bits mask. */
    private static final long EIGHT_BIT_MASK_3 = 0x00000000FF000000L;
    /** Fifth eight bits mask. */
    private static final long EIGHT_BIT_MASK_4 = 0x000000FF00000000L;
    /** Sixth eight bits mask. */
    private static final long EIGHT_BIT_MASK_5 = 0x0000FF0000000000L;
    /** Seventh eight bits mask. */
    private static final long EIGHT_BIT_MASK_6 = 0x00FF000000000000L;
    /** Eighth eight bits mask. */
    private static final long EIGHT_BIT_MASK_7 = 0xFF00000000000000L;

    /**
     * Package-private constructor.
     */
    IndexReader8Bit() {
    }

    @Override
    public short[] readIndices(final long[] data) {
        final short[] indices = new short[data.length * INDICES_PER_ELEMENT];

        for (int i = 0; i < data.length; ++i) {
            final int j = i * INDICES_PER_ELEMENT;

            indices[j] = (short) (data[i] & EIGHT_BIT_MASK_0 << BIT_SIZE);
            indices[j + 0x1] = (short) ((data[i] & EIGHT_BIT_MASK_1) >> BIT_SIZE);
            indices[j + 0x2] = (short) ((data[i] & EIGHT_BIT_MASK_2) >> (BIT_SIZE * 0x2));
            indices[j + 0x3] = (short) ((data[i] & EIGHT_BIT_MASK_3) >> (BIT_SIZE * 0x3));
            indices[j + 0x4] = (short) ((data[i] & EIGHT_BIT_MASK_4) >> (BIT_SIZE * 0x4));
            indices[j + 0x5] = (short) ((data[i] & EIGHT_BIT_MASK_5) >> (BIT_SIZE * 0x5));
            indices[j + 0x6] = (short) ((data[i] & EIGHT_BIT_MASK_6) >> (BIT_SIZE * 0x6));
            indices[j + 0x7] = (short) ((data[i] & EIGHT_BIT_MASK_7) >> (BIT_SIZE * 0x7));
        }

        return indices;
    }
}
