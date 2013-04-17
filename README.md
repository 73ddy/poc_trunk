Using the Validation API
=========

private static final Date dateParam = new Date();<br />
private static final Date dateValue = new Date();<br />
private static final String stringValue = "param1";<br />
private static final String stringValue = "param1";<br />

// Single validations, when not expecting a response
ValidationHelper.validateValue(stringParam).against(stringValue).usingValidator(ValidationHelper.isEqual());

// Single validations, when expecting a response - getValidationResult(Validator)
boolean returnValue = ValidationHelper.validateValue(stringParam).against(stringValue)
		.getValidationResult(ValidationHelper.isEqual());<br />
// The above returns true

// Mutliple validations withing a compound validator (not null + equal)
ValidationHelper.validateValue(stringParam).against(stringValue)
		.usingValidator(ValidationHelper.isNotNullAndEqual());

// Mutliple validations in the order of validator occurrence
ValidationHelper.validateValue(stringParam).against(stringValue)
		.usingValidator(ValidationHelper.isNotNull())
		.and(ValidationHelper.isEqual());<br />
// OR<br />
ValidationHelper.validateValue(stringParam).against(stringValue)
.usingValidators(ValidationHelper.isNotNull(), ValidationHelper.isEqual());


// Some innovative validators like 'greater than'. See implementaion of {@link GreaterThanValidator}<br />
// The following will result in an IllegalStateException to be thrown since dateParam is not greater than dateValue<br />
ValidationHelper.validateValue(dateParam).against(dateValue)
		.usingValidator(ValidationHelper.isGreaterThan());
