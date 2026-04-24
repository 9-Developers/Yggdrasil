package tech.ninecraft.data.adapter;

/**
 * Database adapter interface.
 *
 * @since 1.0.0
 */
public sealed interface DatabaseAdapter permits SQLiteAdapter {
    /**
     * Connect to database.
     */
    void connect();
}
