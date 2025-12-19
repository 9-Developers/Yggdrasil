/**
 * 9craft Web Server plugin.
 */
module tech.ninedev.server {
    requires com.fasterxml.jackson.databind;
    requires jakarta.ws.rs;
    requires java.logging;
    requires org.bukkit;
    requires org.glassfish.grizzly.http.server;
    requires org.glassfish.jersey.container.grizzly2.http;
    requires org.glassfish.jersey.core.common;
    requires org.glassfish.jersey.core.server;
    requires org.glassfish.jersey.media.json.jackson;
    requires org.jspecify;
    requires org.slf4j;
    requires tech.ninedev.server.api;
    requires jakarta.validation;
    requires jakarta.inject;
    requires org.glassfish.jersey.inject.hk2;
    requires org.glassfish.hk2.api;

    exports tech.ninedev.server;
}
