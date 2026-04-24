package tech.ninecraft.chat.api;

import java.util.UUID;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

/**
 * 9craft chat API.
 *
 * @since 1.0.0
 */
public interface Chat {
    /**
     * Get player's chat prefix.
     *
     * @param player Chatting player.
     * @return Player's chat prefix.
     */
    @NonNull String getPrefix(@NonNull Player player);

    /**
     * Get entity's chat prefix.
     *
     * @param uuid Chatting entity's unique ID.
     * @return Entity's chat prefix.
     */
    @NonNull String getPrefix(@NonNull UUID uuid);

    /**
     * Get player's chat prefix for specific world.
     *
     * @param player Chatting player.
     * @param world World player is in.
     * @return Player's chat prefix for specific world.
     */
    @NonNull String getPrefix(@NonNull Player player, @NonNull World world);

    /**
     * Get entity's chat prefix for specific world.
     *
     * @param uuid Chatting entity's unique ID.
     * @param world World entity is chatting in.
     * @return Entity's chat prefix for specific world.
     */
    @NonNull String getPrefix(@NonNull UUID uuid, @NonNull UUID world);

    /**
     * Get player's chat suffix.
     *
     * @param player Chatting player.
     * @return Player's chat suffix.
     */
    @NonNull String getSuffix(@NonNull Player player);

    /**
     * Get entity's chat suffix.
     *
     * @param uuid Chatting entity's unique ID.
     * @return Entity's chat suffix.
     */
    @NonNull String getSuffix(@NonNull UUID uuid);

    /**
     * Get player's chat suffix for specific world.
     *
     * @param player Chatting player.
     * @param world World player is in.
     * @return Player's chat suffix for specific world.
     */
    @NonNull String getSuffix(@NonNull Player player, @NonNull World world);

    /**
     * Get entity's chat suffix for specific world.
     *
     * @param uuid Chatting entity's unique ID.
     * @param world World entity is chatting in.
     * @return Entity's chat suffix for specific world.
     */
    @NonNull String getSuffix(@NonNull UUID uuid, @NonNull UUID world);

    /**
     * Set player's chat prefix.
     *
     * @param player Chatting player.
     * @param prefix Player's chat prefix.
     * @return Player's chat prefix.
     */
    @NonNull String setPrefix(@NonNull Player player, @NonNull String prefix);

    /**
     * Set entity's chat prefix.
     *
     * @param uuid Chatting entity's unique ID.
     * @param prefix Entity's chat prefix.
     * @return Entity's chat prefix.
     */
    @NonNull String setPrefix(@NonNull UUID uuid, @NonNull String prefix);

    /**
     * Set player's chat prefix for specific world.
     *
     * @param player Chatting player.
     * @param world World player is in.
     * @param prefix Player's chat prefix.
     * @return Player's chat prefix for specific world.
     */
    @NonNull String setPrefix(@NonNull Player player, @NonNull World world, @NonNull String prefix);

    /**
     * Set entity's chat prefix for specific world.
     *
     * @param uuid Chatting entity's unique ID.
     * @param world World entity is chatting in.
     * @param prefix Entity's chat prefix.
     * @return Entity's chat prefix for specific world.
     */
    @NonNull String setPrefix(@NonNull UUID uuid, @NonNull UUID world, @NonNull String prefix);

    /**
     * Set player's chat suffix.
     *
     * @param player Chatting player.
     * @param suffix Player's chat suffix.
     * @return Player's chat suffix.
     */
    @NonNull String setSuffix(@NonNull Player player, @NonNull String suffix);

    /**
     * Set entity's chat suffix.
     *
     * @param uuid Chatting entity's unique ID.
     * @param suffix Entity's chat suffix.
     * @return Entity's chat suffix.
     */
    @NonNull String setSuffix(@NonNull UUID uuid, @NonNull String suffix);

    /**
     * Set player's chat suffix for specific world.
     *
     * @param player Chatting player.
     * @param world World player is in.
     * @param suffix Player's chat suffix.
     * @return Player's chat suffix for specific world.
     */
    @NonNull String setSuffix(@NonNull Player player, @NonNull World world, @NonNull String suffix);

    /**
     * Set entity's chat suffix for specific world.
     *
     * @param uuid Chatting entity's unique ID.
     * @param world World entity is chatting in.
     * @param suffix Entity's chat suffix.
     * @return Entity's chat suffix for specific world.
     */
    @NonNull String setSuffix(@NonNull UUID uuid, @NonNull UUID world, @NonNull String suffix);
}
