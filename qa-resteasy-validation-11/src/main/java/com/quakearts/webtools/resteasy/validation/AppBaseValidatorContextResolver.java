package com.quakearts.webtools.resteasy.validation;

import javax.validation.BootstrapConfiguration;
import javax.validation.Configuration;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.ValidatorFactory;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.spi.validation.GeneralValidatorCDI;

@Provider
public class AppBaseValidatorContextResolver implements ContextResolver<GeneralValidatorCDI> {
	private static ValidatorFactory validatorFactory;
	private static Configuration<?> config;
	private static BootstrapConfiguration bootstrapConfiguration;

	public GeneralValidatorCDI getContext(Class<?> type) {
		try {
			return new AppBaseValidatorImpl(getValidatorFactory(), 
					getBootstrapConfiguration().isExecutableValidationEnabled(),
					getBootstrapConfiguration().getDefaultValidatedExecutableTypes());
		} catch (Exception e) {
			throw new ValidationException("Unable to load validation support", e);
		}
	}

	private static synchronized BootstrapConfiguration getBootstrapConfiguration() {
		if (bootstrapConfiguration == null) {
			bootstrapConfiguration = getConfiguration().getBootstrapConfiguration();
		}
		
		return bootstrapConfiguration;
	}

	private static synchronized ValidatorFactory getValidatorFactory() {
		if (validatorFactory == null) 
			validatorFactory = getConfiguration().buildValidatorFactory();

		return validatorFactory;
	}

	private static synchronized Configuration<?> getConfiguration() {
		if(config == null)
			config = Validation.byDefaultProvider().configure();

		return config;
	}

}
