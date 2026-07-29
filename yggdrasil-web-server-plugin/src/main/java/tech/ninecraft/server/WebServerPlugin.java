package tech.ninecraft.server;

import tech.ninecraft.data.api.Data;
import tech.ninecraft.server.api.WebServer;
import tech.ninecraft.server.api.model.Resource;
import tech.ninecraft.server.api.model.Status;
import tech.ninecraft.server.api.model.StatusCode;
import tech.ninecraft.server.controller.AuthController;
import tech.ninecraft.server.controller.CorsFilter;
import tech.ninecraft.server.controller.WebServerBinder;
import tech.ninecraft.server.controller.WebServerController;
import tech.ninecraft.server.model.RegisteredPlugin;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.validation.constraints.NotBlank;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.inject.hk2.AbstractBinder;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;

/**
 * Paper (Bukkit) plugin.
 *
 * @since 1.0.0
 */
public class WebServerPlugin extends JavaPlugin implements Resource, WebServer {
    /** Is web authentication enabled configuration key. */
    private static final String KEY_AUTH_ENABLED = "authEnabled";
    /** Host configuration key. */
    private static final String KEY_HOST = "host";
    /** Port configuration key. */
    private static final String KEY_PORT = "port";
    /** Scheme configuration key. */
    private static final String KEY_SCHEME = "scheme";
    /** API path. */
    private static final String PATH_API = "/api";
    /** Restricted path mappings. */
    private static final List<String> RESTRICTED_MAPPINGS = List.of("/", "/admin", "/api", "/webserver");

    /** Plugin status error messages. */
    @NonNull
    private final List<String> errorMessages;
    /** Plugin status info messages. */
    @NonNull
    private final List<String> infoMessages;
    /** Plugin logger. */
    @NonNull
    private final Logger log;
    /** Registered plugins. */
    @NonNull
    private final Map<String, RegisteredPlugin> registeredPlugins;
    /** Grizzly resource configuration. */
    @NonNull
    private final ResourceConfig resourceConfig;

    /** Is web authentication enabled? */
    private boolean isAuthEnabled;
    /** Grizzly HTTP server. */
    private HttpServer httpServer;
    /** Plugin status message. */
    @NonNull
    private String message;
    /** Plugin status code. */
    @NonNull
    private StatusCode statusCode;

    /**
     * Constructor.
     */
    public WebServerPlugin() {
        final JacksonJaxbJsonProvider jsonProvider = new JacksonJaxbJsonProvider();
        jsonProvider.setMapper(new JsonMapper());

        isAuthEnabled = false;
        errorMessages = new ArrayList<>();
        infoMessages = new ArrayList<>();
        log = getSLF4JLogger();
        message = "Initialized";
        registeredPlugins = new HashMap<>();
        resourceConfig = new ResourceConfig().register(jsonProvider);
        statusCode = StatusCode.OK;
    }

    /* **************************************** Plugin lifecycle methods ***************************************** */

    @Override
    public void onDisable() {
        statusCode = StatusCode.OK;
        message = "Stopped";

        httpServer.shutdownNow();
    }

    @Override
    public void onEnable() {
        try {
            httpServer = GrizzlyHttpServerFactory.createHttpServer(
                new URI(
                    getConfig().getString(KEY_SCHEME),
                    null,
                    getConfig().getString(KEY_HOST),
                    getConfig().getInt(KEY_PORT),
                    PATH_API,
                    null,
                    null),
                resourceConfig);

            httpServer.start();

            statusCode = StatusCode.OK;
            message = "Running";
            infoMessages.add("Plugin enabled");
        } catch (final IOException | URISyntaxException e) {
            statusCode = StatusCode.ERROR;
            message = "Failed to start HTTP server";
            errorMessages.add(e.getMessage());

            throw new RuntimeException("Failed to start web server", e);
        }
    }

    @Override
    public void onLoad() {
        saveDefaultConfig();

        Data dataPlugin = null;

        try {
            dataPlugin = getPlugin(Data.class);
        } catch (ClassCastException | IllegalArgumentException | IllegalStateException _) {
            log.warn("Yggdrasil Data plugin not found");
        }

        isAuthEnabled = getConfig().getBoolean(KEY_AUTH_ENABLED) && dataPlugin != null;

        registerBinder(this, new WebServerBinder(dataPlugin, isAuthEnabled, log, registeredPlugins));
        registerController(this, AuthController.class);
        registerController(this, WebServerController.class);
        registerController(this, CorsFilter.class);
        registerResource(this, "Yggdrasil Web Server", this);
    }

    /* ****************************************** Webserver API methods ****************************************** */

    @Override
    public boolean isAuthEnabled() {
        return isAuthEnabled;
    }

    @Override
    public <T extends AbstractBinder> void registerBinder(@NonNull final Plugin plugin, @NonNull final T binder) {
        log.trace("Registering binder {}", binder.getClass().getName());

        resourceConfig.register(binder);
    }

    @Override
    public void registerController(@NonNull final Plugin plugin, @NonNull final Class<?> controller) {
        log.trace("Registering controller {}", List.of(controller.getTypeParameters()));

        resourceConfig.register(controller);
    }

    @Override
    public boolean registerHttpHandler(
        @NonNull final Plugin plugin,
        @NonNull final HttpHandler handler,
        @NonNull final String... mappings) {
        log.trace("Registering handler with mappings {}", (Object[]) mappings);

        if (!areMappingsValid(plugin, mappings)) {
            return false;
        }

        final List<String> paths = getPlugin(plugin.getName()).paths();

        paths.addAll(List.of(mappings));
        Collections.sort(paths);
        httpServer.getServerConfiguration().addHttpHandler(handler, mappings);

        return true;
    }

    @Override
    public boolean registerResource(
        @NonNull final Plugin plugin,
        @NotBlank final String name,
        @NonNull final Resource resource) {
        log.trace("Registering resource {}", name);

        final RegisteredPlugin registeredPlugin = getPlugin(plugin.getName());

        if (registeredPlugin.resources().containsKey(name)) {
            return false;
        } else {
            registeredPlugin.resources().put(name, resource);

            return true;
        }
    }

    @NonNull
    @Override
    public Status status() {
        log.trace("Getting status");

        return new Status(
            statusCode,
            message,
            Collections.unmodifiableList(errorMessages),
            Collections.emptyList(),
            Collections.unmodifiableList(infoMessages));
    }

    @Nullable
    @Override
    public Resource unregisterResource(@NonNull final Plugin plugin, @NotBlank final String name) {
        log.trace("Unregistering resource {}", name);

        if (registeredPlugins.containsKey(plugin.getName())) {
            return registeredPlugins.get(plugin.getName()).resources().remove(name);
        } else {
            return null;
        }
    }

    /* ***************************************** Private utility methods ***************************************** */

    /**
     * Check if HTTP path mappings are valid.
     *
     * @param plugin   Plugin registering the mappings.
     * @param mappings Mappings to check.
     * @return {@code true} if mappings are valid, {@code false} otherwise.
     */
    private boolean areMappingsValid(@NonNull final Plugin plugin, @NonNull final String... mappings) {
        final List<String> restrictedMappings = new ArrayList<>();
        // This is a terrible algorithm, but so few mappings should be registered that it doesn't really matter
        final List<String> registeredMappings = new ArrayList<>();

        for (final String mapping : mappings) {
            for (final String restrictedMapping : RESTRICTED_MAPPINGS) {
                if (mapping.equalsIgnoreCase(restrictedMapping)) {
                    restrictedMappings.add(mapping);
                }
            }

            final boolean isAlreadyMapped = registeredPlugins.values()
                .stream()
                .map(RegisteredPlugin::paths)
                .flatMap(List::stream)
                .anyMatch(mapping::equalsIgnoreCase);

            if (isAlreadyMapped) {
                registeredMappings.add(mapping);
            }
        }

        if (!restrictedMappings.isEmpty()) {
            log.warn("Plugin {} attempted to map restricted paths: {}", plugin.getName(), restrictedMappings);

            return false;
        } else if (!registeredMappings.isEmpty()) {
            log.warn("Plugin {} attempted to map already registered paths: {}", plugin.getName(), registeredMappings);

            return false;
        }

        return true;
    }

    /**
     * Get registered plugin.
     *
     * @param plugin Plugin name.
     * @return Registered plugin.
     */
    @NonNull
    private RegisteredPlugin getPlugin(@NonNull final String plugin) {
        if (!registeredPlugins.containsKey(plugin)) {
            final RegisteredPlugin registeredPlugin = new RegisteredPlugin(new ArrayList<>(), new HashMap<>());

            registeredPlugins.put(plugin, registeredPlugin);

            return registeredPlugin;
        } else {
            return registeredPlugins.get(plugin);
        }
    }
}
