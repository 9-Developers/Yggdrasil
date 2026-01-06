/**
 * 9craft Web Server API.
 */
module tech.ninecraft.server.api {
    requires jakarta.validation;
    requires jakarta.ws.rs;
    requires java.logging;
    requires org.bukkit;
    requires org.glassfish.grizzly.http.server;
    requires org.glassfish.jersey.inject.hk2;
    requires org.jspecify;
    requires org.slf4j;

    exports tech.ninecraft.server.api;
}
