package tech.ninecraft.map.nbt.section;

/**
 * Read 4 bit indices from section data.
 *
 * @since 1.0.0
 */
public final class IndexReader5Bit implements IndexReader {
    /** Number of bits per index. */
    private static final int BIT_SIZE = 0x5;
    /** Number of indices packed per element. */
    private static final int INDICES_PER_ELEMENT = 64 / BIT_SIZE;

    /** First five bits mask. */
    private static final long FIVE_BIT_MASK_0 = 0x00000000000001FL;
    /** Second five bits mask. */
    private static final long FIVE_BIT_MASK_1 = 0x0000000000003E0L;
    /** Third five bits mask. */
    private static final long FIVE_BIT_MASK_2 = 0x000000000007C00L;
    /** Fourth five bits mask. */
    private static final long FIVE_BIT_MASK_3 = 0x0000000000F8000L;
    /** Fifth five bits mask. */
    private static final long FIVE_BIT_MASK_4 = 0x000000001F00000L;
    /** Sixth five bits mask. */
    private static final long FIVE_BIT_MASK_5 = 0x00000003E000000L;
    /** Seventh five bits mask. */
    private static final long FIVE_BIT_MASK_6 = 0x0000007C0000000L;
    /** Eighth five bits mask. */
    private static final long FIVE_BIT_MASK_7 = 0x00000F800000000L;
    /** Ninth five bits mask. */
    private static final long FIVE_BIT_MASK_8 = 0x0001F0000000000L;
    /** Tenth five bits mask. */
    private static final long FIVE_BIT_MASK_9 = 0x003E00000000000L;
    /** Eleventh five bits mask. */
    private static final long FIVE_BIT_MASK_A = 0x07C000000000000L;
    /** Twelfth five bits mask. */
    private static final long FIVE_BIT_MASK_B = 0xF80000000000000L;

    /**
     * Package-private constructor.
     */
    IndexReader5Bit() {
    }

    @Override
    public short[] readIndices(final long[] data) {
        final short[] indices = new short[data.length * INDICES_PER_ELEMENT];

        for (int i = 0; i < data.length; ++i) {
            final int j = i * INDICES_PER_ELEMENT;

            indices[j] = (short) (data[i] & FIVE_BIT_MASK_0 << BIT_SIZE);
            indices[j + 0x1] = (short) ((data[i] & FIVE_BIT_MASK_1) >> BIT_SIZE);
            indices[j + 0x2] = (short) ((data[i] & FIVE_BIT_MASK_2) >> (BIT_SIZE * 0x2));
            indices[j + 0x3] = (short) ((data[i] & FIVE_BIT_MASK_3) >> (BIT_SIZE * 0x3));
            indices[j + 0x4] = (short) ((data[i] & FIVE_BIT_MASK_4) >> (BIT_SIZE * 0x4));
            indices[j + 0x5] = (short) ((data[i] & FIVE_BIT_MASK_5) >> (BIT_SIZE * 0x5));
            indices[j + 0x6] = (short) ((data[i] & FIVE_BIT_MASK_6) >> (BIT_SIZE * 0x6));
            indices[j + 0x7] = (short) ((data[i] & FIVE_BIT_MASK_7) >> (BIT_SIZE * 0x7));
            indices[j + 0x8] = (short) ((data[i] & FIVE_BIT_MASK_8) >> (BIT_SIZE * 0x8));
            indices[j + 0x9] = (short) ((data[i] & FIVE_BIT_MASK_9) >> (BIT_SIZE * 0x9));
            indices[j + 0xA] = (short) ((data[i] & FIVE_BIT_MASK_A) >> (BIT_SIZE * 0xA));
            indices[j + 0xB] = (short) ((data[i] & FIVE_BIT_MASK_B) >> (BIT_SIZE * 0xB));
        }

        return indices;
    }
}
