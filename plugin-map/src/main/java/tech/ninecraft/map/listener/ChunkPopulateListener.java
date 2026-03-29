package tech.ninecraft.map.listener;

import tech.ninecraft.map.model.Location2;
import tech.ninecraft.map.model.TaskBlock;
import tech.ninecraft.map.model.TaskChunk;
import tech.ninecraft.map.task.UpdateMapTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.jspecify.annotations.NonNull;

/**
 * Listen for chunk population events.
 *
 * @since 1.0.0
 */
public class ChunkPopulateListener implements Listener {
    /** Map of world name to an update task. */
    @NonNull
    private final Map<String, UpdateMapTask> updateMapTaskMap;

    /**
     * Constructor.
     *
     * @param updateMapTaskMap Map of world name to an update task.
     */
    public ChunkPopulateListener(@NonNull final Map<String, UpdateMapTask> updateMapTaskMap) {
        this.updateMapTaskMap = updateMapTaskMap;
    }

    /**
     * Handle {@link ChunkPopulateEvent}s.
     *
     * @param event Event to handle.
     */
    @EventHandler
    public void onChunkPopulate(@NonNull final ChunkPopulateEvent event) {
        final Chunk chunk = event.getChunk();
        final List<TaskBlock> taskBlocks = new ArrayList<>(256);

        for (int z = 0; z < 16; ++z) {
            for (int x = 0; x < 16; ++x) {
                final Block chunkBlock = chunk.getBlock(x, 0, z);
                final Block surface = event.getWorld().getHighestBlockAt(chunkBlock.getLocation());

                taskBlocks.add(new TaskBlock(surface.getX(), surface.getY(), surface.getZ(), surface.getType()));
            }
        }

        updateMapTaskMap.get(event.getWorld().getName())
                .addChunk(new TaskChunk(new Location2(chunk.getX(), chunk.getZ()), taskBlocks));
    }
}
