package tech.ninecraft.map.controller;

import tech.ninecraft.map.model.Message;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;

import static tech.ninecraft.map.controller.MapBinder.MOTD_NAME;
import static tech.ninecraft.map.controller.MapBinder.PLUGIN_LOGGER_NAME;

/**
 * REST controller.
 *
 * @since 1.0.0
 */
@Path("/map")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class MapController {
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
    @Inject
    public MapController(
        @NonNull @Named(PLUGIN_LOGGER_NAME) final Logger log,
        @NonNull @Named(MOTD_NAME) final String motd) {
        this.log = log;
        this.motd = motd;
    }

    /**
     * Hello world endpoint.
     *
     * @return Hello world!
     */
    @GET
    @NonNull
    @Path("/hello")
    public Message hello() {
        return new Message("Hello world!");
    }

    /**
     * Server name endpoint.
     *
     * @return Server name.
     */
    @GET
    @NonNull
    @Path("/motd")
    public Message motd() {
        return new Message(motd);
    }
}
