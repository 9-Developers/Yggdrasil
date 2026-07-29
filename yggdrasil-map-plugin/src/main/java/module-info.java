/**
 * Yggdrasil Map plugin.
 */
module tech.ninecraft.map {
    requires static com.google.common;
    requires static org.jetbrains.annotations;

    requires jakarta.inject;
    requires jakarta.ws.rs;
    requires jdk.incubator.vector;
    requires org.bukkit;
    requires org.glassfish.jersey.core.common;
    requires org.glassfish.jersey.inject.hk2;
    requires org.jspecify;
    requires org.slf4j;
    requires org.glassfish.grizzly.http.server;
    requires tech.ninecraft.map.api;
    requires tech.ninecraft.server.api;
}
