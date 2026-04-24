package tech.ninecraft.data.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Table column definition.
 *
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
    /**
     * @return Collating function clause for string comparisons.
     */
    CollateFunction collate() default CollateFunction.BINARY;

    /**
     * @return Default value.
     */
    String defaultValue() default "";

    /**
     * @return Generated always as clause.
     */
    String generatedAlwaysAs() default "";

    /**
     * @return Table name case.
     */
    Case nameCase() default Case.SNAKE_CASE;

    /**
     * @return Not null constraint.
     */
    boolean notNull() default false;

    /**
     * @return Unique constraint.
     */
    boolean unique() default false;

    /**
     * @return Column name.
     */
    String value() default "";
}
