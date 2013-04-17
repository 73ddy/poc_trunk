package com.kodelog.validation.complexvalidators;

import com.kodelog.validation.utility.ValidationHelper;
import com.kodelog.validation.validators.Validator;

/*-
 * Example of a complex validator, which is a combination of 2 validators.
 * 1. NotNullGeneralValidation
 * 2. EqualsGeneralValidator
 * 
 * @author 73ddy
 */
public class NotNullEqualValidator<K, V, R> implements Validator<K, K, Boolean> {

	public Boolean validate(final K paramValue, final K value) throws IllegalArgumentException, IllegalStateException {
		return ValidationHelper.validateValue(paramValue).against(value)
				.usingValidator(ValidationHelper.isNotNull())
				.and(ValidationHelper.isEqual(), true);
	}
}
