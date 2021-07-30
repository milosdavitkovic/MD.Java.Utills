package milos.davitkovic.utills.annotations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Facade annotation
 * has runtime visibility, and we can apply it to types (classes).
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface Facade {
    @AliasFor(
            annotation = Component.class
    )
    String value() default "";
}
