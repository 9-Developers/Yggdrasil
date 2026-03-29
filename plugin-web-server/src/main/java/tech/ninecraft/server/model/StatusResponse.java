package tech.ninecraft.server.model;

import tech.ninecraft.server.api.Status;
import tech.ninecraft.server.api.StatusCode;

import java.util.Map;

import org.jspecify.annotations.NonNull;

/**
 * Status API response.
 *
 * @param errors   Resources with {@link StatusCode#ERROR} status.
 * @param warnings Resources with {@link StatusCode#WARNING} status.
 * @param oks      Resources with {@link StatusCode#OK} status.
 */
public record StatusResponse(
    @NonNull Map<String, Status> errors,
    @NonNull Map<String, Status> warnings,
    @NonNull Map<String, Status> oks) {
}
