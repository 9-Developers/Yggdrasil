package tech.ninecraft.data.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SQLiteAdapterTest {
    private static final String DATABASE = "test.db";

    private SQLiteAdapter sqliteAdapter;

    @BeforeEach
    void setup() {
        sqliteAdapter = new SQLiteAdapter(DATABASE, LoggerFactory.getLogger(SQLiteAdapterTest.class));
    }

    @Test
    void GIVEN_database_exists_WHEN_connect_THEN_connects() {
        // Then
        assertDoesNotThrow(() -> sqliteAdapter.connect(), "Should not throw exception on connect");
    }
}
