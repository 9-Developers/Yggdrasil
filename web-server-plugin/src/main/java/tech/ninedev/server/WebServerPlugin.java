package tech.ninedev.server;

import tech.ninedev.server.api.WebServer;

import java.io.IOException;
import java.net.URI;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.bukkit.plugin.java.JavaPlugin;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;

/**
 * PaperMC (Bukkit) plugin.
 *
 * @since 1.0.0
 */
public class WebServerPlugin extends JavaPlugin implements WebServer {
    /** Server base URI. */
    private static final String BASE_URI = "https://127.0.0.1:9999/api";

    /** Grizzly HTTP server. */
    @NonNull
    private final HttpServer httpServer;
    /** Plugin logger. */
    @NonNull
    private final Logger log;
    /** Grizzly resource configuration. */
    @NonNull
    private final ResourceConfig resourceConfig;

    /**
     * Constructor.
     */
    public WebServerPlugin() {
        final JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        provider.setMapper(new JsonMapper());

        log = getSLF4JLogger();
        resourceConfig = new ResourceConfig().register(provider);
        httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig);
    }

    @Override
    public void onDisable() {
        log.trace("Shutting down {}", getName());

        httpServer.shutdownNow();
    }

    @Override
    public void onEnable() {
        log.trace("Starting {}", getName());

        try {
            httpServer.start();
        } catch (final IOException e) {
            throw new RuntimeException("Failed to start web server", e);
        }
    }

    @Override
    public void registerController(@NonNull final Object controller) {
        log.trace("Registering controller {}", controller.getClass().getName());

        resourceConfig.register(controller);
    }

    @Override
    public void registerHttpHandler(@NonNull final HttpHandler handler, @NonNull final String... mappings) {
        log.trace("Registering handler with mappings {}", (Object[]) mappings);

        httpServer.getServerConfiguration().addHttpHandler(handler, mappings);
    }
}
