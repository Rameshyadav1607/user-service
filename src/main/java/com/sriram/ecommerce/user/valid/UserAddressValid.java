package com.sriram.ecommerce.user.valid;


import com.sriram.ecommerce.user.validator.UserAddressValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = {UserAddressValidator.class})
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserAddressValid {
    String message() default "{com.sairam.ecommerce-microservices.user.UserAddress.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
