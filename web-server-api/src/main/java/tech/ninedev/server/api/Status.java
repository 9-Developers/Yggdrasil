package tech.ninedev.server.api;

import java.util.List;

import org.jspecify.annotations.NonNull;

/**
 * Resource status.
 *
 * @param code            Status code.
 * @param message         Overall (short) status message.
 * @param errorMessages   Error messages.
 * @param warningMessages Warning messages.
 * @param infoMessages    Information messages.
 * @since 1.0.0
 */
public record Status(
    @NonNull StatusCode code,
    @NonNull String message,
    @NonNull List<String> errorMessages,
    @NonNull List<String> warningMessages,
    @NonNull List<String> infoMessages) {
}
