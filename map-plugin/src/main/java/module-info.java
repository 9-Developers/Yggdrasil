/**
 * 9craft Map plugin.
 */
module tech.ninecraft.map {
    requires jakarta.inject;
    requires jakarta.ws.rs;
    requires org.bukkit;
    requires org.glassfish.jersey.core.common;
    requires org.glassfish.jersey.inject.hk2;
    requires org.jspecify;
    requires org.slf4j;
    requires org.glassfish.grizzly.http.server;
    requires tech.ninecraft.map.api;
    requires tech.ninecraft.server.api;
}
