package tech.ninecraft.data.schema;

import tech.ninecraft.data.api.Case;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atteo.evo.inflector.English;
import org.jspecify.annotations.NonNull;

/**
 * Schema generator.
 *
 * @since 1.0.0
 */
public class SchemaGenerator {
    /** Pattern for splitting camel case words. */
    private static final Pattern WORD_PATTERN = Pattern.compile(
        "(?<=[A-Z])(?=[A-Z][a-z])|(?<=[^A-Z])(?=[A-Z])|(?<=[A-Za-z])(?=[^A-Za-z])");

    /** Locale for name conversion. */
    @NonNull
    private final Locale locale;

    /**
     * Construct a new {@link SchemaGenerator} with English locale.
     */
    public SchemaGenerator() {
        this(Locale.ENGLISH);
    }

    /**
     * Construct a new {@link SchemaGenerator} with the given locale.
     *
     * @param locale Locale for name conversion.
     */
    public SchemaGenerator(@NonNull final Locale locale) {
        this.locale = locale;
    }

    /**
     * Get table schema for a given entity class.
     *
     * @param tableClass Entity class.
     * @param <T>        Entity type.
     * @return Table schema.
     */
    public <T> @NonNull Table getTable(@NonNull final Class<T> tableClass) {
        final boolean isPlural;
        final String name;
        final Case nameCase;

        if (tableClass.isAnnotationPresent(tech.ninecraft.data.api.Table.class)) {
            final tech.ninecraft.data.api.Table annotation = tableClass.getAnnotation(tech.ninecraft.data.api.Table.class);

            if (annotation.value().isEmpty()) {
                name = tableClass.getName();
            } else {
                name = annotation.value();
            }
            isPlural = annotation.isPlural();
            nameCase = annotation.nameCase();
        } else {
            isPlural = true;
            name = tableClass.getName();
            nameCase = Case.SNAKE_CASE;
        }

        final String tableName = getName(name, nameCase, isPlural);
        final Table table = new Table(tableName);

        for (final Field field : tableClass.getDeclaredFields()) {
            table.column(getName(field.getName(), Case.SNAKE_CASE, false), field.getType().getName());
        }

        return table;
    }

    /* **************************************** Private utility methods ***************************************** */

    private @NonNull String getName(@NonNull final String name, @NonNull final Case nameCase, final boolean plural) {
        final String pluralName;

        if (plural) {
            final Matcher matcher = WORD_PATTERN.matcher(name);
            final String finalWord = matcher.group(matcher.groupCount() - 1);
            pluralName = name.replace(finalWord, English.plural(finalWord));
        } else {
            pluralName = name;
        }

        return switch (nameCase) {
            case CAMEL_CASE -> null;
            case KEBAB_CASE -> null;
            case PASCAL_CASE -> null;
            case ROADKILL_SNAKE_CASE -> null;
            case SCREAMING_SNAKE_CASE -> null;
            case SNAKE_CASE -> toSnakeCase(pluralName);
        };
    }

    private String toSnakeCase(final String name) {
        return name.replaceAll("([A-Z])", "_$1").toLowerCase(locale);
    }
}
