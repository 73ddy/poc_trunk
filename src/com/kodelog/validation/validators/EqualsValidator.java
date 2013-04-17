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
public class EqualsValidator<K, V, R> implements Validator<K, K, Boolean> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.kodelog.validation.validators.Validator#validate(java.lang.Object,
	 * java.lang.Object)
	 */
	public Boolean validate(final K paramValue, final K value) {
		if (!paramValue.equals(value)) {
			throw new IllegalStateException(
					(new StringBuilder(paramValue.toString()).append(" is not equal to ").append(value.toString()))
							.toString());
		}
		return true;
	}
}
