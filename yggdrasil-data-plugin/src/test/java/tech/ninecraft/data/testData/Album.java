package tech.ninecraft.data.testData;

import tech.ninecraft.data.api.Column;
import tech.ninecraft.data.api.Table;

import java.util.List;

import org.jspecify.annotations.NonNull;

@Table
public record Album(@Column(isPrimaryKey = true) int id, @NonNull String title, @NonNull List<@NonNull Track> tracks) {
}
