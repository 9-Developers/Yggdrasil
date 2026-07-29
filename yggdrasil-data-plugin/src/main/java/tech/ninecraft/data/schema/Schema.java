package tech.ninecraft.data.schema;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.jspecify.annotations.NonNull;

/**
 * Schema.
 *
 * @since 1.0.0
 */
public class Schema {
    /** Schema generator for default (English) locale. */
    private static final SchemaGenerator DEFAULT_SCHEMA_GENERATOR = new SchemaGenerator();

    /** Schema map of Entity classes to {@link Table}s. */
    private final Map<@NonNull Class<?>, @NonNull Table> schema;

    /**
     * Constructor.
     */
    public Schema() {
        schema = new HashMap<>();
    }

    /**
     * Get table for entity class.
     *
     * @param entityClass Entity class to get table schema for.
     * @return Table for entity class.
     */
    public @NonNull Table getTable(@NonNull final Class<?> entityClass) {
        return getTable(entityClass, DEFAULT_SCHEMA_GENERATOR);
    }

    /**
     * Get table for entity class and locale.
     *
     * @param entityClass Entity class to get table schema for.
     * @param locale      Locale to use to generate the schema.
     * @return Table for entity class.
     */
    public @NonNull Table getTable(@NonNull final Class<?> entityClass, @NonNull final Locale locale) {
        return getTable(entityClass, new SchemaGenerator(locale));
    }

    /* **************************************** Private utility methods ***************************************** */

    /**
     * Get table for entity class.
     *
     * @param entityClass     Entity class to get the table schema for.
     * @param schemaGenerator Schema generator to use if the schema isn't already generated.
     * @return Table for entity class.
     */
    private @NonNull Table getTable(
        @NonNull final Class<?> entityClass,
        @NonNull final SchemaGenerator schemaGenerator) {
        final Table cached = schema.get(entityClass);

        if (cached != null) {
            return cached;
        }

        final Table table = schemaGenerator.getTable(entityClass);

        schema.put(entityClass, table);

        return table;
    }
}
