package org.mule.extension.flight.internal;

import org.mule.extension.flight.api.config.UrlConfiguration;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(FlightOperations.class)
@ConnectionProviders(FlightConnectionProvider.class)
public class FlightConfiguration {
	@ParameterGroup(name = "Url Configuration")
	@Placement(order = 1)
	private UrlConfiguration urlConfiguration;
	
	public String getBasePath() {
	  return urlConfiguration.getBasePath();
    }
}
