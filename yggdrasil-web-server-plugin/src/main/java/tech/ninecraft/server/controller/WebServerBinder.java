package tech.ninecraft.server.controller;

import tech.ninecraft.data.api.Data;
import tech.ninecraft.server.model.RegisteredPlugin;

import java.util.Map;

import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.jersey.inject.hk2.AbstractBinder;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;

/**
 * Web server plugin resource binder.
 *
 * @since 1.0.0
 */
public class WebServerBinder extends AbstractBinder {
    /** Data plugin injection name. */
    public static final String DATA_PLUGIN_NAME = "Yggdrasil web server data plugin";
    /** Is web authentication enabled injection name. */
    public static final String IS_AUTH_ENABLED_NAME = "Yggdrasil web server authentication enabled";
    /** Plugin logger injection name. */
    public static final String PLUGIN_LOGGER_NAME = "Yggdrasil web server logger";
    /** Plugin logger injection name. */
    public static final String REGISTERED_PLUGINS_NAME = "Yggdrasil web server plugins";

    /** (Optional) Data plugin. */
    @Nullable
    private final Data dataPlugin;
    /** Is web authentication enabled? */
    private final boolean isAuthEnabled;
    /** Plugin logger. */
    @NonNull
    private final Logger log;
    /** Registered plugins. */
    @NonNull
    private final Map<String, RegisteredPlugin> registeredPlugins;

    /**
     * Constructor.
     *
     * @param dataPlugin        (Optional) Data plugin.
     * @param isAuthEnabled     Is web authentication enabled?
     * @param log               Plugin logger.
     * @param registeredPlugins Registered resources.
     */
    public WebServerBinder(
        @Nullable final Data dataPlugin,
        final boolean isAuthEnabled,
        @NonNull final Logger log,
        @NonNull final Map<String, RegisteredPlugin> registeredPlugins) {
        this.dataPlugin = dataPlugin;
        this.isAuthEnabled = isAuthEnabled;
        this.log = log;
        this.registeredPlugins = registeredPlugins;
    }

    @Override
    protected void configure() {
        log.trace("Configuring binder");

        bind(dataPlugin).to(Data.class).named(DATA_PLUGIN_NAME);
        bind(isAuthEnabled).to(Boolean.class).named(IS_AUTH_ENABLED_NAME);
        bind(log).to(Logger.class).named(PLUGIN_LOGGER_NAME);
        bind(registeredPlugins).to(new TypeLiteral<Map<String, RegisteredPlugin>>() {
        }.getType()).named(REGISTERED_PLUGINS_NAME);
    }
}
