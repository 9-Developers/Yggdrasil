package tech.ninecraft.data.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Database table definition.
 *
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {
    /**
     * Column name case.
     *
     * @return Column name case.
     */
    Case columnCase() default Case.SNAKE_CASE;

    /**
     * If {@code true} plural table names should be used.
     *
     * @return {@code true} if plural table names should be used.
     */
    boolean isPlural() default true;

    /**
     * Table name case.
     *
     * @return Table name case.
     */
    Case nameCase() default Case.SNAKE_CASE;

    /**
     * SQL schema the table is defined in.
     *
     * @return SQL schema the table is defined in.
     */
    String schema() default "public";

    /**
     * Table name.
     *
     * @return Table name.
     */
    String value() default "";
}
