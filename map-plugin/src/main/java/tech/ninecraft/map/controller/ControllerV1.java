package tech.ninecraft.map.controller;

import tech.ninecraft.map.data.Message;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jspecify.annotations.NonNull;

/**
 * REST controller.
 *
 * @since 1.0.0
 */
@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
public class ControllerV1 {
    /** Message of the day. */
    @NonNull
    private final String motd;

    /**
     * Constructor.
     *
     * @param motd Message of the day.
     */
    public ControllerV1(@NonNull final String motd) {
        this.motd = motd;
    }

    /**
     * Hello world endpoint.
     *
     * @return Hello world!
     */
    @GET
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
    @Path("/motd")
    public Message motd() {
        return new Message(motd);
    }
}
