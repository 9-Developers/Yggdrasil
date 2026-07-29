package tech.ninecraft.data.schema;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Database table definition.
 *
 * @since 1.0.0
 */
public class Table {
    /** Table column definitions. */
    @NonNull
    private final Map<String, Column> columns;
    /** Table name. */
    @NonNull
    private final String name;
    /** Primary key columns. */
    @NonNull
    private final List<Column> primaryKeys;

    /**
     * Constructor.
     *
     * @param name Table name.
     */
    public Table(@NonNull final String name) {
        this.columns = new LinkedHashMap<>();
        this.name = name;
        this.primaryKeys = new ArrayList<>();
    }

    /**
     * Add a column to the table.
     *
     * @param name Column name.
     * @param type Column type.
     * @return {@code this}.
     */
    public Table column(@NonNull final String name, @NonNull final String type) {
        columns.put(name, Column.builder(this.name, name, type).build());

        return this;
    }

    /**
     * Add a column to the table.
     *
     * @param name      Column name.
     * @param type      Column type.
     * @param configure {@link Consumer} lambda which allows configuration of the column.
     * @return {@code this}.
     */
    public Table column(
        @NonNull final String name,
        @NonNull final String type,
        @NonNull final Consumer<Column.Builder> configure) {
        final Column.Builder builder = Column.builder(this.name, name, type);

        configure.accept(builder);
        columns.put(name, builder.build());

        return this;
    }

    /**
     * Get SQL statement to create the table in the SQLite dialect.
     *
     * @return SQL statement to create the table in the SQLite dialect.
     */
    public String createSQLite() {
        final List<String> foreignKeys = new LinkedList<>();
        final List<String> primaryKeys = new LinkedList<>();
        final StringBuilder statementBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
            .append(name)
            .append(" (")
            .append(System.lineSeparator());

        for (final Column column : columns.values()) {
            statementBuilder.append("    ").append(column.toSQLite()).append(System.lineSeparator());

            if (column.isPrimary()) {
                primaryKeys.add(column.getName());
            }

            final Column reference = column.getReference();

            if (reference != null) {
                foreignKeys.add("    FOREIGN KEY (%s) REFERENCES %s(%s)".formatted(
                    column.getName(),
                    reference.getTable(),
                    reference.getName()));
            }
        }

        statementBuilder
            .append("    PRIMARY KEY (")
            .append(String.join(", ", primaryKeys))
            .append(')');

        if (!foreignKeys.isEmpty()) {
            statementBuilder.append(',')
                .append(System.lineSeparator())
                .append(String.join(",%s".formatted(System.lineSeparator()), foreignKeys));
        }

        return statementBuilder
            .append(System.lineSeparator())
            .append(");")
            .toString();
    }

    /**
     * Get column from table.
     *
     * @param name Column name.
     * @return Column from table.
     */
    @Nullable
    public Column getColumn(@NonNull final String name) {
        return columns.get(name);
    }
}
