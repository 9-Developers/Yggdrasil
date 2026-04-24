package tech.ninecraft.economy.api;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Currency definition.
 *
 * <p>
 * The precision is the number of fractional digits supported by the currency. A currency with no fractional
 * precision uses whole numbers only and the precision returned by this method would be {@code 0}. US dollars have
 * precision of {@code 2} because cents are hundredths of a dollar (2 decimal places).
 * </p>
 *
 * @param namePlural The plural form of the currency name.
 * @param nameSingular The singular form of the currency name.
 * @param precision The precision (number of supported decimal places) of the currency.
 * @param prefix (Optional) Currency prefix (i.e., "$").
 * @param separator Decimal separator character (i.e., "," or ".").
 * @param suffix (Optional) Currency suffix (i.e., "USD").
 * @since 1.0.0
 */
public record Currency(
    @NonNull String namePlural,
    @NonNull String nameSingular,
    int precision,
    @Nullable String prefix,
    char separator,
    @Nullable String suffix) {
}
