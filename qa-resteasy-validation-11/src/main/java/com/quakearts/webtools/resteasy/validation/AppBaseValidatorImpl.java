package com.quakearts.webtools.resteasy.validation;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintDefinitionException;
import javax.validation.ConstraintViolation;
import javax.validation.GroupDefinitionException;
import javax.validation.MessageInterpolator;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableType;
import org.jboss.resteasy.api.validation.ResteasyViolationException;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.InjectorFactory;
import org.jboss.resteasy.spi.validation.GeneralValidatorCDI;

public class AppBaseValidatorImpl implements GeneralValidatorCDI {
	public static final String SUPPRESS_VIOLATION_PATH = "resteasy.validation.suppress.path";

	private ValidatorFactory validatorFactory;

	public AppBaseValidatorImpl(ValidatorFactory validatorFactory, boolean isExecutableValidationEnabled,
			Set<ExecutableType> defaultValidatedExecutableTypes) {
		this.validatorFactory = validatorFactory;
	}

	@Override
	public void validate(HttpRequest request, Object object, Class<?>... groups) {
		Validator validator = getValidator(request);
		Set<ConstraintViolation<Object>> cvs = null;

		try {
			cvs = validator.validate(object, groups);
		} catch (Exception e) {
			AppBaseViolationsContainer violationsContainer = getViolationsContainer(request, object);
			violationsContainer.setException(e);
			violationsContainer.setFieldsValidated(true);
			throw toValidationException(e, violationsContainer);
		}

		AppBaseViolationsContainer violationsContainer = getViolationsContainer(request, object);
		violationsContainer.addViolations(cvs);
		violationsContainer.setFieldsValidated(true);
	}

	private ValidationException toValidationException(Exception exception,
			AppBaseViolationsContainer simpleViolationsContainer) {
		if (exception instanceof ConstraintDeclarationException || exception instanceof ConstraintDefinitionException
				|| exception instanceof GroupDefinitionException) {
			return (ValidationException) exception;
		}
		return new ResteasyViolationException(simpleViolationsContainer);
	}

	@Override
	public void checkViolations(HttpRequest request) {
		throw new UnsupportedOperationException("Execution validation must occur within a CDI interceptor");
	}

	@Override
	public void checkViolationsfromCDI(HttpRequest request) {
		if (request == null) {
			return;
		}

		AppBaseViolationsContainer violationsContainer = AppBaseViolationsContainer.class
				.cast(request.getAttribute(AppBaseViolationsContainer.class.getName()));
		if (violationsContainer != null && violationsContainer.size() > 0) {
			throw new ResteasyViolationException(violationsContainer,
					request.getHttpHeaders().getAcceptableMediaTypes());
		}
	}

	@Override
	public void validateAllParameters(HttpRequest request, Object object, Method method, Object[] parameterValues,
			Class<?>... groups) {
		throw new UnsupportedOperationException("Execution validation must occur within a CDI interceptor");
	}

	@Override
	public void validateReturnValue(HttpRequest request, Object object, Method method, Object returnValue,
			Class<?>... groups) {
		throw new UnsupportedOperationException("Execution validation must occur within a CDI interceptor");
	}

	@Override
	public boolean isValidatable(Class<?> clazz) {
		return false;
	}

	@Override
	public boolean isValidatable(Class<?> clazz, InjectorFactory injectorFactory) {
		return false;
	}

	@Override
	public boolean isValidatableFromCDI(Class<?> clazz) {
		return true;
	}

	@Override
	public boolean isMethodValidatable(Method m) {
		return false;
	}

	@Override
	public void checkForConstraintViolations(HttpRequest request, Exception e) {
		throw new UnsupportedOperationException("Execution validation must occur within a CDI interceptor");
	}

	private Validator getValidator(HttpRequest request) {
		Validator validator = Validator.class.cast(request.getAttribute(Validator.class.getName()));
		if (validator == null) {
			Locale locale = getLocale(request);
			if (locale == null) {
				validator = validatorFactory.getValidator();
			} else {
				MessageInterpolator interpolator = new LocaleSpecificMessageInterpolator(
						validatorFactory.getMessageInterpolator(), locale);
				validator = validatorFactory.usingContext().messageInterpolator(interpolator).getValidator();
			}
			request.setAttribute(Validator.class.getName(), validator);
		}
		return validator;
	}

	private AppBaseViolationsContainer getViolationsContainer(HttpRequest request, Object target) {
		if (request == null) {
			return new AppBaseViolationsContainer(target);
		}

		AppBaseViolationsContainer violationsContainer = AppBaseViolationsContainer.class
				.cast(request.getAttribute(AppBaseViolationsContainer.class.getName()));
		if (violationsContainer == null) {
			violationsContainer = new AppBaseViolationsContainer(target);
			request.setAttribute(AppBaseViolationsContainer.class.getName(), violationsContainer);
		}
		return violationsContainer;
	}

	private Locale getLocale(HttpRequest request) {
		if (request == null) {
			return null;
		}
		List<Locale> locales = request.getHttpHeaders().getAcceptableLanguages();
		Locale locale = locales == null || locales.isEmpty() ? null : locales.get(0);
		return locale;
	}

	static private class LocaleSpecificMessageInterpolator implements MessageInterpolator {
		private final MessageInterpolator interpolator;
		private final Locale locale;

		public LocaleSpecificMessageInterpolator(MessageInterpolator interpolator, Locale locale) {
			this.interpolator = interpolator;
			this.locale = locale;
		}

		@Override
		public String interpolate(String messageTemplate, Context context) {
			return interpolator.interpolate(messageTemplate, context, locale);
		}

		@Override
		public String interpolate(String messageTemplate, Context context, Locale locale) {
			return interpolator.interpolate(messageTemplate, context, locale);
		}
	}

}
