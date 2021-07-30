package milos.davitkovic.utills.annotations;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Interface annotation
 * has runtime visibility, and we can apply it to types (classes).
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Indexed
public @interface Interface {
}
