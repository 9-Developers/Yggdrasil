package tech.ninecraft.server.api;

import tech.ninecraft.server.api.model.Resource;

import jakarta.validation.constraints.NotBlank;
import org.bukkit.plugin.Plugin;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.jersey.inject.hk2.AbstractBinder;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Yggdrasil Web Server API.
 *
 * @since 1.0.0
 */
public interface WebServer {
    /**
     * Check if web authentication is enabled.
     *
     * <p>
     *     Requires the {@code authEnabled} config to be {@code true} and Yggdrasil Data plugin to be present.
     * </p>
     *
     * @return {@code true} if web authentication is enabled, otherwise {@code false}.
     */
    boolean isAuthEnabled();

    /**
     * Register a resource binder with the webserver.
     *
     * @param plugin Plugin registering the resource binder.
     * @param binder Resource binder.
     * @param <T>    Type of the resource binder.
     * @see WebServer
     */
    <T extends AbstractBinder> void registerBinder(@NonNull Plugin plugin, @NonNull T binder);

    /**
     * Register an API controller with the webserver.
     *
     * <p>
     * A controller is a class annotated with {@link jakarta.ws.rs.Path}. The path should be unique so that it does not
     * conflict with another plugin's controllers.
     * </p>
     *
     * <p>
     * Anything can be registered with the webserver, but we recommend registering either a controller class
     * ({@code PluginController.class}) or an instance of {@link org.glassfish.jersey.inject.hk2.AbstractBinder}
     * configured with your controller and its dependencies.
     * </p>
     *
     * @param plugin          Plugin registering the controller.
     * @param controllerClass Jakarta REST API controller class.
     * @see WebServer
     */
    void registerController(@NonNull Plugin plugin, @NonNull Class<?> controllerClass);

    /**
     * Register an HTTP handler with the webserver.
     *
     * <p>
     * The {@code mappings} should be unique and not conflict with another plugin's mappings.
     * </p>
     *
     * @param plugin   Plugin registering the handler.
     * @param handler  HTTP handler.
     * @param mappings Context path mappings.
     * @return {@code true} if the handler was registered successfully, {@code false} otherwise.
     */
    boolean registerHttpHandler(@NonNull Plugin plugin, @NonNull HttpHandler handler, @NonNull String... mappings);

    /**
     * Register a resource with the webserver.
     *
     * @param plugin   Plugin registering the resource.
     * @param name     Resource name.
     * @param resource Resource.
     * @return {@code true} if the resource was registered successfully, {@code false} otherwise.
     */
    boolean registerResource(@NonNull Plugin plugin, @NotBlank String name, @NonNull Resource resource);

    /**
     * Unregister a resource with the webserver.
     *
     * @param plugin Plugin unregistering the resource.
     * @param name   Resource name.
     * @return Unregistered resource, or {@code null} if no resource was found.
     */
    @Nullable
    Resource unregisterResource(@NonNull Plugin plugin, @NotBlank String name);
}
