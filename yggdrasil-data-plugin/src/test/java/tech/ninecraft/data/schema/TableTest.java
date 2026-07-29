package tech.ninecraft.data.schema;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TableTest {
    private static final Table ARTISTS = new Table("artists")
        .column("id", "INTEGER", Column.Builder::primary)
        .column("name", "NVARCHAR(70)", Column.Builder::notNull);
    private static final Table ALBUMS = new Table("albums")
        .column("id", "INTEGER", Column.Builder::primary)
        .column("name", "NVARCHAR(160)", Column.Builder::notNull)
        .column("artist_id", "INTEGER", builder -> builder.reference(ARTISTS.getColumn("id")));

    @Test
    void GIVEN_artists_table_WHEN_createSQLite_THEN_returns_statement() {
        // Given
        final String expected = """
            CREATE TABLE IF NOT EXISTS artists (
                id INTEGER,
                name NVARCHAR(70) NOT NULL,
                PRIMARY KEY (id)
            );""";

        // When
        final String actual = ARTISTS.createSQLite();

        // Then
        assertEquals(expected, actual, "SQLite create statement should equal expected");
    }

    @Test
    void GIVEN_albums_table_WHEN_createSQLite_THEN_returns_statement() {
        // Given
        final String expected = """
            CREATE TABLE IF NOT EXISTS albums (
                id INTEGER,
                name NVARCHAR(160) NOT NULL,
                artist_id INTEGER,
                PRIMARY KEY (id),
                FOREIGN KEY (artist_id) REFERENCES artists(id)
            );""";

        // When
        final String actual = ALBUMS.createSQLite();

        // Then
        assertEquals(expected, actual, "SQLite create statement should equal expected");
    }
}
