package by.ttre16.briana.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation may be used on a field or parameter as a qualifier for
 * custom {@link java.util.Properties} bean.
 *
 * @author Ilia Tretiak
 * @author Juergen Hoeller
 * @version 1.0
 * @see Qualifier
 */

@Qualifier
@Target({ElementType.FIELD, ElementType.METHOD,
        ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProfileProperties { }
