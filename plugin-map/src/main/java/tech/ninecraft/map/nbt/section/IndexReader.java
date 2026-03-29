package tech.ninecraft.map.nbt.section;

import java.util.Optional;

/**
 * Interface for reading indices from section data.
 *
 * @since 1.0.0
 */
public sealed interface IndexReader
        permits IndexReader4Bit, IndexReader5Bit, IndexReader6Bit, IndexReader7Bit, IndexReader8Bit, IndexReader9Bit,
        IndexReaderABit, IndexReaderBBit, IndexReaderCBit {

    /** Index reader for 4-bit indices. */
    IndexReader4Bit INDEX_READER_4_BIT = new IndexReader4Bit();
    /** Index reader for 5-bit indices. */
    IndexReader5Bit INDEX_READER_5_BIT = new IndexReader5Bit();
    /** Index reader for 6-bit indices. */
    IndexReader6Bit INDEX_READER_6_BIT = new IndexReader6Bit();
    /** Index reader for 7-bit indices. */
    IndexReader7Bit INDEX_READER_7_BIT = new IndexReader7Bit();
    /** Index reader for 8-bit indices. */
    IndexReader8Bit INDEX_READER_8_BIT = new IndexReader8Bit();
    /** Index reader for 9-bit indices. */
    IndexReader9Bit INDEX_READER_9_BIT = new IndexReader9Bit();
    /** Index reader for 10-bit indices. */
    IndexReaderABit INDEX_READER_A_BIT = new IndexReaderABit();
    /** Index reader for 11-bit indices. */
    IndexReaderBBit INDEX_READER_B_BIT = new IndexReaderBBit();
    /** Index reader for 12-bit indices. */
    IndexReaderCBit INDEX_READER_C_BIT = new IndexReaderCBit();

    /* ***************************************** Static utility methods ***************************************** */

    /**
     * Get the appropriate index reader based on palette length.
     *
     * @param paletteLength Palette length.
     * @return Index reader for section data.
     */
    static Optional<IndexReader> getIndexReader(final int paletteLength) {
        if (paletteLength <= 16) {
            return Optional.of(INDEX_READER_4_BIT);
        } else if (paletteLength <= 32) {
            return Optional.of(INDEX_READER_5_BIT);
        } else if (paletteLength <= 64) {
            return Optional.of(INDEX_READER_6_BIT);
        } else if (paletteLength <= 128) {
            return Optional.of(INDEX_READER_7_BIT);
        } else if (paletteLength <= 256) {
            return Optional.of(INDEX_READER_8_BIT);
        } else if (paletteLength <= 512) {
            return Optional.of(INDEX_READER_9_BIT);
        } else if (paletteLength <= 1024) {
            return Optional.of(INDEX_READER_A_BIT);
        } else if (paletteLength <= 2048) {
            return Optional.of(INDEX_READER_B_BIT);
        } else if (paletteLength <= 4096) {
            return Optional.of(INDEX_READER_C_BIT);
        } else {
            return Optional.empty();
        }
    }

    /* ******************************************** Abstract methods ******************************************** */

    /**
     * Read (unpack) indices from section data.
     *
     * <p>
     * There are fewer than 32,767 blocks in minecraft so we can safely fit each index in a short.
     * </p>
     *
     * @param data Section data.
     * @return Unpacked indices.
     */
    short[] readIndices(long[] data);
}
