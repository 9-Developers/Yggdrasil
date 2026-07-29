package tech.ninecraft.data.api;

/**
 * Collating function for binary comparisons.
 *
 * @see <a href="https://www.sqlite.org/datatype3.html#collation">SQLite3 - Collating Sequences</a>
 * @since 1.0.0
 */
public enum CollateFunction {
    /** Compares string data using memcmp(), regardless of text encoding. */
    BINARY,
    /** Similar to binary, except that it uses sqlite3_strnicmp() for the comparison. */
    NO_CASE,
    /** The same as binary, except that trailing space characters are ignored. */
    RTRIM,
}
