package com.kodelog.validation.validators;

/**
 * Your Validators must implement this interface.
 * 
 * @author 73ddy
 */
public interface Validator<K, V, Response> {

	/**
	 * Validates the parameter value against a given value.
	 * 
	 * @param paramValue
	 * @param value
	 */
	public Response validate(K param, V value);
}
