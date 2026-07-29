package tech.ninecraft.economy.api;

import java.util.UUID;

import org.jspecify.annotations.NonNull;

/**
 * Currency store.
 *
 * <p>
 * A currency store could belong to a single entity (player, mob, etc.), a group (permission group, Towny town, etc.),
 * or anything else you generate a UUID for.
 * </p>
 *
 * @since 1.0.0
 */
public abstract sealed class CurrencyStore permits CurrencyStoreContainer, CurrencyStoreValue {
    /** Store name. */
    @NonNull
    private final String name;
    /** Owning entity's UUID. */
    @NonNull
    private final UUID owner;

    /**
     * Constructor.
     *
     * @param name Store name.
     * @param owner Owning entity's UUID.
     */
    CurrencyStore(@NonNull final String name, @NonNull final UUID owner) {
        this.name = name;
        this.owner = owner;
    }

    /**
     * Get total value in the store.
     *
     * @return Total value in the store.
     */
    public abstract @NonNull CurrencyValue getValue();
}
