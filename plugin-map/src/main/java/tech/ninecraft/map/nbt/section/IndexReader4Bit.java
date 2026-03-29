package tech.ninecraft.map.nbt.section;

/**
 * Read 4 bit indices from section data.
 *
 * @since 1.0.0
 */
public final class IndexReader4Bit implements IndexReader {
    /** Number of bits per index. */
    private static final int BIT_SIZE = 0x4;
    /** Number of indices packed per element. */
    private static final int INDICES_PER_ELEMENT = 64 / BIT_SIZE;

    /** First four bits mask. */
    private static final long FOUR_BIT_MASK_0 = 0x000000000000000FL;
    /** Second four bits mask. */
    private static final long FOUR_BIT_MASK_1 = 0x00000000000000F0L;
    /** Third four bits mask. */
    private static final long FOUR_BIT_MASK_2 = 0x0000000000000F00L;
    /** Fourth four bits mask. */
    private static final long FOUR_BIT_MASK_3 = 0x000000000000F000L;
    /** Fifth four bits mask. */
    private static final long FOUR_BIT_MASK_4 = 0x00000000000F0000L;
    /** Sixth four bits mask. */
    private static final long FOUR_BIT_MASK_5 = 0x0000000000F00000L;
    /** Seventh four bits mask. */
    private static final long FOUR_BIT_MASK_6 = 0x000000000F000000L;
    /** Eighth four bits mask. */
    private static final long FOUR_BIT_MASK_7 = 0x00000000F0000000L;
    /** Ninth four bits mask. */
    private static final long FOUR_BIT_MASK_8 = 0x0000000F00000000L;
    /** Tenth four bits mask. */
    private static final long FOUR_BIT_MASK_9 = 0x000000F000000000L;
    /** Eleventh four bits mask. */
    private static final long FOUR_BIT_MASK_A = 0x00000F0000000000L;
    /** Twelfth four bits mask. */
    private static final long FOUR_BIT_MASK_B = 0x0000F00000000000L;
    /** Thirteenth four bits mask. */
    private static final long FOUR_BIT_MASK_C = 0x000F000000000000L;
    /** Fourteenth four bits mask. */
    private static final long FOUR_BIT_MASK_D = 0x00F0000000000000L;
    /** Fifteenth four bits mask. */
    private static final long FOUR_BIT_MASK_E = 0x0F00000000000000L;
    /** Sixteenth four bits mask. */
    private static final long FOUR_BIT_MASK_F = 0xF000000000000000L;

    /**
     * Package-private constructor.
     */
    IndexReader4Bit() {
    }

    @Override
    public short[] readIndices(final long[] data) {
        final short[] indices = new short[data.length * INDICES_PER_ELEMENT];

        for (int i = 0; i < data.length; ++i) {
            final int j = i * INDICES_PER_ELEMENT;

            indices[j] = (short) (data[i] & FOUR_BIT_MASK_0 << BIT_SIZE);
            indices[j + 0x1] = (short) ((data[i] & FOUR_BIT_MASK_1) >> BIT_SIZE);
            indices[j + 0x2] = (short) ((data[i] & FOUR_BIT_MASK_2) >> (BIT_SIZE * 0x2));
            indices[j + 0x3] = (short) ((data[i] & FOUR_BIT_MASK_3) >> (BIT_SIZE * 0x3));
            indices[j + 0x4] = (short) ((data[i] & FOUR_BIT_MASK_4) >> (BIT_SIZE * 0x4));
            indices[j + 0x5] = (short) ((data[i] & FOUR_BIT_MASK_5) >> (BIT_SIZE * 0x5));
            indices[j + 0x6] = (short) ((data[i] & FOUR_BIT_MASK_6) >> (BIT_SIZE * 0x6));
            indices[j + 0x7] = (short) ((data[i] & FOUR_BIT_MASK_7) >> (BIT_SIZE * 0x7));
            indices[j + 0x8] = (short) ((data[i] & FOUR_BIT_MASK_8) >> (BIT_SIZE * 0x8));
            indices[j + 0x9] = (short) ((data[i] & FOUR_BIT_MASK_9) >> (BIT_SIZE * 0x9));
            indices[j + 0xA] = (short) ((data[i] & FOUR_BIT_MASK_A) >> (BIT_SIZE * 0xA));
            indices[j + 0xB] = (short) ((data[i] & FOUR_BIT_MASK_B) >> (BIT_SIZE * 0xB));
            indices[j + 0xC] = (short) ((data[i] & FOUR_BIT_MASK_C) >> (BIT_SIZE * 0xC));
            indices[j + 0xD] = (short) ((data[i] & FOUR_BIT_MASK_D) >> (BIT_SIZE * 0xD));
            indices[j + 0xE] = (short) ((data[i] & FOUR_BIT_MASK_E) >> (BIT_SIZE * 0xE));
            indices[j + 0xF] = (short) ((data[i] & FOUR_BIT_MASK_F) >> (BIT_SIZE * 0xF));
        }

        return indices;
    }
}
