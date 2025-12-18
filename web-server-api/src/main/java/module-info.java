/**
 * 9craft Web Server API.
 */
module tech.ninedev.server.api {
    requires jakarta.ws.rs;
    requires java.logging;
    requires org.glassfish.grizzly.http.server;
    requires org.jspecify;

    exports tech.ninedev.server.api;
}
