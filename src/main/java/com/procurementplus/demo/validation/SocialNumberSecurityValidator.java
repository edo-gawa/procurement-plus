package com.procurementplus.demo.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.procurementplus.demo.validation.impl.SocialNumberSecurityValidatorImpl;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = SocialNumberSecurityValidatorImpl.class)
public @interface SocialNumberSecurityValidator {
	// error message
		public String message() default "Invalid Number";

		// represents group of constraints
		public Class<?>[] groups() default {};

		// represents additional information about annotation
		public Class<? extends Payload>[] payload() default {};
}
