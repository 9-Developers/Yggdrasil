package tech.ninedev.server.binder;

import tech.ninedev.server.model.RegisteredPlugin;

import java.util.Map;

import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.jersey.inject.hk2.AbstractBinder;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;

/**
 * Web server plugin resource binder.
 *
 * @since 1.0.0
 */
public class WebServerBinder extends AbstractBinder {
    /** Plugin logger injection name. */
    public static final String PLUGIN_LOGGER_NAME = "9craft Web Server Logger";
    /** Plugin logger injection name. */
    public static final String REGISTERED_PLUGINS_NAME = "9craft Web Server Plugins";

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
    public WebServerBinder(
        @NonNull final Logger log,
        @NonNull final Map<String, RegisteredPlugin> registeredPlugins) {
        this.log = log;
        this.registeredPlugins = registeredPlugins;
    }

    @Override
    protected void configure() {
        log.info("Configuring binder");

        bind(log).to(Logger.class).named(PLUGIN_LOGGER_NAME);
        bind(registeredPlugins)
            .to(new TypeLiteral<Map<String, RegisteredPlugin>>() {
            }.getType())
            .named(REGISTERED_PLUGINS_NAME);
    }
}
