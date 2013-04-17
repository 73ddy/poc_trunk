package com.kodelog.validation.utility;

import com.kodelog.validation.complexvalidators.NotNullEqualValidator;
import com.kodelog.validation.innovativevalidators.GreaterThanValidator;
import com.kodelog.validation.validators.EqualsValidator;
import com.kodelog.validation.validators.NotNullValidator;
import com.kodelog.validation.validators.Validator;

/**
 * Validation class used for validation. The class is an implementation of the
 * famous Builder Pattern. In order to validate a param with a certain value.
 * Example- <br /> {@code
 *     ValidationHelper.validateValue(new Integer(3)).against(1)
				.usingValidator(ValidationHelper.isNotNull())
				.and(ValidationHelper.isEqual(), true)
 * }</br> This code validates if (new Integer(3)) is equal to 3.
 * 
 * @author 73ddy
 */
public final class ValidationHelper {

	// Simple atomic validators
	private final static Validator<Object, Object, Object> NOT_NULL_VALIDATOR = new NotNullValidator();
	private final static Validator<Object, Object, Boolean> EQUALS_VALIDATOR = new EqualsValidator();

	// Complex/Compound Validators
	private final static Validator<Object, Object, Boolean> NOT_NULL_EQUALS_VALIDATOR = new NotNullEqualValidator();

	// More Innovative Validators
	private final static Validator<Object, Object, Boolean> GREATER_THAN_VALIDATOR = new GreaterThanValidator();

	/**
	 * Making the constructor private to prevent any instantiation of the
	 * {@link ValidationHelper}
	 */
	private ValidationHelper() {
	}

	/**
	 * Returns a null {@link Validator}
	 * 
	 * @return {@link NotNullValidator}
	 */
	public static Validator<Object, Object, Object> isNotNull() {
		return NOT_NULL_VALIDATOR;
	}

	/**
	 * Returns equals {@link Validator}
	 * 
	 * @return {@link EqualsValidator}
	 */
	public static Validator<Object, Object, Boolean> isEqual() {
		return EQUALS_VALIDATOR;
	}

	/**
	 * Returns a not null and not empty {@link Validator}
	 * 
	 * @return {@link NotNullEqualValidator}
	 */
	public static Validator<Object, Object, Boolean> isNotNullAndEqual() {
		return NOT_NULL_EQUALS_VALIDATOR;
	}

	/**
	 * Returns a not null and not empty {@link Validator}
	 * 
	 * @return {@link GreaterThanValidator}
	 */
	public static Validator<Object, Object, Boolean> isGreaterThan() {
		return GREATER_THAN_VALIDATOR;
	}

	/**
	 * Creates a new builder for the given value.
	 * 
	 * @param value
	 * @return {@link Builder}
	 */
	public static Builder<Object, Object> validateValue(final Object paramValue) {
		return new Builder<Object, Object>(paramValue);
	}

	/**
	 * Builder class for Validation
	 * 
	 * @author 73ddy
	 */
	public static class Builder<K, V> {
		// The value to be validated
		private V value;

		// The param name for the value to be validated
		private K paramValue;

		/**
		 * Constructor must be always called with the value to be validated.
		 * 
		 * @param {@link Object}
		 */
		public Builder(final K paramValue) {
			this.value = null;
			this.paramValue = paramValue;
		}

		/**
		 * Sets the parameter name for the value to be validated
		 * 
		 * @param -param name for value to be validated
		 * @return {@link Builder}
		 */
		public Builder<K, V> against(final V value) {
			this.value = value;
			return this;
		}

		/**
		 * Validates the value against the provided {@link Validator} by running
		 * the {@link Validator#validate(String, Object)} method
		 * 
		 * @param validator
		 * @return {@link Constraint}
		 * @throws IllegalArgumentException
		 * @throws IllegalStateException
		 */
		public Constraint<K, V> usingValidator(final Validator<K, V, ?> validator) throws IllegalArgumentException,
				IllegalStateException {
			validator.validate(this.paramValue, this.value);
			return new Constraint<K, V>(this);
		}

		/**
		 * Validates the value against the provided {@link Validator} by calling
		 * the {@link Validator#validate(K, V)} method and returns the response
		 * object.
		 * 
		 * @param <R>
		 *            response object
		 * @param validator
		 * @return The response object of {@link Validator#validate(K, V)}
		 * @throws IllegalArgumentException
		 * @throws IllegalStateException
		 */
		public <R> R getValidationResult(final Validator<K, V, R> validator) throws IllegalArgumentException,
				IllegalStateException {
			return validator.validate(this.paramValue, this.value);
		}

		/**
		 * Validation using more than one validators at once.
		 * 
		 * @param validators
		 * @throws IllegalArgumentException
		 * @throws IllegalStateException
		 */
		public void usingValidators(final Validator<K, V, ?>... validators) throws IllegalArgumentException,
				IllegalStateException {
			if (validators != null) {
				for (Validator<K, V, ?> validator : validators) {
					validator.validate(this.paramValue, this.value);
				}
			}
		}

		/**
		 * Returns the value to be validated
		 * 
		 * @return <V>
		 */
		public V getValue() {
			return this.value;
		}

		/**
		 * Returns the param name of the parameter to be validated
		 * 
		 * @return <K>
		 */
		public K getParamValue() {
			return this.paramValue;
		}
	}

	/**
	 * Each validation returns a Constraint. And each constraint can be added to
	 * a reference of a constraint.
	 * 
	 * @author 73ddy
	 */
	public static class Constraint<K, V> {
		private Builder<K, V> builder = null;

		/**
		 * There is no use of a constraint without a builder, which feeds a
		 * constraint the param value and param name
		 * 
		 * @param builder
		 */
		public Constraint(final Builder<K, V> builder) {
			this.builder = builder;
		}

		/**
		 * This method is responsible for validating the value against one
		 * constraint. This returns an object of Constraint class.
		 * 
		 * @param {@link Validator}
		 * @return
		 */
		public Constraint<K, V> and(final Validator<K, V, ?> validator) {
			validator.validate(builder.getParamValue(), builder.getValue());
			return this;
		}

		/**
		 * Validates the given values and returns the response object of the
		 * validator.
		 * 
		 * @param <T>
		 * @param validator
		 * @param throwResult
		 * @return Response object returned after validation
		 */
		public <T> T and(final Validator<Object, Object, T> validator, boolean throwResult) {
			if (throwResult) {
				return validator.validate(builder.getParamValue(), builder.getValue());
			} else {
				return null;
			}
		}
	}
}