package com.poc.designpatterns.builderpattern.executor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.poc.designpatterns.builderpattern.Validation;

public class Executor {
	// String
	final static String NULL_STRING = null;
	final static String EMPTY_STRING = "";
	final static String NON_EMPTY_STRING = "test";
	// Collections
	@SuppressWarnings("rawtypes")
	final static List EMPTY_LIST = new ArrayList<String>();
	@SuppressWarnings({ "serial", "rawtypes" })
	final static List NON_EMPTY_LIST = new ArrayList<String>() {
		{
			this.add(NON_EMPTY_STRING);
		}
	};
	@SuppressWarnings("rawtypes")
	final static Set EMPTY_SET = new HashSet<String>();
	@SuppressWarnings({ "rawtypes", "serial" })
	final static Set NON_EMPTY_SET = new HashSet<String>() {
		{
			this.add(NON_EMPTY_STRING);
		}
	};
	// Array
	final static Object[] EMPTY_ARRAY = new Object[0];
	final static Object[] NON_EMPTY_ARRAY = { NON_EMPTY_STRING };

	/**
	 * Here are some examples how to use the validation code. Please note that
	 * the parameter name will come into picture in case you wish to update the
	 * exceptions thrown and insert the param name in them
	 * 
	 * @param args
	 */
	public static void main(String... args) {
		/*********** Examples ***********/
		/*********** Is Empty Examples ************/
		try {

			// Check String for empty
			Validation.checkThat(NON_EMPTY_STRING).is(Validation.isNotEmpty());
			// OR pass the parameter name
			Validation.checkThat(NON_EMPTY_STRING).as("username").is(Validation.isNotEmpty());

			// Similar check for Collections
			Validation.checkThat(EMPTY_LIST).is(Validation.isNotEmpty());

			// Similar check for Arrays
			Validation.checkThat(NON_EMPTY_ARRAY).is(Validation.isNotEmpty());

		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		/*********** Is Null Examples ************/
		try {

			Validation.checkThat(NULL_STRING).is(Validation.isNotNull());
			// OR pass the parameter name
			Validation.checkThat(null).as("username").is(Validation.isNotNull());

		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		/*********** Complex Validator Combination Examples ************/
		try {

			// Notice the and method invocation
			Validation.checkThat(NULL_STRING).is(Validation.isNotNull()).add(Validation.isNotEmpty());
			// OR pass the parameter name
			Validation.checkThat(null).as("username").is(Validation.isNotNull()).add(Validation.isNotEmpty());

		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
}
