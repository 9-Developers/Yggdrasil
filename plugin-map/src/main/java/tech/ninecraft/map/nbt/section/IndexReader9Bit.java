package tech.ninecraft.map.nbt.section;

/**
 * Read 4 bit indices from section data.
 *
 * @since 1.0.0
 */
public final class IndexReader9Bit implements IndexReader {
    /** Number of bits per index. */
    private static final int BIT_SIZE = 0x9;
    /** Number of indices packed per element. */
    private static final int INDICES_PER_ELEMENT = 64 / BIT_SIZE;

    /** First nine bits mask. */
    private static final long NINE_BIT_MASK_0 = 0x00000000000001FFL;
    /** Second nine bits mask. */
    private static final long NINE_BIT_MASK_1 = 0x000000000003FE00L;
    /** Third nine bits mask. */
    private static final long NINE_BIT_MASK_2 = 0x0000000007FC0000L;
    /** Fourth nine bits mask. */
    private static final long NINE_BIT_MASK_3 = 0x0000000FF8000000L;
    /** Fifth nine bits mask. */
    private static final long NINE_BIT_MASK_4 = 0x00001FF000000000L;
    /** Sixth nine bits mask. */
    private static final long NINE_BIT_MASK_5 = 0x003FE00000000000L;
    /** Seventh nine bits mask. */
    private static final long NINE_BIT_MASK_6 = 0x7FC0000000000000L;

    /**
     * Package-private constructor.
     */
    IndexReader9Bit() {
    }

    @Override
    public short[] readIndices(final long[] data) {
        final short[] indices = new short[data.length * INDICES_PER_ELEMENT];

        for (int i = 0; i < data.length; ++i) {
            final int j = i * INDICES_PER_ELEMENT;

            indices[j] = (short) (data[i] & NINE_BIT_MASK_0 << BIT_SIZE);
            indices[j + 0x1] = (short) ((data[i] & NINE_BIT_MASK_1) >> BIT_SIZE);
            indices[j + 0x2] = (short) ((data[i] & NINE_BIT_MASK_2) >> (BIT_SIZE * 0x2));
            indices[j + 0x3] = (short) ((data[i] & NINE_BIT_MASK_3) >> (BIT_SIZE * 0x3));
            indices[j + 0x4] = (short) ((data[i] & NINE_BIT_MASK_4) >> (BIT_SIZE * 0x4));
            indices[j + 0x5] = (short) ((data[i] & NINE_BIT_MASK_5) >> (BIT_SIZE * 0x5));
            indices[j + 0x6] = (short) ((data[i] & NINE_BIT_MASK_6) >> (BIT_SIZE * 0x6));
        }

        return indices;
    }
}
