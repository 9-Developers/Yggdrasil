package tech.ninedev.server.controller;

import tech.ninedev.server.WebServerPlugin;
import tech.ninedev.server.api.Message;
import tech.ninedev.server.api.Resource;
import tech.ninedev.server.api.Status;
import tech.ninedev.server.api.StatusCode;
import tech.ninedev.server.model.StatusResponse;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;

/**
 * Plugin status controller.
 *
 * @since 1.0.0
 */
@Path("/webserver")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class WebServerController {
    /** Plugin logger. */
    @NonNull
    private final Logger log;
    /** Registered resources. */
    @NonNull
    private final Map<String, Resource> resources;

    /**
     * Constructor.
     *
     * @param log       Plugin logger.
     * @param resources Registered resources.
     */
    @Inject
    public WebServerController(
            @NonNull @Named(WebServerPlugin.PLUGIN_LOGGER_NAME) final Logger log,
            @NonNull @Named(WebServerPlugin.PLUGIN_RESOURCES_NAME) final Map<String, Resource> resources) {
        this.log = log;
        this.resources = resources;
    }

    /**
     * Get all resource messages.
     *
     * @return All resource messages.
     */
    @GET
    @Path("/messages")
    @NonNull
    public Map<String, List<Message>> getMessages() {
        final Map<String, List<Message>> messages = HashMap.newHashMap(resources.size());

        for (final Map.Entry<String, Resource> entry : resources.entrySet()) {
            final String name = entry.getKey();
            final Resource resource = entry.getValue();

            messages.put(name, resource.messages());
        }

        return messages;
    }

    /**
     * Get messages for {@code resource}.
     *
     * @param resource Resource name.
     * @return Messages for {@code resource}.
     */
    @GET
    @Path("/messages/{resource}")
    @NonNull
    public List<Message> getMessages(@NonNull @PathParam("resource") final String resource) {
        if (resources.containsKey(resource)) {
            return resources.get(resource).messages();
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * Get resource status.
     *
     * @return Status of all registered resources.
     */
    @GET
    @Path("/status")
    @NonNull
    public StatusResponse getStatus() {
        log.trace("Getting resource statuses");

        final Map<String, Status> errors = HashMap.newHashMap(resources.size());
        final Map<String, Status> warnings = HashMap.newHashMap(resources.size());
        final Map<String, Status> oks = HashMap.newHashMap(resources.size());

        for (final Map.Entry<String, Resource> entry : resources.entrySet()) {
            final String name = entry.getKey();
            final Resource resource = entry.getValue();
            final Status status = resource.status();

            if (status.code() == StatusCode.ERROR) {
                errors.put(name, status);
            } else if (status.code() == StatusCode.WARNING) {
                warnings.put(name, status);
            } else {
                oks.put(name, status);
            }
        }

        return new StatusResponse(errors, warnings, oks);
    }

    /**
     * Get status of {@code resource}.
     *
     * @param resource Resource name.
     * @return Status of {@code resource}.
     */
    @GET
    @Path("/status/{resource}")
    @NonNull
    public Status getStatus(@NonNull @PathParam("resource") final String resource) {
        if (resources.containsKey(resource)) {
            return resources.get(resource).status();
        } else {
            return new Status(
                    StatusCode.ERROR,
                    "Resource not found",
                    Collections.emptyList(),
                    Collections.emptyList(),
                    Collections.emptyList());
        }
    }
}
