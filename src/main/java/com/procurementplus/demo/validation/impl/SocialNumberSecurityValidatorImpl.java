package com.procurementplus.demo.validation.impl;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.procurementplus.demo.validation.SocialNumberSecurityValidator;

public class SocialNumberSecurityValidatorImpl implements ConstraintValidator<SocialNumberSecurityValidator, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if( Optional.ofNullable(value).isPresent() && value.matches("^(?!.*(.).*\\1)\\d{4,5}$")) {			
			return true;
		}
		return false;
	}
	
	
	
	

}
