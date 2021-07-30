package milos.davitkovic.utills.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IntegrationTest {
    boolean standalone() default false;
    Class replaces() default IntegrationTest.class;
    String excludedAppserver() default "";
}
