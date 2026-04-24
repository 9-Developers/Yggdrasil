package tech.ninecraft.map;

import tech.ninecraft.map.api.WebMap;
import tech.ninecraft.map.controller.MapBinder;
import tech.ninecraft.map.controller.MapController;
import tech.ninecraft.map.listener.ChunkPopulateListener;
import tech.ninecraft.map.model.WorldMap;
import tech.ninecraft.map.task.ReadRegionFileTask;
import tech.ninecraft.map.task.UpdateMapTask;
import tech.ninecraft.server.api.WebServer;
import tech.ninecraft.server.api.model.Resource;
import tech.ninecraft.server.api.model.Status;
import tech.ninecraft.server.api.model.StatusCode;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;

/**
 * 9craft Map plugin.
 *
 * @since 1.0.0
 */
public class MapPlugin extends JavaPlugin implements WebMap, Resource {
    /** Number of chunks per region file. */
    private static final int CHUNKS_PER_REGION = 32 * 32;

    /** Plugin status error messages. */
    @NonNull
    private final List<String> errorMessages;
    /** Plugin status information messages. */
    @NonNull
    private final List<String> infoMessages;
    /** Plugin logger. */
    private final Logger log;
    /** Map of world name to an update task. */
    private final Map<String, UpdateMapTask> updateMapTaskMap;
    /** Map of a world name to a world map. */
    private final Map<String, WorldMap> worldMaps;

    /** Plugin status message. */
    @NonNull
    private String message;
    /** Plugin status code. */
    @NonNull
    private StatusCode statusCode;

    /**
     * Constructor.
     */
    public MapPlugin() {
        errorMessages = new ArrayList<>();
        infoMessages = new ArrayList<>();
        log = getSLF4JLogger();
        message = "Initialized";
        statusCode = StatusCode.OK;
        updateMapTaskMap = HashMap.newHashMap(getServer().getWorlds().size());
        worldMaps = HashMap.newHashMap(getServer().getWorlds().size());
    }

    @Override
    public void onDisable() {
        log.trace("Disabling plugin");

        for (final UpdateMapTask updateMapTask : updateMapTaskMap.values()) {
            updateMapTask.stop();
        }

        statusCode = StatusCode.OK;
        message = "Stopped";
    }

    @Override
    public void onEnable() {
        log.trace("Enabling plugin");

        getServer().getPluginManager().registerEvents(new ChunkPopulateListener(updateMapTaskMap), this);

        for (final World world : getServer().getWorlds()) {
            final File[] regionFiles = new File(world.getWorldFolder(), "region").listFiles();

            if (regionFiles == null) {
                continue;
            }

            final WorldMap worldMap = new WorldMap(HashMap.newHashMap(regionFiles.length * CHUNKS_PER_REGION));
            final UpdateMapTask updateMapTask = new UpdateMapTask(log, worldMap);

            updateMapTaskMap.put(world.getName(), updateMapTask);
            worldMaps.put(world.getName(), worldMap);
            getServer().getScheduler().runTaskAsynchronously(this, updateMapTask);

            final File regionFile = new File(new File(world.getWorldFolder(), "region"), "r.0.0.mca");
            getServer().getScheduler().runTaskAsynchronously(this, new ReadRegionFileTask(regionFile, log));

            // TODO: Batch this
            // for (final File regionFile : regionFiles) {
            //     getServer().getScheduler().runTaskAsynchronously(this, new ReadRegionFileTask(regionFile, logger));
            // }
        }
    }

    @Override
    public void onLoad() {
        log.trace("Loading plugin");

        final HttpHandler httpHandler = new CLStaticHttpHandler(getClass().getClassLoader(), "/site/");
        final WebServer webServer = (WebServer) getServer().getPluginManager().getPlugin("9craft-Web-Server");

        if (webServer == null) {
            throw new IllegalStateException("9craft-Web-Server plugin not found");
        }

        webServer.registerBinder(this, new MapBinder(log, message));
        webServer.registerController(this, MapController.class);
        webServer.registerResource(this, "9craft Map", this);
        webServer.registerHttpHandler(this, httpHandler, "/map");
    }

    @Override
    @NonNull
    public Status status() {
        log.trace("Getting status");

        return new Status(
            statusCode,
            message,
            Collections.unmodifiableList(errorMessages),
            Collections.emptyList(),
            Collections.unmodifiableList(infoMessages));
    }

    /* **************************************** Private utility methods ***************************************** */
}
