package com.poc.designpatterns.builderpattern;

/**
 * All validators must implement this interface.
 * 
 * @author 73ddy
 */
public interface Validator {
	/**
	 * Validates the parameter value against a validation rule.
	 * 
	 * @param param
	 * @param value
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 */
	void validate(final String param, final Object value) throws IllegalArgumentException, IllegalStateException;
}
