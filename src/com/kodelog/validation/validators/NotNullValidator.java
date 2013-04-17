package com.kodelog.validation.validators;

/**
 * Equals Validator.
 * 
 * @param <K>
 *            Type of parameter to be validated.
 * @param <V>
 *            Type of value to be validated against.
 * @param <R>
 *            Type of response object.
 *            
 * @author 73ddy
 */
public class NotNullValidator<K, V, B> implements Validator<K, V, Boolean> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.kodelog.validation.validators.Validator#validate(java.lang.Object,
	 * java.lang.Object)
	 */
	public Boolean validate(final K paramValue, final V value) {
		if (paramValue == null) {
			throw new IllegalStateException("Null value found when expecting not null.");
		}
		return true;
	}
}
