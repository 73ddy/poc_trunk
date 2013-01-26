package com.poc.designpatterns.builderpattern;

import java.util.Collection;

/**
 * Validation class used for validation. The class is an implementation of the
 * famous Builder Pattern. In order to validate a param with a certain value.
 * Example- <br />
 * {@code
 *     Validation.checkThat(someString).as(paramName).is(Validation.isNotNull()).add(Validation.isNotEmpty());
 * }</br> This code validates if the string someString is not null and is not
 * empty(of lenght zero).
 * 
 * @author 73ddy
 */
public class Validation {

	private static Validator NOT_NULL_VALIDATOR = new Validator() {
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.poc.designpatterns.builderpattern.Validator#validate(java.lang
		 * .String, java.lang.Object)
		 */
		@Override
		public void validate(final String param, final Object value) throws IllegalArgumentException,
						IllegalStateException {
			if (null == value) {
				throw new IllegalStateException("Null value found when expecting not null value.");
			}
		}
	};

	private static Validator NOT_EMPTY_VALIDATOR = new Validator() {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.poc.designpatterns.builderpattern.Validator#validate(java.lang
		 * .String, java.lang.Object)
		 */
		@Override
		public void validate(final String param, final Object value) throws IllegalArgumentException,
						IllegalStateException {
			if ((null == value)
							|| (!(value instanceof String) && !(value instanceof Collection) && !(value.getClass()
											.isArray()))) {
				throw new IllegalArgumentException("Only not null Strings, Collection or Arrays are supported.");
			}

			if ((value instanceof String)) {
				if (((String) value).length() == 0) {
					throw new IllegalStateException("Empty string found when expecting: non-empty string.");
				}
			} else if (value instanceof Collection) {
				if (((Collection<?>) value).size() == 0) {
					throw new IllegalStateException("Empty collection found when expecting: non-empty collection.");
				}
			} else if (value.getClass().isArray()) {
				boolean isEmpty = true;
				for (Object obj : ((Object[]) value)) {
					if (null != obj) {
						isEmpty = false;
						break;
					}
				}
				if (isEmpty) {
					throw new IllegalStateException("Empty array found when expecting: non-empty array.");
				}
			}
		}
	};

	/**
	 * Making the constructor private to prevent any instantiation of the
	 * {@link Validation}
	 */
	private Validation() {
	}

	/**
	 * Throws IllegalArgumentException if anything other than not null String,
	 * Collection or Array is passed. Throws IllegalStateException if - <br />
	 * <html>
	 * <ul>
	 * <li>1. The value passed is string and is empty.</li>
	 * <li>2. The value passed is collection and is empty.</li>
	 * <li>3. The value passed is an array and either the array is of length
	 * zero or every object of array is null.</li>
	 * </ul>
	 * </html>
	 * 
	 * @return not null validator
	 */
	public static Validator isNotNull() {
		return NOT_NULL_VALIDATOR;
	}

	/**
	 * Returns a not empty {@link Validator}
	 * 
	 * @return NOT NULL VALIDATOR
	 */
	public static Validator isNotEmpty() {
		return NOT_EMPTY_VALIDATOR;
	}

	/**
	 * Creates a new builder for the given value.
	 * 
	 * @param value
	 * @return {@link Builder}
	 */
	public static Builder checkThat(final Object value) {
		Builder builder = new Builder(value);
		return builder;
	}

	/**
	 * Builder class for Validation
	 * 
	 * @author 73ddy
	 */
	public static class Builder {
		// The value to be validated
		Object value;

		// The param name for the value to be validated
		String param;

		/**
		 * Constructor must be always called with the value to be validated.
		 * 
		 * @param {@link Object}
		 */
		public Builder(final Object value) {
			this.value = value;
			this.param = null;
		}

		/**
		 * Sets the parameter name for the value to be validated
		 * 
		 * @param -param name for value to be validated
		 * @return {@link Builder}
		 */
		public Builder as(final String param) {
			this.param = param;
			return this;
		}

		/**
		 * Validates the value against the provided {@link Validator} by running
		 * the {@link Validator#validate(String, Object)} method
		 * 
		 * @param validator
		 * @return {@link Builder}
		 * @throws IllegalArgumentException
		 * @throws IllegalStateException
		 */
		public Constraint is(final Validator validator) throws IllegalArgumentException, IllegalStateException {
			validator.validate(this.param, this.value);
			return new Constraint(this);
		}

		/**
		 * Returns the value to be validated
		 * 
		 * @return {@link Object}
		 */
		public Object getValue() {
			return this.value;
		}

		/**
		 * Returns the param name of the parameter to be validated
		 * 
		 * @return {@link String}
		 */
		public String getParam() {
			return this.param;
		}
	}

	/**
	 * Each validation returns a Constraint. And each constraint can be added to
	 * a reference of a constraint.
	 * 
	 * @author 73ddy
	 */
	public static class Constraint {
		Builder builder = null;

		/**
		 * There is no use of a constraint without a builder, which feeds a
		 * constraint the param value and param name
		 * 
		 * @param builder
		 */
		public Constraint(final Builder builder) {
			this.builder = builder;
		}

		/**
		 * This method is responsible for validating the value against one
		 * constraint. This returns an object of Constraint class.
		 * 
		 * @param {@link Validator}
		 * @return
		 */
		public Constraint add(final Validator validator) {
			validator.validate(builder.getParam(), builder.getValue());
			return this;
		}
	}
}
