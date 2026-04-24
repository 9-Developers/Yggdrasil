package tech.ninecraft.data.testData;

import tech.ninecraft.data.api.Table;

import org.jspecify.annotations.NonNull;

@Table
public record Track(
    @NonNull String title,
    long length,
    int number) {
}
