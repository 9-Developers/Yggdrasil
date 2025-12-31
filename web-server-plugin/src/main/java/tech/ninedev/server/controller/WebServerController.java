package tech.ninedev.server.controller;

import tech.ninedev.server.api.Resource;
import tech.ninedev.server.api.Status;
import tech.ninedev.server.api.StatusCode;
import tech.ninedev.server.model.PluginResponse;
import tech.ninedev.server.model.RegisteredPlugin;
import tech.ninedev.server.model.StatusResponse;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;

import static tech.ninedev.server.binder.WebServerBinder.PLUGIN_LOGGER_NAME;
import static tech.ninedev.server.binder.WebServerBinder.REGISTERED_PLUGINS_NAME;

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
    /** Registered plugins. */
    @NonNull
    private final Map<String, RegisteredPlugin> registeredPlugins;

    /**
     * Constructor.
     *
     * @param log               Plugin logger.
     * @param registeredPlugins Registered resources.
     */
    @Inject
    public WebServerController(
        @NonNull @Named(PLUGIN_LOGGER_NAME) final Logger log,
        @NonNull @Named(REGISTERED_PLUGINS_NAME) final Map<String, RegisteredPlugin> registeredPlugins) {
        log.info("Initializing plugin controller");

        this.log = log;
        this.registeredPlugins = registeredPlugins;
    }

    /**
     * Get publicly accessible HTTP handler paths.
     *
     * @return Registered HTTP handler paths.
     */
    @GET
    @Path("/paths")
    @NonNull
    public Map<String, List<String>> getPaths() {
        log.trace("Getting registered http handler paths");

        return registeredPlugins.entrySet()
            .stream()
            .collect(Collectors.toUnmodifiableMap(
                Map.Entry::getKey,
                entry -> entry.getValue().paths()));
    }

    /**
     * Get registered HTTP handler paths.
     *
     * @param plugin Plugin name.
     * @return Registered HTTP handler paths.
     */
    @GET
    @Path("/paths/{plugin}")
    @NonNull
    public List<String> getPaths(@NotBlank @PathParam("plugin") final String plugin) {
        log.trace("Getting registered http handler paths for plugin {}", plugin);

        return getPlugin(plugin).paths();
    }

    /**
     * Get all registered plugins.
     *
     * @return All registered plugins.
     */
    @GET
    @Path("/plugins")
    @NonNull
    public List<String> getPlugins() {
        log.trace("Getting plugin names");

        return registeredPlugins.keySet().stream().sorted().toList();
    }

    /**
     * Get plugin.
     *
     * @param plugin Plugin name.
     * @return Plugin.
     */
    @GET
    @Path("/plugins/{plugin}")
    @NonNull
    public PluginResponse getPlugins(@NotBlank @PathParam("plugin") final String plugin) {
        log.trace("Getting plugin {}", plugin);

        final RegisteredPlugin registeredPlugin = getPlugin(plugin);

        return new PluginResponse(registeredPlugin.paths(), getResourceNames(registeredPlugin));
    }

    /**
     * Get all registered resources.
     *
     * @return All registered resources.
     */
    @GET
    @Path("/resources")
    @NonNull
    public Map<String, List<String>> getResources() {
        log.trace("Getting resources");

        return registeredPlugins.entrySet()
            .stream()
            .collect(Collectors.toUnmodifiableMap(
                Map.Entry::getKey,
                entry -> getResourceNames(entry.getKey())));
    }

    /**
     * Get registered resources for {@code plugin}.
     *
     * @param plugin Plugin name.
     * @return Registered resources for {@code plugin}.
     */
    @GET
    @Path("/resources/{plugin}")
    @NonNull
    public List<String> getResources(@NonNull @PathParam("plugin") final String plugin) {
        log.trace("Getting resources for plugin {}", plugin);

        return getResourceNames(plugin);
    }

    /**
     * Get resource status.
     *
     * @return Status of all registered resources.
     */
    @GET
    @Path("/status")
    @NonNull
    public Map<String, StatusResponse> getStatus() {
        log.trace("Getting resource statuses");

        return registeredPlugins.entrySet()
            .stream()
            .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, this::getPluginStatus));
    }

    /**
     * Get status of {@code plugin}.
     *
     * @param plugin Plugin name.
     * @return Status of {@code plugin}.
     */
    @GET
    @Path("/status/{plugin}")
    @NonNull
    public StatusResponse getStatus(@NonNull @PathParam("plugin") final String plugin) {
        log.trace("Getting resource statuses for plugin {}", plugin);

        return getPluginStatus(getPlugin(plugin));
    }

    /**
     * Get status of {@code resource}.
     *
     * @param plugin   Plugin name.
     * @param resource Resource name.
     * @return Status of {@code resource}.
     */
    @GET
    @Path("/status/{plugin}/{resource}")
    @NonNull
    public Status getStatus(
        @NonNull @PathParam("plugin") final String plugin,
        @NonNull @PathParam("resource") final String resource) {
        log.trace("Getting status for plugin {} and resource {}", plugin, resource);

        return getResource(plugin, resource).status();
    }

    /* ***************************************** Private utility methods ***************************************** */

    /**
     * Get registered plugin by name.
     *
     * @param plugin Plugin name.
     * @return Registered plugin.
     * @throws NotFoundException If the plugin is not found.
     */
    @NonNull
    private RegisteredPlugin getPlugin(@NonNull final String plugin) {
        log.trace("Getting registered plugin {}", plugin);

        final RegisteredPlugin registeredPlugin = registeredPlugins.get(plugin);

        if (registeredPlugin == null) {
            throw new NotFoundException("Plugin \"" + plugin + "\" not found");
        }

        return registeredPlugin;
    }

    /**
     * Get plugin status.
     *
     * @param entry Plugin resources.
     * @return Plugin status.
     */
    @NonNull
    private StatusResponse getPluginStatus(final Map.Entry<String, RegisteredPlugin> entry) {
        log.trace("Getting status for plugin {}", entry.getKey());

        return getPluginStatus(entry.getValue());
    }

    /**
     * Get plugin status.
     *
     * @param registeredPlugin Registered plugin.
     * @return Plugin status.
     */
    @NonNull
    private StatusResponse getPluginStatus(@NonNull final RegisteredPlugin registeredPlugin) {
        log.trace("Getting status for registered plugin");

        final Map<String, Status> errors = HashMap.newHashMap(registeredPlugin.resources().size());
        final Map<String, Status> warnings = HashMap.newHashMap(registeredPlugin.resources().size());
        final Map<String, Status> oks = HashMap.newHashMap(registeredPlugin.resources().size());

        registeredPlugin.resources().forEach((name, resource) -> {
            switch (resource.status().code()) {
                case StatusCode.OK -> oks.put(name, resource.status());
                case StatusCode.WARNING -> warnings.put(name, resource.status());
                case StatusCode.ERROR -> errors.put(name, resource.status());
            }
        });

        return new StatusResponse(
            Collections.unmodifiableMap(errors),
            Collections.unmodifiableMap(warnings),
            Collections.unmodifiableMap(oks));
    }

    /**
     * Get resource by name.
     *
     * @param plugin   Plugin name.
     * @param resource Resource name.
     * @return Registered plugin resource.
     * @throws NotFoundException If the resource is not found.
     */
    @NonNull
    private Resource getResource(@NonNull final String plugin, @NonNull final String resource) {
        log.trace("Getting resource {} for plugin {}", resource, plugin);

        final Resource pluginResource = getPlugin(plugin).resources().get(resource);

        if (pluginResource == null) {
            throw new NotFoundException("Resource \"" + resource + "\" not found in plugin \"" + plugin + "\"");
        }

        return pluginResource;
    }

    /**
     * Get resource names for a plugin.
     *
     * @param plugin Plugin name.
     * @return Resource names.
     */
    @NonNull
    private List<String> getResourceNames(@NonNull final String plugin) {
        log.trace("Getting resource names for plugin {}", plugin);

        return getResourceNames(getPlugin(plugin));
    }

    /**
     * Get resource names for a plugin.
     *
     * @param registeredPlugin Registered plugin.
     * @return Resource names.
     */
    @NonNull
    private List<String> getResourceNames(@NonNull final RegisteredPlugin registeredPlugin) {
        log.trace("Getting resource names for plugin");

        return registeredPlugin.resources().keySet().stream().sorted().toList();
    }
}
