package com.procurementplus.demo.validation.impl;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.procurementplus.demo.validation.UserNameValidator;

public class UserNameValidatorImpl implements ConstraintValidator<UserNameValidator, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub

		if(Optional.ofNullable(value).isPresent() && value.matches("^[a-zA-Z0-9]{2,50}$")) {
			return true;
		}
		return false;
	}



}
