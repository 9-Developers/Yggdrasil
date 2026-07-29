package tech.ninecraft.economy.api;

import java.util.UUID;

import org.jspecify.annotations.NonNull;

/**
 * A currency store that belongs to an entity and stores a value directly.
 *
 * <p>
 * Some examples of currency store values include:
 * </p>
 *
 * <ul>
 *     <li>Total balance (no differentiation between "on-hand" and bank account)</li>
 *     <li>"On-hand" currency (not in a bank)</li>
 *     <li>Bank account balance</li>
 *     <li>An investment account or something if you're crazy</li>
 * </ul>
 *
 * @since 1.0.0
 */
public final class CurrencyStoreValue extends CurrencyStore {
    /** Currency value in the store. */
    @NonNull
    private final CurrencyValue value;

    /**
     * Constructor.
     *
     * @param name  Store name.
     * @param owner Owning entity's UUID.
     * @param value Initial value in the store.
     */
    CurrencyStoreValue(@NonNull final String name, @NonNull final UUID owner, @NonNull final CurrencyValue value) {
        super(name, owner);

        this.value = value;
    }

    @Override
    public @NonNull CurrencyValue getValue() {
        return value;
    }
}
