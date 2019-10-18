
package org.mule.extension.flight.api.config;

import org.mule.runtime.extension.api.annotation.param.Parameter;

public class ResponseAttributes {
	@Parameter
	private final int statusCode;
	
	public ResponseAttributes(int statusCode) {
	    this.statusCode = statusCode;
	  }
}
