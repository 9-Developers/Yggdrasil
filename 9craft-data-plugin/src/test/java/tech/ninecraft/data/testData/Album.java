package tech.ninecraft.data.testData;

import tech.ninecraft.data.api.ID;
import tech.ninecraft.data.api.Table;

import java.util.List;

import org.jspecify.annotations.NonNull;

@Table
public record Album(@ID int id, @NonNull String title, @NonNull List<@NonNull Track> tracks) {
}
