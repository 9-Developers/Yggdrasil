package tech.ninecraft.map.nbt.data;

import org.jspecify.annotations.NonNull;

/**
 * NBT data interface.
 *
 * @param <T> Data type.
 * @since 1.0.0
 */
public sealed interface Data<T>
        permits ByteArrayData, ByteData, CompoundData, DoubleData, EndData, FloatData, IntArrayData, IntData, ListData,
        LongArrayData, LongData, ShortData, StringData {
    /**
     * Get tag value.
     *
     * @return Tag value.
     */
    @NonNull
    T value();
}
