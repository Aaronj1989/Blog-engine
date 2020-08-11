package com.example.demo.validation;


import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = UserFormValidator.class )
@Retention(RUNTIME)
@Target({TYPE, METHOD })
public @interface PasswordConfirmation {

	String message() default "Passwords don't match";
	Class<?> [] groups()  default {};
	Class<? extends Payload>[] payload() default{};
	
	String field();
	String fieldMatch();
	

}
