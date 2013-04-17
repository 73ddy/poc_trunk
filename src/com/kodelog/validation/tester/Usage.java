package com.kodelog.validation.tester;

import java.util.Date;

import com.kodelog.validation.utility.ValidationHelper;

/**
 * How to use. Instructions.
 * 
 * @author 73ddy
 */
public class Usage {

	private static final String stringParam = "param1";
	private static final Date dateParam = new Date();

	private static final String stringValue = "param1";
	private static final Date dateValue = new Date();

	public static void main(String[] args) {
		// Single validations, when not expecting a response
		ValidationHelper.validateValue(stringParam).against(stringValue).usingValidator(ValidationHelper.isEqual());

		// Single validations, when expecting a response - getValidationResult(Validator)
		boolean returnValue = ValidationHelper.validateValue(stringParam).against(stringValue)
				.getValidationResult(ValidationHelper.isEqual());
		// The above returns true

		/*
		 * Mutliple validations withing a compound validator (not null + equal)
		 */
		ValidationHelper.validateValue(stringParam).against(stringValue)
				.usingValidator(ValidationHelper.isNotNullAndEqual());
		
		// Mutliple validations in the order of validator occurrence
		ValidationHelper.validateValue(stringParam).against(stringValue)
				.usingValidator(ValidationHelper.isNotNull())
				.and(ValidationHelper.isEqual());
		// OR
		ValidationHelper.validateValue(stringParam).against(stringValue)
		.usingValidators(ValidationHelper.isNotNull(), ValidationHelper.isEqual());
		
		
		// Some innovative validators like 'greater than'. See implementaion of {@link GreaterThanValidator}
		// The following will result in an IllegalStateException to be thrown since dateParam is not greater than dateValue
		ValidationHelper.validateValue(dateParam).against(dateValue)
				.usingValidator(ValidationHelper.isGreaterThan());
	}
}
