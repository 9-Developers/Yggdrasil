package tech.ninecraft.data.testData;

import tech.ninecraft.data.api.Table;

import java.util.List;

import org.jspecify.annotations.NonNull;

@Table
public record Artist(
    @NonNull String name,
    @NonNull List<@NonNull Album> albums) {
}
