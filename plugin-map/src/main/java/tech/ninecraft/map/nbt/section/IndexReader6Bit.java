package tech.ninecraft.map.nbt.section;

/**
 * Read 4 bit indices from section data.
 *
 * @since 1.0.0
 */
public final class IndexReader6Bit implements IndexReader {
    /** Number of bits per index. */
    private static final int BIT_SIZE = 0x6;
    /** Number of indices packed per element. */
    private static final int INDICES_PER_ELEMENT = 64 / BIT_SIZE;

    /** First six bits mask. */
    private static final long SIX_BIT_MASK_0 = 0x00000000000003FL;
    /** Second six bits mask. */
    private static final long SIX_BIT_MASK_1 = 0x000000000000FC0L;
    /** Third six bits mask. */
    private static final long SIX_BIT_MASK_2 = 0x00000000003F000L;
    /** Fourth six bits mask. */
    private static final long SIX_BIT_MASK_3 = 0x000000000FC0000L;
    /** Fifth six bits mask. */
    private static final long SIX_BIT_MASK_4 = 0x00000003F000000L;
    /** Sixth six bits mask. */
    private static final long SIX_BIT_MASK_5 = 0x000000FC0000000L;
    /** Seventh six bits mask. */
    private static final long SIX_BIT_MASK_6 = 0x00003F000000000L;
    /** Eighth six bits mask. */
    private static final long SIX_BIT_MASK_7 = 0x000FC0000000000L;
    /** Ninth six bits mask. */
    private static final long SIX_BIT_MASK_8 = 0x03F000000000000L;
    /** Tenth six bits mask. */
    private static final long SIX_BIT_MASK_9 = 0xFC0000000000000L;

    /**
     * Package-private constructor.
     */
    IndexReader6Bit() {
    }

    @Override
    public short[] readIndices(final long[] data) {
        final short[] indices = new short[data.length * INDICES_PER_ELEMENT];

        for (int i = 0; i < data.length; ++i) {
            final int j = i * INDICES_PER_ELEMENT;

            indices[j] = (short) (data[i] & SIX_BIT_MASK_0 << BIT_SIZE);
            indices[j + 0x1] = (short) ((data[i] & SIX_BIT_MASK_1) >> BIT_SIZE);
            indices[j + 0x2] = (short) ((data[i] & SIX_BIT_MASK_2) >> (BIT_SIZE * 0x2));
            indices[j + 0x3] = (short) ((data[i] & SIX_BIT_MASK_3) >> (BIT_SIZE * 0x3));
            indices[j + 0x4] = (short) ((data[i] & SIX_BIT_MASK_4) >> (BIT_SIZE * 0x4));
            indices[j + 0x5] = (short) ((data[i] & SIX_BIT_MASK_5) >> (BIT_SIZE * 0x5));
            indices[j + 0x6] = (short) ((data[i] & SIX_BIT_MASK_6) >> (BIT_SIZE * 0x6));
            indices[j + 0x7] = (short) ((data[i] & SIX_BIT_MASK_7) >> (BIT_SIZE * 0x7));
            indices[j + 0x8] = (short) ((data[i] & SIX_BIT_MASK_8) >> (BIT_SIZE * 0x8));
            indices[j + 0x9] = (short) ((data[i] & SIX_BIT_MASK_9) >> (BIT_SIZE * 0x9));
        }

        return indices;
    }
}
