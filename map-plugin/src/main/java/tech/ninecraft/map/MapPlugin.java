package tech.ninecraft.map;

import tech.ninecraft.map.data.WorldMap;
import tech.ninecraft.map.listener.ChunkPopulateListener;
import tech.ninecraft.map.task.ReadRegionFileTask;
import tech.ninecraft.map.task.UpdateMapTask;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;

/**
 * 9craft Map plugin.
 *
 * @since 1.0.0
 */
public class MapPlugin extends JavaPlugin {
    /** Number of chunks per region file. */
    private static final int CHUNKS_PER_REGION = 32 * 32;

    /** Plugin logger. */
    private final Logger logger;
    /** Map of world name to an update task. */
    private final Map<String, UpdateMapTask> updateMapTaskMap;
    /** Map of a world name to a world map. */
    private final Map<String, WorldMap> worldMaps;

    /**
     * Constructor.
     */
    public MapPlugin() {
        logger = getSLF4JLogger();
        updateMapTaskMap = HashMap.newHashMap(getServer().getWorlds().size());
        worldMaps = HashMap.newHashMap(getServer().getWorlds().size());
    }

    @Override
    public void onDisable() {
        for (final UpdateMapTask updateMapTask : updateMapTaskMap.values()) {
            updateMapTask.stop();
        }

        logger.info("9craft Map disabled");
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ChunkPopulateListener(updateMapTaskMap), this);

        for (final World world : getServer().getWorlds()) {
            final File[] regionFiles = new File(world.getWorldFolder(), "region").listFiles();

            if (regionFiles == null) {
                continue;
            }

            final WorldMap worldMap = new WorldMap(HashMap.newHashMap(regionFiles.length * CHUNKS_PER_REGION));
            final UpdateMapTask updateMapTask = new UpdateMapTask(logger, worldMap);

            updateMapTaskMap.put(world.getName(), updateMapTask);
            worldMaps.put(world.getName(), worldMap);
            getServer().getScheduler().runTaskAsynchronously(this, updateMapTask);

            final File regionFile = new File(new File(world.getWorldFolder(), "region"), "r.0.0.mca");
            getServer().getScheduler().runTaskAsynchronously(this, new ReadRegionFileTask(regionFile, logger));

            // TODO: Batch this
            // for (final File regionFile : regionFiles) {
            //     getServer().getScheduler().runTaskAsynchronously(this, new ReadRegionFileTask(regionFile, logger));
            // }
        }

        logger.info("9craft Map enabled");
    }

    /* **************************************** Private utility methods ***************************************** */
}
