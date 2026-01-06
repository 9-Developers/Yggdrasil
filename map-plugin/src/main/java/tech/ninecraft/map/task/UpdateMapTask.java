package tech.ninecraft.map.task;

import tech.ninecraft.map.data.MapBlock;
import tech.ninecraft.map.data.MapChunk;
import tech.ninecraft.map.data.TaskBlock;
import tech.ninecraft.map.data.TaskChunk;
import tech.ninecraft.map.data.WorldMap;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;

/**
 * Asynchronous plugin task to update a map.
 *
 * @since 1.0.0
 */
public class UpdateMapTask implements Runnable {
    /** Width of a chunk in chunks. */
    private static final int CHUNK_WIDTH = 16;

    /** Update queue. */
    private final Deque<TaskChunk> taskQueue = new ArrayDeque<>();

    /** Plugin logger. */
    @NonNull
    private final Logger log;
    /** World map to update. */
    @NonNull
    private final WorldMap worldMap;

    /** If {@code true} don't do any work until the task is resumed. */
    private boolean isPaused = false;
    /** If {@code false} end the task. */
    private boolean isRunning = true;

    /**
     * Constructor.
     *
     * @param log      Plugin logger.
     * @param worldMap World map to update.
     */
    public UpdateMapTask(@NonNull final Logger log, @NonNull final WorldMap worldMap) {
        this.log = log;
        this.worldMap = worldMap;
    }

    /**
     * Add a {@link TaskChunk} to the task queue.
     *
     * @param taskChunk {@link TaskChunk} to add.
     */
    public void addChunk(@NonNull final TaskChunk taskChunk) {
        taskQueue.addLast(taskChunk);
    }

    /**
     * Pause the task.
     */
    public void pause() {
        isPaused = true;
    }

    /**
     * Resume the task.
     */
    public void resume() {
        isPaused = false;
    }

    /**
     * Stop/cancel the task.
     */
    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning) {
            if (isPaused || taskQueue.isEmpty()) {
                try {
                    Thread.sleep(Duration.of(1, ChronoUnit.SECONDS));
                } catch (final InterruptedException e) {
                    log.error("Task interrupted while sleeping", e);
                }

                continue;
            }

            final TaskChunk taskChunk = taskQueue.removeFirst();

            if (worldMap.chunks().containsKey(taskChunk.location())) {
                final MapChunk mapChunk = worldMap.chunks().get(taskChunk.location());

                synchronized (mapChunk) {
                    for (final TaskBlock taskBlock : taskChunk.blocks()) {
                        mapChunk.blocks()
                            .get(taskBlock.z())
                            .set(taskBlock.x(), new MapBlock(taskBlock.y(), taskBlock.material()));
                    }
                }
            } else {
                // Initialize blocks matrix
                final List<List<MapBlock>> blocks = new ArrayList<>(CHUNK_WIDTH);

                for (int index = 0; index < CHUNK_WIDTH; ++index) {
                    blocks.add(new ArrayList<>(CHUNK_WIDTH));
                }

                for (final TaskBlock taskBlock : taskChunk.blocks()) {
                    blocks.get(taskBlock.z()).set(taskBlock.x(), new MapBlock(taskBlock.y(), taskBlock.material()));
                }

                worldMap.chunks().put(taskChunk.location(), new MapChunk(blocks));
            }
        }
    }
}
