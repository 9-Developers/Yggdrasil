package tech.ninecraft.economy.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.NonNull;

/**
 * A container (list) of currency stores that represents the total balance of all the stores it contains.
 *
 * <p>
 *     A container can contain other containers or values.
 * </p>
 *
 * @since 1.0.0
 */
public final class CurrencyStoreContainer extends CurrencyStore {
    /** List of stores contained in this container. */
    @NonNull
    private final List<CurrencyStore> stores;

    /**
     * Constructor.
     *
     * @param name  Store name.
     * @param owner Owning entity's UUID.
     */
    public CurrencyStoreContainer(@NonNull final String name, @NonNull final UUID owner) {
        super(name, owner);

        this.stores = new ArrayList<>();
    }

    @Override
    public @NonNull CurrencyValue getValue() {
        return new CurrencyValue(null, 0);
    }
}
