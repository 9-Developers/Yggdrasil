package tech.ninecraft.map.nbt.section;

/**
 * Read 4 bit indices from section data.
 *
 * @since 1.0.0
 */
public final class IndexReaderBBit implements IndexReader {
    /** Number of bits per index. */
    private static final int BIT_SIZE = 0xB;
    /** Number of indices packed per element. */
    private static final int INDICES_PER_ELEMENT = 64 / BIT_SIZE;

    /** First eleven bits mask. */
    private static final long ELEVEN_BIT_MASK_0 = 0x00000000000007FFL;
    /** Second eleven bits mask. */
    private static final long ELEVEN_BIT_MASK_1 = 0x00000000003FF800L;
    /** Third eleven bits mask. */
    private static final long ELEVEN_BIT_MASK_2 = 0x00000001FFC00000L;
    /** Fourth eleven bits mask. */
    private static final long ELEVEN_BIT_MASK_3 = 0x00000FFE00000000L;
    /** Fifth eleven bits mask. */
    private static final long ELEVEN_BIT_MASK_4 = 0x007FF00000000000L;
    /** Sixth eleven bits mask. */
    private static final long ELEVEN_BIT_MASK_5 = 0xFF80000000000000L;

    /**
     * Package-private constructor.
     */
    IndexReaderBBit() {
    }

    @Override
    public short[] readIndices(final long[] data) {
        final short[] indices = new short[data.length * INDICES_PER_ELEMENT];

        for (int i = 0; i < data.length; ++i) {
            final int j = i * INDICES_PER_ELEMENT;

            indices[j] = (short) (data[i] & ELEVEN_BIT_MASK_0 << BIT_SIZE);
            indices[j + 0x1] = (short) ((data[i] & ELEVEN_BIT_MASK_1) >> BIT_SIZE);
            indices[j + 0x2] = (short) ((data[i] & ELEVEN_BIT_MASK_2) >> (BIT_SIZE * 0x2));
            indices[j + 0x3] = (short) ((data[i] & ELEVEN_BIT_MASK_3) >> (BIT_SIZE * 0x3));
            indices[j + 0x4] = (short) ((data[i] & ELEVEN_BIT_MASK_4) >> (BIT_SIZE * 0x4));
            indices[j + 0x5] = (short) ((data[i] & ELEVEN_BIT_MASK_5) >> (BIT_SIZE * 0x5));
        }

        return indices;
    }
}
