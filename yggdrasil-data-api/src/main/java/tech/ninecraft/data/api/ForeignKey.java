package tech.ninecraft.data.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Foreign key constraint.
 *
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ForeignKey {
    /**
     * @return Cross-table relationship (i.e. many-to-many, one-to-many, one-to-one).
     */
    Relationship relationship() default Relationship.ONE_TO_MANY;
}
