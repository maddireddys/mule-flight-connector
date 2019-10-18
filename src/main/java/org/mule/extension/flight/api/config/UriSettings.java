package org.mule.extension.flight.api.config;

import static org.mule.runtime.extension.api.annotation.param.display.Placement.DEFAULT_TAB;

import org.mule.runtime.extension.api.annotation.param.ExclusiveOptionals;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

public class UriSettings {
	@Parameter
	@Optional
	@Placement(tab = DEFAULT_TAB, order = 2)
	private String path = "/";

	public String getPath() {
		return path;
	}
}
