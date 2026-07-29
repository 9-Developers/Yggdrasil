package tech.ninecraft.data.testData;

import tech.ninecraft.data.api.Table;

import java.util.List;

import org.jspecify.annotations.NonNull;

@Table
public record Playlist(@NonNull String name, @NonNull List<@NonNull Track> tracks) {
}
