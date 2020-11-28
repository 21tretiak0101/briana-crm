package by.ttre16.briana.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * This annotation may be used on a field or parameter as a qualifier for
 * custom {@link java.util.Properties} bean.
 *
 * @author Ilia Tretiak
 * @version 1.0
 * @see Qualifier
 */

@Qualifier
@Target({FIELD, METHOD, PARAMETER, TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProfileProperties { }
