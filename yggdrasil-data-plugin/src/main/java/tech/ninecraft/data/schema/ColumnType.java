package tech.ninecraft.data.schema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import org.jspecify.annotations.NonNull;

/**
 * Column type.
 *
 * @since 1.0.0
 */
public enum ColumnType {
    /** Binary large object. */
    BLOB("BLOB"),
    /** Boolean. */
    BOOLEAN("INTEGER"),
    /** Date only. */
    DATE("TEXT"),
    /** Date and time. */
    DATE_TIME("TEXT"),
    /** Date and time with time zone. */
    DATE_TIME_WITH_TZ("TEXT"),
    /** Integer. */
    INTEGER("INTEGER"),
    /** Real number. */
    REAL("REAL"),
    /** Text. */
    TEXT("TEXT"),
    /** Time only. */
    TIME("TEXT");

    /** MongoDB type name. */
    @NonNull
    private final String mongo;
    /** MySQL type name. */
    @NonNull
    private final String mysql;
    /** PostgreSQL type name. */
    @NonNull
    private final String postgres;
    /** SQLite type name. */
    @NonNull
    private final String sqlite;

    /**
     * Constructor.
     *
     * @param sqlite SQLite type name.
     */
    ColumnType(@NonNull final String sqlite) {
        this.mongo = sqlite;
        this.mysql = sqlite;
        this.postgres = sqlite;
        this.sqlite = sqlite;
    }

    /**
     * Get column type from a field.
     *
     * @param fieldType Field type.
     * @return Column type.
     */
    public static ColumnType fromField(@NonNull final Class<?> fieldType) {
        if (boolean.class.equals(fieldType) || Boolean.class.equals(fieldType)) {
            return BOOLEAN;
        } else if (byte.class.equals(fieldType) || Byte.class.equals(fieldType)) {
            return INTEGER;
        } else if (char.class.equals(fieldType) || Character.class.equals(fieldType)) {
            return TEXT;
        } else if (double.class.equals(fieldType) || Double.class.equals(fieldType)) {
            return REAL;
        } else if (float.class.equals(fieldType) || Float.class.equals(fieldType)) {
            return REAL;
        } else if (int.class.equals(fieldType) || Integer.class.equals(fieldType)) {
            return INTEGER;
        } else if (long.class.equals(fieldType) || Long.class.equals(fieldType)) {
            return INTEGER;
        } else if (short.class.equals(fieldType) || Short.class.equals(fieldType)) {
            return INTEGER;
        } else if (LocalDate.class.equals(fieldType)) {
            return DATE;
        } else if (LocalDateTime.class.equals(fieldType)) {
            return DATE_TIME;
        } else if (LocalTime.class.equals(fieldType)) {
            return TIME;
        } else if (String.class.equals(fieldType)) {
            return TEXT;
        } else if (ZonedDateTime.class.equals(fieldType)) {
            return DATE_TIME_WITH_TZ;
        }

        return BLOB;
    }

    /**
     * Get MongoDB type name.
     *
     * @return MongoDB type name.
     */
    public @NonNull String mongo() {
        return mongo;
    }

    /**
     * Get MySQL type name.
     *
     * @return MySQL type name.
     */
    public @NonNull String mysql() {
        return mysql;
    }

    /**
     * Get PostgreSQL type name.
     *
     * @return PostgreSQL type name.
     */
    public @NonNull String postgres() {
        return postgres;
    }

    /**
     * Get SQLite type name.
     *
     * @return SQLite type name.
     */
    public @NonNull String sqlite() {
        return sqlite;
    }
}
