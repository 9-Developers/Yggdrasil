package tech.ninecraft.data.adapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;

/**
 * SQLite adapter.
 *
 * @since 1.0.0
 */
public final class SQLiteAdapter implements AutoCloseable, DatabaseAdapter {
    /** Database name (IE. server.db). */
    private final String database;
    /** Plugin logger. */
    private final Logger log;

    /** Database connection. */
    private Connection connection;

    /**
     * Open a database connection.
     *
     * @param database Database name (IE. server.db).
     * @param log      Plugin logger.
     */
    public SQLiteAdapter(@NonNull final String database, @NonNull final Logger log) {
        this.database = database;
        this.log = log;
    }

    @Override
    public void close() {
        log.trace("Closing SQLite connection");

        try {
            connection.close();
        } catch (final SQLException e) {
            log.warn("Failed to close SQLite connection", e);
        }
    }

    /**
     * Connect to database.
     */
    @Override
    public void connect() {
        if (connection != null) {
            close();
        }

        log.trace("Connecting to SQLite database");

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + database);
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
