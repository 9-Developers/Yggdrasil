package tech.ninecraft.map.controller;

import org.glassfish.jersey.inject.hk2.AbstractBinder;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;

/**
 * Map plugin resource binder.
 *
 * @since 1.0.0
 */
public class MapBinder extends AbstractBinder {
    /** Plugin logger injection name. */
    public static final String PLUGIN_LOGGER_NAME = "Yggdrasil Map Logger";
    /** Plugin logger injection name. */
    public static final String MOTD_NAME = "Yggdrasil Map MOTD";

    /** Plugin logger. */
    @NonNull
    private final Logger log;
    /** Message of the day. */
    @NonNull
    private final String motd;

    /**
     * Constructor.
     *
     * @param log  Plugin logger.
     * @param motd Message of the day.
     */
    public MapBinder(@NonNull final Logger log, @NonNull final String motd) {
        this.log = log;
        this.motd = motd;
    }

    @Override
    protected void configure() {
        log.trace("Configuring binder");

        bind(log).to(Logger.class).named(PLUGIN_LOGGER_NAME);
        bind(motd).to(String.class).named(MOTD_NAME);
    }
}
