package tech.ninecraft.map.nbt.section;

import jdk.incubator.vector.LongVector;
import jdk.incubator.vector.VectorOperators;

/**
 * Read indices from NBT data.
 *
 * @since 1.0.0
 */
public class IndexReader {
    /** Index readers by bit size. */
    private static final IndexReader[] INDEX_READERS = new IndexReader[9];

    /** First batch element count. */
    private final int batchElementCountFirst;
    /** Second batch element count. */
    private final int batchElementCountSecond;
    /** First four-bit mask vector. */
    private final LongVector bitMasksFirst;
    /** Second four-bit mask vector. */
    private final LongVector bitMasksSecond;
    /** First bit shift vector. */
    private final LongVector bitShiftsFirst;
    /** Second bit shift vector. */
    private final LongVector bitShiftsSecond;
    /** Number of indices packed per element. */
    private final int indicesPerElement;

    /**
     * Constructor.
     *
     * @param bitSize Bit-size of indices in NBT data.
     */
    private IndexReader(final int bitSize) {
        final long bitMask = Math.toIntExact(Math.round(Math.pow(2, bitSize))) - 1;

        indicesPerElement = 64 / bitSize;
        batchElementCountFirst = Math.min(8, indicesPerElement);
        batchElementCountSecond = Math.clamp(indicesPerElement - batchElementCountFirst, 0, 8);
        bitMasksFirst = LongVector.fromArray(
            LongVector.SPECIES_512,
            new long[] { bitMask, bitMask << bitSize, bitMask << (bitSize * 2), bitMask << (bitSize * 3),
                bitMask << (bitSize * 4), bitMask << (bitSize * 5), bitMask << (bitSize * 6),
                bitMask << (bitSize * 7), },
            0);
        bitMasksSecond = LongVector.fromArray(
            LongVector.SPECIES_512,
            new long[] { bitMask << (bitSize * 8), bitMask << (bitSize * 9), bitMask << (bitSize * 10),
                bitMask << (bitSize * 11), bitMask << (bitSize * 12), bitMask << (bitSize * 13),
                bitMask << (bitSize * 14), bitMask << (bitSize * 15), },
            0);
        bitShiftsFirst = LongVector.fromArray(
            LongVector.SPECIES_512,
            new long[] { 0, bitSize, bitSize * 2L, bitSize * 3L, bitSize * 4L, bitSize * 5L, bitSize * 6L,
                bitSize * 7L, },
            0);
        bitShiftsSecond = LongVector.fromArray(
            LongVector.SPECIES_512,
            new long[] { bitSize * 8L, bitSize * 9L, bitSize * 10L, bitSize * 11L, bitSize * 12L, bitSize * 13L,
                bitSize * 14L, bitSize * 15L, },
            0);
    }

    /**
     * Get an index reader for {@code bitSize}.
     *
     * @param bitSize Bit-size of indices in NBT data.
     * @return Index reader for {@code bitSize}.
     */
    public static IndexReader getIndexReader(final int bitSize) {
        if (bitSize < 4 || bitSize > 12) {
            throw new IllegalArgumentException("Bit size must be in the range [4, 12]");
        }

        final int index = bitSize - 4;

        if (INDEX_READERS[index] == null) {
            INDEX_READERS[index] = new IndexReader(bitSize);
        }

        return INDEX_READERS[index];
    }

    /**
     * Read (unpack) indices from section data.
     *
     * @param data Section data.
     * @return Unpacked indices.
     */
    public int[] readIndices(final long[] data) {
        final int[] results = new int[data.length * indicesPerElement];

        for (int i = 0; i < data.length; ++i) {
            final long value = data[i];

            final LongVector indexBitsFirst = bitMasksFirst.and(value);
            final LongVector shiftedBitsFirst = indexBitsFirst.lanewise(VectorOperators.LSHR, bitShiftsFirst);
            final int[] indicesFirst = shiftedBitsFirst.convert(VectorOperators.L2I, 0).toIntArray();

            System.arraycopy(indicesFirst, 0, results, i * indicesPerElement, batchElementCountFirst);

            if (batchElementCountSecond > 0) {
                final LongVector indexBits2 = bitMasksSecond.and(value);
                final LongVector shiftedBits2 = indexBits2.lanewise(VectorOperators.LSHR, bitShiftsSecond);
                final int[] indices2 = shiftedBits2.convert(VectorOperators.L2I, 0).toIntArray();

                System.arraycopy(
                    indices2,
                    0,
                    results,
                    (i * indicesPerElement) + batchElementCountFirst,
                    batchElementCountSecond);
            }
        }

        return results;
    }
}
