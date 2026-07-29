package tech.ninecraft.map.nbt.section;

import tech.ninecraft.map.aggregator.IntArrayAggregator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class IndexReader6BitTest {
    private final IndexReader indexReader = IndexReader.getIndexReader(6);

    @CsvFileSource(resources = "/IndexReader6Bit.csv")
    @ParameterizedTest
    void GIVEN_data_WHEN_readIndices_THEN_returnsIndices(
        final long data,
        @AggregateWith(IntArrayAggregator.class) final int[] expected) {
        // When
        final int[] actual = indexReader.readIndices(new long[] { data });

        // Then
        assertArrayEquals(expected, actual, "Indices should equal expected");
    }
}
