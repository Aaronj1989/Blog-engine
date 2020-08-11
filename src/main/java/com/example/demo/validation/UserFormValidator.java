package com.example.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

import com.example.demo.models.UserForm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFormValidator implements ConstraintValidator<PasswordConfirmation, UserForm> {

	private String field;
	private String fieldMatch;

	public void initialize(PasswordConfirmation constraint) {
		this.field = constraint.field();
		this.fieldMatch = constraint.fieldMatch();

	}

	@Override
	public boolean isValid(UserForm userForm, ConstraintValidatorContext context) {

		Object fieldValue = new BeanWrapperImpl(userForm).getPropertyValue(field);

		Object fieldMatchValue = new BeanWrapperImpl(userForm).getPropertyValue(fieldMatch);
		boolean isValid = fieldValue == null && fieldMatchValue == null
				|| fieldValue != null && fieldValue.equals(fieldMatchValue);
		if (!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
					.addPropertyNode("repeatPassword").addConstraintViolation();
		}

		return isValid;
	}
}
