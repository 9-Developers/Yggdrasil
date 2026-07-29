package tech.ninecraft.economy.api;

import org.jspecify.annotations.NonNull;

/**
 * Currency value store.
 *
 * @since 1.0.0
 */
// TODO: Fractional precision
public class CurrencyValue {
    /** Currency this is a value of. */
    @NonNull
    private final Currency currency;

    /** Fractional currency value (i.e., $0.99). */
    private int fractionalValue;
    /** Whole-number currency value (i.e., $99). */
    private int wholeValue;

    /**
     * Constructor.
     *
     * @param currency   Currency this is a value of.
     * @param wholeValue Whole-number currency value (i.e., $99).
     */
    public CurrencyValue(@NonNull final Currency currency, final int wholeValue) {
        this(currency, wholeValue, 0);
    }

    /**
     * Constructor.
     *
     * @param currency        Currency this is a value of.
     * @param wholeValue      Whole-number currency value (i.e., $99).
     * @param fractionalValue Fractional currency value (i.e., $0.99).
     */
    public CurrencyValue(@NonNull final Currency currency, final int wholeValue, final int fractionalValue) {
        this.currency = currency;
        this.fractionalValue = fractionalValue;
        this.wholeValue = wholeValue;
    }

    /**
     * Add currency.
     *
     * @param addend Currency to add.
     * @return {@code this}.
     */
    public @NonNull CurrencyValue add(@NonNull final CurrencyValue addend) {
        final int fractionalTotal = fractionalValue + addend.fractionalValue;
        final int carryOver;

        if (fractionalTotal > 100) {
            carryOver = 1;
            fractionalValue = fractionalTotal % 100;
        } else {
            carryOver = 0;
            fractionalValue = fractionalTotal;
        }

        wholeValue += addend.wholeValue + carryOver;

        return this;
    }

    /**
     * Divide currency.
     *
     * @param dividend Currency to divide by.
     * @return {@code this}.
     */
    public @NonNull CurrencyValue divide(@NonNull final CurrencyValue dividend) {
        return this;
    }

    /**
     * @return Fractional (decimal) currency value.
     */
    public int getFractionalValue() {
        return fractionalValue;
    }

    /**
     * @return Whole-number currency value.
     */
    public int getWholeValue() {
        return wholeValue;
    }

    /**
     * Multiply currency.
     *
     * @param multiplicand Currency to multiply by.
     * @return {@code this}.
     */
    public @NonNull CurrencyValue multiply(@NonNull final CurrencyValue multiplicand) {
        return this;
    }

    /**
     * Subtract currency.
     *
     * @param subtrahend Currency to subtract.
     * @return {@code this}.
     */
    public @NonNull CurrencyValue subtract(@NonNull final CurrencyValue subtrahend) {
        return this;
    }
}
