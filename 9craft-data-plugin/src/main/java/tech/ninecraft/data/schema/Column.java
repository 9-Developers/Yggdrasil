package tech.ninecraft.data.schema;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Table column definition.
 *
 * @since 1.0.0
 */
public class Column {
    /** Is the column non-null? */
    private final boolean isNotNull;
    /** Is the column (part of) the primary key? */
    private final boolean isPrimary;
    /** Column name. */
    @NonNull
    private final String name;
    /** (Optional) Foreign key constraint. */
    @Nullable
    private final Column reference;
    /** Table name. */
    @NonNull
    private final String table;
    /** Column type. */
    @NonNull
    private final String type;

    /**
     * Constructor.
     *
     * @param isNotNull Is the column non-null?
     * @param isPrimary is the column (part of) the primary key?
     * @param name      Column name.
     * @param reference (Optional) Foreign key constraint.
     * @param table     Table name.
     * @param type      Column type.
     */
    Column(
        final boolean isNotNull,
        final boolean isPrimary,
        @NonNull final String name,
        @Nullable final Column reference,
        @NonNull final String table,
        @NonNull final String type) {
        this.isNotNull = isNotNull;
        this.isPrimary = isPrimary;
        this.name = name;
        this.reference = reference;
        this.table = table;
        this.type = type;
    }

    /**
     * Get a column builder.
     *
     * @param table Table name.
     * @param name  Column name.
     * @param type  Column type.
     * @return New column builder.
     */
    public static Builder builder(@NonNull final String table, @NonNull final String name, @NonNull final String type) {
        return new Builder(table, name, type);
    }

    /**
     * @return Column name.
     */
    @NonNull String getName() {
        return name;
    }

    /**
     * @return Foreign key constraint if one exists, otherwise {@code null}.
     */
    @Nullable Column getReference() {
        return reference;
    }

    /**
     * @return Table name.
     */
    @NonNull String getTable() {
        return table;
    }

    /**
     * @return {@code true} if the column is (part of) the primary key.
     */
    boolean isPrimary() {
        return isPrimary;
    }

    /**
     * Get SQL statement to create the column in the SQLite dialect.
     *
     * @return SQL statement to create the column in the SQLite dialect.
     */
    public String toSQLite() {
        return "%s %s%s,".formatted(name, type, isNotNull ? " NOT NULL" : "");
    }

    /**
     * Column builder.
     *
     * @since 1.0.0
     */
    public static class Builder {
        /** Column name. */
        @NonNull
        private final String name;
        /** Table name. */
        @NonNull
        private final String table;
        /** Column type. */
        @NonNull
        private final String type;

        /** Is the column non-null? */
        private boolean isNotNull;
        /** Is the column (part of) the primary key? */
        private boolean isPrimary;
        /** (Optional) Foreign key constraint. */
        @Nullable
        private Column reference;

        private Builder(@NonNull final String table, @NonNull final String name, @NonNull final String type) {
            this.name = name;
            this.table = table;
            this.type = type;
            this.isNotNull = false;
            this.isPrimary = false;
            this.reference = null;
        }

        Column build() {
            return new Column(isNotNull, isPrimary, name, reference, table, type);
        }

        /**
         * Set the column to be non-null.
         *
         * @return {@code this}.
         */
        public Builder notNull() {
            isNotNull = true;

            return this;
        }

        /**
         * Set the column to be (part of) the primary key.
         *
         * @return {@code this}.
         */
        public Builder primary() {
            isPrimary = true;

            return this;
        }

        /**
         * Add a foreign key constraint to the column.
         *
         * @param reference Column to reference.
         * @return {@code this}.
         */
        public Builder reference(@NonNull final Column reference) {
            this.reference = reference;

            return this;
        }
    }
}
