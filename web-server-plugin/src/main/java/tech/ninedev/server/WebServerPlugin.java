package tech.ninedev.server;

import tech.ninedev.server.api.Message;
import tech.ninedev.server.api.Resource;
import tech.ninedev.server.api.Status;
import tech.ninedev.server.api.StatusCode;
import tech.ninedev.server.api.WebServer;
import tech.ninedev.server.controller.StatusController;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

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
public class WebServerPlugin extends JavaPlugin implements Resource, WebServer {
    /** Server base URI. */
    private static final String BASE_URI = "https://127.0.0.1:9999/api";

    /** Grizzly HTTP server. */
    @NonNull
    private final HttpServer httpServer;
    /** Plugin logger. */
    @NonNull
    private final Logger log;
    /** Plugin messages. */
    @NonNull
    private final List<Message> messages;
    /** Registered resources. */
    @NonNull
    private final Map<String, Resource> resources;
    /** Grizzly resource configuration. */
    @NonNull
    private final ResourceConfig resourceConfig;

    /** Plugin status. */
    @NonNull
    private Status status;

    /**
     * Constructor.
     */
    public WebServerPlugin() {
        final JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        provider.setMapper(new JsonMapper());

        log = getSLF4JLogger();
        messages = new ArrayList<>();
        resources = new HashMap<>();
        resourceConfig = new ResourceConfig()
                .register(provider)
                .register(new StatusController(log, resources));
        httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig);
        status = new Status(
                StatusCode.OK,
                "Uninitialized",
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList());

        messages.add(new Message(Level.INFO, "Initialized 9craft Web Server plugin"));
        registerResource("9craft Web Server", this);
    }

    @NonNull
    @Override
    public List<Message> messages() {
        log.trace("Getting messages");

        return Collections.unmodifiableList(messages);
    }

    @Override
    public void onDisable() {
        log.trace("Disabling {}", getName());
        messages.add(new Message(Level.INFO, "Disabling 9craft Web Server plugin"));
        status = new Status(
                StatusCode.OK,
                "Stopped",
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList());

        httpServer.shutdownNow();
    }

    @Override
    public void onEnable() {
        log.trace("Enabling {}", getName());
        messages.add(new Message(Level.INFO, "Enabling 9craft Web Server plugin"));

        try {
            httpServer.start();

            status = new Status(
                    StatusCode.OK,
                    "Running",
                    Collections.emptyList(),
                    Collections.emptyList(),
                    Collections.emptyList());
        } catch (final IOException e) {
            status = new Status(
                    StatusCode.ERROR,
                    "Failed to start HTTP server",
                    List.of(e.getMessage()),
                    Collections.emptyList(),
                    Collections.emptyList());

            throw new RuntimeException("Failed to start web server", e);
        }
    }

    @Override
    public void registerController(@NonNull final Object controller) {
        log.trace("Registering controller {}", controller.getClass().getName());
        messages.add(new Message(Level.INFO, "Registering REST controller"));

        resourceConfig.register(controller);
    }

    @Override
    public void registerHttpHandler(@NonNull final HttpHandler handler, @NonNull final String... mappings) {
        log.trace("Registering handler with mappings {}", (Object[]) mappings);
        messages.add(new Message(Level.INFO, "Registering HTTP handler"));

        httpServer.getServerConfiguration().addHttpHandler(handler, mappings);
    }

    @Override
    public void registerResource(@NonNull final String name, @NonNull final Resource resource) {
        log.trace("Registering resource {}", name);
        messages.add(new Message(Level.INFO, "Registering resource \"" + name + "\""));

        resources.put(name, resource);
    }

    @NonNull
    @Override
    public Status status() {
        log.trace("Getting status");

        return status;
    }
}
