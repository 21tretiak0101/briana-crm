package by.ttre16.briana.annotation;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Annotation that is used to resolve {@link Authentication#getPrincipal()}
 * to a method argument.
 *
 * @author Ilia Tretiak
 * @version 1.0
 * @see AuthenticationPrincipal
 */

@Target({PARAMETER, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@AuthenticationPrincipal
public @interface Authenticated { }
