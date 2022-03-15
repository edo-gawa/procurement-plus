package com.procurementplus.demo.validation.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.procurementplus.demo.validation.DateOfBirthValidator;

public class DateOfBirthValidatorImpl implements ConstraintValidator<DateOfBirthValidator, String>{
    private String pattern="YYYY-MM-DD";

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(Optional.ofNullable(value).isEmpty()) {
			return false;
		}
		  try {
	            Date date = new SimpleDateFormat(pattern).parse(value);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	}

}
