package tech.ninedev.server.api;

import java.util.List;

import org.jspecify.annotations.NonNull;

/**
 * Resource status.
 *
 * <p>
 * The message lists ({@code messages}, {@code warnings}, {@code errors}) will display in their own sections.
 * </p>
 *
 * @param code     Status code.
 * @param message  Overall (short) status message.
 * @param messages Information (non-error) messages.
 * @param warnings Warning messages.
 * @param errors   Error messages.
 * @since 1.0.0
 */
public record Status(
        @NonNull StatusCode code,
        @NonNull String message,
        @NonNull List<String> messages,
        @NonNull List<String> warnings,
        @NonNull List<String> errors) {
}
