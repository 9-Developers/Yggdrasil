package tech.ninecraft.core.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.NonNull;

/**
 * A group that entities (can) belong to.
 *
 * <p>
 * A group could be a permission group defined by a permissions plugin, a town or nation defined by a residence plugin,
 * or anything else.
 * </p>
 *
 * @param members Members belonging to the group.
 * @param owner   Owning entity's ID.
 * @param uuid    Unique ID.
 * @since 1.0.0
 */
public record Group(@NonNull List<UUID> members, @NonNull UUID owner, @NonNull UUID uuid) {
    /**
     * Constructor.
     *
     * @param owner Owning entity's ID.
     * @param uuid  Unique ID.
     */
    public Group(@NonNull final UUID owner, @NonNull final UUID uuid) {
        this(new ArrayList<>(), owner, uuid);
    }

    /**
     * Transfer group to a new owner.
     *
     * @param owner New owner's ID.
     * @return A new {@link Group} with the same members and ID but new owner.
     */
    public Group transfer(@NonNull final UUID owner) {
        return new Group(this.members, owner, this.uuid);
    }
}
