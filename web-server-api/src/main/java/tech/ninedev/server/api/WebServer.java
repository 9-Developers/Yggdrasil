package tech.ninedev.server.api;

import org.glassfish.grizzly.http.server.HttpHandler;
import org.jspecify.annotations.NonNull;

/**
 * 9craft Web Server API.
 *
 * @since 1.0.0
 */
public interface WebServer {
    /**
     * Register an API controller with the webserver.
     *
     * <p>
     * The controller should be annotated with {@link jakarta.ws.rs.Path} with a unique path that does not conflict with
     * another plugin's APIs.
     * </p>
     *
     * @param controller Jakarta REST API controller.
     */
    void registerController(@NonNull Object controller);

    /**
     * Register an HTTP handler with the webserver.
     *
     * <p>
     * The {@code mappings} should be unique and not conflict with another plugin's mappings.
     * </p>
     *
     * @param handler  HTTP handler.
     * @param mappings Context path mappings.
     */
    void registerHttpHandler(@NonNull HttpHandler handler, @NonNull String... mappings);

    /**
     * Register a resource with the webserver.
     *
     * @param name     Resource name.
     * @param resource Resource.
     */
    void registerResource(@NonNull String name, @NonNull Resource resource);
}
