package tech.ninecraft.map.task;

import tech.ninecraft.map.model.nbt.ByteArrayData;
import tech.ninecraft.map.model.nbt.ByteData;
import tech.ninecraft.map.model.nbt.CompoundData;
import tech.ninecraft.map.model.nbt.Data;
import tech.ninecraft.map.model.nbt.DoubleData;
import tech.ninecraft.map.model.nbt.EndData;
import tech.ninecraft.map.model.nbt.FloatData;
import tech.ninecraft.map.model.nbt.IntArrayData;
import tech.ninecraft.map.model.nbt.IntData;
import tech.ninecraft.map.model.nbt.ListData;
import tech.ninecraft.map.model.nbt.LongArrayData;
import tech.ninecraft.map.model.nbt.LongData;
import tech.ninecraft.map.model.nbt.ReadResult;
import tech.ninecraft.map.model.nbt.ShortData;
import tech.ninecraft.map.model.nbt.StringData;
import tech.ninecraft.map.nbt.reader.TagReader;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;

/**
 * Utility to process an NBT file.
 *
 * @since 1.0.0
 */
public class ReadRegionFileTask implements Runnable {
    /** Header section byte size. */
    private static final int SECTOR_SIZE = 4096;

    /** Region file chunk model bytes. */
    private final byte[] data;
    /** Plugin logger. */
    @NonNull
    private final Logger log;
    /** Region file to process. */
    @NonNull
    private final File regionFile;

    /**
     * Constructor.
     *
     * @param regionFile Region file to process.
     * @param log        Plugin logger.
     */
    public ReadRegionFileTask(@NonNull final File regionFile, @NonNull final Logger log) {
        this.data = new byte[Math.toIntExact(regionFile.length())];
        this.log = log;
        this.regionFile = regionFile;
    }

    @Override
    public void run() {
        if (!readRegionFile()) {
            return;
        }

        for (int index = 0; index < SECTOR_SIZE; index += 4) {
            final int offset = getOffset(index);
            final byte sectorCount = data[index + 3];
            final int coordinateBase = index / 4;
            final int x = coordinateBase & 31;
            final int z = coordinateBase / 32;

            if (offset == 0 && sectorCount == 0) {
                continue;
            }

            log.info("Parsing nbt {} chunk ({}, {}) : {} {}", regionFile.getName(), x, z, offset, sectorCount);

            final int adjustedOffset = offset * SECTOR_SIZE;
            final ReadResult<Integer> dataSizeResult = TagReader.INT_READER.readValue(data, adjustedOffset);
            final byte compression = data[adjustedOffset + dataSizeResult.size()];

            log.info("Size: {}, Compression: {}", dataSizeResult.data().value(), compression);

            if (compression != 2) {
                log.error("Unsupported compression type {}", compression);

                continue;
            }

            final byte[] chunkData =
                decompressChunkData(adjustedOffset + dataSizeResult.size() + 1, dataSizeResult.data().value());
            final byte typeID = chunkData[0];
            final Optional<TagReader<?>> reader = TagReader.getReader(typeID);

            if (reader.isEmpty()) {
                log.error("Failed to get tag reader for type {}", typeID);

                break;
            }

            final ReadResult<String> rootName = TagReader.STRING_READER.readValue(chunkData, 1);

            if (rootName.isFailed()) {
                log.error("Failed to read root tag name\n\"{}\"", rootName.error().getMessage());

                break;
            }

            final ReadResult<?> compoundResult = reader.get().readValue(chunkData, rootName.size() + 1);

            if (compoundResult.isSuccess()) {
                processData(compoundResult.data());
            } else {
                log.error("Failed to read chunk model\n\"{}\"", compoundResult.error().getMessage());
            }

            break;
        }
    }

    /* **************************************** Private utility methods ***************************************** */

    /**
     * Decompress chunk model.
     *
     * @param offset Offset of chunk model in {@link ReadRegionFileTask#data}.
     * @param size   Size of chunk model.
     * @return Decompressed chunk model.
     */
    private byte[] decompressChunkData(final int offset, final int size) {
        final Inflater inflater = new Inflater();
        byte[] chunkData = new byte[SECTOR_SIZE];
        int bytesRead = 0;

        inflater.setInput(data, offset, size);

        while (!inflater.finished()) {
            // Resize the results array if needed
            if (bytesRead == chunkData.length) {
                chunkData = Arrays.copyOf(chunkData, chunkData.length * 2);
            }

            try {
                bytesRead += inflater.inflate(chunkData, bytesRead, chunkData.length - bytesRead);
            } catch (final DataFormatException e) {
                log.error("Failed to decompress chunk model", e);

                break;
            }
        }

        return chunkData;
    }

    /**
     * Get chunk offset.
     *
     * @param index Header byte starting index.
     * @return chunk offset.
     */
    private int getOffset(final int index) {
        final byte[] offsetBytes = new byte[4];
        final ByteBuffer offsetBuffer = ByteBuffer.wrap(offsetBytes);

        offsetBytes[0] = 0;
        offsetBytes[1] = data[index];
        offsetBytes[2] = data[index + 1];
        offsetBytes[3] = data[index + 2];

        return offsetBuffer.getInt();
    }

    private void processData(@NonNull final Data<?> data) {
        switch (data) {
            case ByteArrayData byteArrayData -> {
                log.info("Read byte array {}", byteArrayData.value());
            }
            case ByteData byteData -> {
                log.info("Read byte {}", byteData.value());
            }
            case CompoundData compoundData -> {
                log.info(
                    "Read compound x: {}, z: {}",
                    compoundData.value().get("xPos"),
                    compoundData.value().get("zPos"));
            }
            case DoubleData doubleData -> {
                log.info("Read double {}", doubleData.value());
            }
            case EndData endData -> {
                log.info("Read end");
            }
            case FloatData floatData -> {
                log.info("Read float {}", floatData.value());
            }
            case IntArrayData intArrayData -> {
                log.info("Read int array {}", intArrayData.value());
            }
            case IntData intData -> {
                log.info("Read int {}", intData.value());
            }
            case ListData listData -> {
                log.info("Read list {}", listData.value());
            }
            case LongArrayData longArrayData -> {
                log.info("Read long array {}", longArrayData.value());
            }
            case LongData longData -> {
                log.info("Read long {}", longData.value());
            }
            case ShortData shortData -> {
                log.info("Read short {}", shortData.value());
            }
            case StringData stringData -> {
                log.info("Read string {}", stringData.value());
            }
        }
    }

    /**
     * Read the nbt file in to byte arrays.
     *
     * @return {@code true} if the file was read successfully, otherwise {@code false}.
     */
    private boolean readRegionFile() {
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(regionFile))) {
            final int dataBytesRead = inputStream.read(data);

            if (dataBytesRead != data.length) {
                log.error(
                    "Error reading nbt {} model. Read {} bytes but expected {}",
                    regionFile.getName(),
                    dataBytesRead,
                    data.length);

                return false;
            }
        } catch (final IOException e) {
            log.error("Failed to read nbt file", e);

            return false;
        }

        return true;
    }
}
