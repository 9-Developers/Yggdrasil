package tech.ninecraft.map.nbt.section;

/**
 * Read 4 bit indices from section data.
 *
 * @since 1.0.0
 */
public final class IndexReader7Bit implements IndexReader {
    /** Number of bits per index. */
    private static final int BIT_SIZE = 0x7;
    /** Number of indices packed per element. */
    private static final int INDICES_PER_ELEMENT = 64 / BIT_SIZE;

    /** First seven bits mask. */
    private static final long SEVEN_BIT_MASK_0 = 0x000000000000007FL;
    /** Second seven bits mask. */
    private static final long SEVEN_BIT_MASK_1 = 0x0000000000003F80L;
    /** Third seven bits mask. */
    private static final long SEVEN_BIT_MASK_2 = 0x00000000001FC000L;
    /** Fourth seven bits mask. */
    private static final long SEVEN_BIT_MASK_3 = 0x000000000FE00000L;
    /** Fifth seven bits mask. */
    private static final long SEVEN_BIT_MASK_4 = 0x00000007F0000000L;
    /** Sixth seven bits mask. */
    private static final long SEVEN_BIT_MASK_5 = 0x000003F800000000L;
    /** Seventh seven bits mask. */
    private static final long SEVEN_BIT_MASK_6 = 0x0001FC0000000000L;
    /** Eighth seven bits mask. */
    private static final long SEVEN_BIT_MASK_7 = 0x00FE000000000000L;
    /** Ninth seven bits mask. */
    private static final long SEVEN_BIT_MASK_8 = 0x7F00000000000000L;

    /**
     * Package-private constructor.
     */
    IndexReader7Bit() {
    }

    @Override
    public short[] readIndices(final long[] data) {
        final short[] indices = new short[data.length * INDICES_PER_ELEMENT];

        for (int i = 0; i < data.length; ++i) {
            final int j = i * INDICES_PER_ELEMENT;

            indices[j] = (short) (data[i] & SEVEN_BIT_MASK_0 << BIT_SIZE);
            indices[j + 0x1] = (short) ((data[i] & SEVEN_BIT_MASK_1) >> BIT_SIZE);
            indices[j + 0x2] = (short) ((data[i] & SEVEN_BIT_MASK_2) >> (BIT_SIZE * 0x2));
            indices[j + 0x3] = (short) ((data[i] & SEVEN_BIT_MASK_3) >> (BIT_SIZE * 0x3));
            indices[j + 0x4] = (short) ((data[i] & SEVEN_BIT_MASK_4) >> (BIT_SIZE * 0x4));
            indices[j + 0x5] = (short) ((data[i] & SEVEN_BIT_MASK_5) >> (BIT_SIZE * 0x5));
            indices[j + 0x6] = (short) ((data[i] & SEVEN_BIT_MASK_6) >> (BIT_SIZE * 0x6));
            indices[j + 0x7] = (short) ((data[i] & SEVEN_BIT_MASK_7) >> (BIT_SIZE * 0x7));
            indices[j + 0x8] = (short) ((data[i] & SEVEN_BIT_MASK_8) >> (BIT_SIZE * 0x8));
        }

        return indices;
    }
}
