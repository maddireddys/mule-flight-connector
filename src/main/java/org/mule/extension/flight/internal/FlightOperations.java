package org.mule.extension.flight.internal;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.io.InputStream;

import org.mule.extension.flight.api.MetadataResolver;
import org.mule.extension.flight.api.RequestBuilder;
import org.mule.extension.flight.api.config.ResponseAttributes;
import org.mule.extension.flight.api.config.UriSettings;
import org.mule.runtime.extension.api.annotation.Streaming;
import org.mule.runtime.extension.api.annotation.metadata.OutputResolver;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.runtime.process.CompletionCallback;
import org.mule.runtime.extension.api.runtime.streaming.StreamingHelper;

/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class FlightOperations {
  private String EMPTY = "";
  
  @MediaType(value = ANY, strict = false)
  @OutputResolver(output = MetadataResolver.class)
  @Streaming
  public void getFlightById(@Placement(order = 1) @ParameterGroup(name = "URI Settings") UriSettings uriSettings,
		@Placement(order = 2) @Optional(defaultValue = "GET") String method,
		@Placement(order = 3) @ParameterGroup(name = "Request") RequestBuilder requestBuilder,
		@Config FlightConfiguration configuration,
		StreamingHelper streamingHelper, @Connection FlightConnection connection, 
		CompletionCallback<InputStream, ResponseAttributes> callback)  {
	try {
	  String url = this.buildUrl(uriSettings, requestBuilder, configuration);
	  callback.success(connection.getFlightById(url));
	} catch(Exception e) {
	  callback.error(e);
	}
  }
  
  @MediaType(value = ANY, strict = false)
  @OutputResolver(output = MetadataResolver.class)
  @Streaming
  public void getFlights(@Placement(order = 1) @ParameterGroup(name = "URI Settings") UriSettings uriSettings,
		@Placement(order = 2) @Optional(defaultValue = "GET") String method,
		@Placement(order = 3) @ParameterGroup(name = "Request") RequestBuilder requestBuilder,
		@Config FlightConfiguration configuration,
		StreamingHelper streamingHelper, @Connection FlightConnection connection, 
		CompletionCallback<InputStream, ResponseAttributes> callback)  {
	try {
	  String url = this.buildUrl(uriSettings, requestBuilder, configuration);
	  callback.success(connection.getFlights(url));
	} catch(Exception e) {
	  callback.error(e);
	}
  }
  
  @MediaType(value = ANY, strict = false)
  @OutputResolver(output = MetadataResolver.class)
  @Streaming
  public void saveFlight(@Placement(order = 1) @ParameterGroup(name = "URI Settings") UriSettings uriSettings,
		@Placement(order = 2) @Optional(defaultValue = "POST") String method,
		@Placement(order = 3) @ParameterGroup(name = "Request") RequestBuilder requestBuilder,
		@Config FlightConfiguration configuration,
		StreamingHelper streamingHelper, @Connection FlightConnection connection, 
		CompletionCallback<InputStream, ResponseAttributes> callback)  {
	try {
	  String url = this.buildUrl(uriSettings, requestBuilder, configuration);
	  callback.success(connection.saveFlight(requestBuilder.getBody().getValue(), url));
	} catch(Exception e) {
	  callback.error(e);
	}
  }

  private String buildUrl(UriSettings uriSettings, RequestBuilder requestBuilder, FlightConfiguration configuration) {
	  String path = java.util.Optional.ofNullable(uriSettings.getPath()).filter(p -> !p.equalsIgnoreCase("/")).orElse(EMPTY);
	  String url = configuration.getBasePath() + path;
	  return requestBuilder.replaceUriParams(url);
  }
}
