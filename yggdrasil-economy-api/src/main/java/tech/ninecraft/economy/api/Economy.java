package tech.ninecraft.economy.api;

import java.util.Optional;
import java.util.UUID;

import org.jspecify.annotations.NonNull;

/**
 * Economy API.
 *
 * @since 1.0.0
 */
public interface Economy {
    /**
     * Take currency from a single entity.
     *
     * <p>
     * Calls {@link Economy#transfer(UUID, CurrencyValue)} after verifying that {@code amount} is positive.
     * </p>
     *
     * @param to   Entity to deposit to.
     * @param amount Amount to transfer.
     * @return {@code true} if the transfer was successful, otherwise {@code false}.
     * @see Economy#transfer(UUID, CurrencyValue)
     */
    boolean deposit(@NonNull UUID to, @NonNull CurrencyValue amount);

    /**
     * Get an entity's total currency.
     *
     * @param entity Entity.
     * @return Player's total currency.
     */
    @NonNull CurrencyValue getBalance(@NonNull UUID entity);

    /**
     * Get the maximum amount of currency an entity is allowed to have.
     *
     * @param entity The entity whose limits to check.
     * @return The maximum amount of currency an entity is allowed to have, or {@link Optional#empty()} if there is no
     *     limit.
     */
    @NonNull Optional<CurrencyValue> getMaximumAllowedCurrency(@NonNull UUID entity);

    /**
     * Get the minimum amount of currency an entity is allowed to have.
     *
     * @param entity The entity whose limits to check.
     * @return The minimum amount of currency an entity is allowed to have, or {@link Optional#empty()} if there is no
     *     limit.
     */
    @NonNull Optional<CurrencyValue> getMinimumAllowedCurrency(@NonNull UUID entity);

    /**
     * Give (or take) currency from a single entity.
     *
     * <p>
     * Passing a positive value to {@code amount} will give the entity money, while passing a negative value will take
     * money.
     * </p>
     *
     * @param to     Entity to transfer currency to (destination).
     * @param amount Amount to transfer.
     * @return {@code true} if the transfer was successful, otherwise {@code false}.
     */
    boolean transfer(@NonNull UUID to, @NonNull CurrencyValue amount);

    /**
     * Transfer currency from one entity to another.
     *
     * @param from   Entity to transfer currency from (source).
     * @param to     Entity to transfer currency to (destination).
     * @param amount Amount to transfer.
     * @return {@code true} if the transfer was successful, otherwise {@code false}.
     */
    boolean transfer(@NonNull UUID from, @NonNull UUID to, @NonNull CurrencyValue amount);

    /**
     * Take currency from a single entity.
     *
     * <p>
     * Calls {@link Economy#transfer(UUID, CurrencyValue)} after verifying that {@code amount} is positive then making
     * it negative.
     * </p>
     *
     * @param from   Entity to withdraw from.
     * @param amount Amount to transfer.
     * @return {@code true} if the transfer was successful, otherwise {@code false}.
     * @see Economy#transfer(UUID, CurrencyValue)
     */
    boolean withdraw(@NonNull UUID from, @NonNull CurrencyValue amount);
}
