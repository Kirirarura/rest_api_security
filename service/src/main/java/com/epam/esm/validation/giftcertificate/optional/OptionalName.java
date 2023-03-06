package com.epam.esm.validation.giftcertificate.optional;


import org.hibernate.validator.constraints.Length;

import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

@Length(min = 4,max = 80)
@Target({ElementType.TYPE_USE, METHOD, FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptionalName {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
