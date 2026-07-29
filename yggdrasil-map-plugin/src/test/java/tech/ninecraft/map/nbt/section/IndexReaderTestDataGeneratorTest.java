package tech.ninecraft.map.nbt.section;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class IndexReaderTestDataGeneratorTest {

    @Disabled
    @Test
    void generateTestData() {
        generateTestData(4);
        generateTestData(5);
        generateTestData(6);
        generateTestData(7);
        generateTestData(8);
        generateTestData(9);
        generateTestData(10);
        generateTestData(11);
        generateTestData(12);
    }

    /* **************************************** Private utility methods ***************************************** */

    private void generateTestData(final int bitSize) {
        final int count = 64 / bitSize;
        final long max = Math.toIntExact(Math.round(Math.pow(2, bitSize)));

        try (
            RandomAccessFile stream = new RandomAccessFile(String.format("IndexReader%xBit.csv", bitSize), "rw");
            FileChannel channel = stream.getChannel()) {

            for (int i = 0; i < max; ++i) {
                long value = 0;
                final List<String> expected = new ArrayList<>(count);

                for (int j = 0; j < count; ++j) {
                    value += ((long) (i + j) % max) << (bitSize * j);
                    expected.add(String.format("%d", (i + j) % max));
                }

                final String line = String.format(
                    "%d, %s%s",
                    value,
                    String.join(", ", expected),
                    System.lineSeparator());
                final byte[] lineBytes = line.getBytes();
                final ByteBuffer buffer = ByteBuffer.allocate(lineBytes.length);

                buffer.put(lineBytes);
                buffer.flip();
                channel.write(buffer);
            }
        } catch (final IOException e) {
            fail("Failed to write file", e);
        }
    }
}
