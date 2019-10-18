package org.mule.extension.flight.api.config;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Example;

public class UrlConfiguration {
	@Parameter
	@Optional
	@Example("http://www.mulesoft.com")
	private String basePath;

	public String getBasePath() {
		return basePath;
	}
}
