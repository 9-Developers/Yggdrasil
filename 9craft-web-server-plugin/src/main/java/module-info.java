/**
 * 9craft Web Server plugin.
 */
module tech.ninecraft.server {
    requires com.fasterxml.jackson.databind;
    requires jakarta.inject;
    requires jakarta.ws.rs;
    requires jakarta.validation;
    requires org.bukkit;
    requires org.glassfish.grizzly.http.server;
    requires org.glassfish.hk2.api;
    requires org.glassfish.jersey.container.grizzly2.http;
    requires org.glassfish.jersey.core.common;
    requires org.glassfish.jersey.core.server;
    requires org.glassfish.jersey.inject.hk2;
    requires org.glassfish.jersey.media.json.jackson;
    requires org.jspecify;
    requires org.slf4j;
    requires tech.ninecraft.data.api;
    requires tech.ninecraft.server.api;
}
