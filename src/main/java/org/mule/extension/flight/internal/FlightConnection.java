package org.mule.extension.flight.internal;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.mule.extension.flight.api.config.ResponseAttributes;
import org.mule.extension.flight.api.config.UrlConfiguration;
import org.mule.runtime.api.metadata.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.runtime.operation.Result;
import org.mule.runtime.extension.api.runtime.operation.Result.Builder;
import org.mule.runtime.http.api.domain.entity.InputStreamHttpEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * This class represents an extension connection just as example (there is no real connection with anything here c:).
 */
public final class FlightConnection {
  private static final String CONTENT_TYPE = "Content-type";
  private static final String CONTENT_TYPE_JSON = "application/json";
  
  private final Logger LOGGER = LoggerFactory.getLogger(FlightConnection.class);
  
  private CloseableHttpClient client;

  public FlightConnection() {
    this.client =  HttpClients.createDefault();
  }
  
  public Result<InputStream,ResponseAttributes> getFlightById(String url) throws ClientProtocolException, IOException {
	return buildResponse(this.client.execute(new HttpGet(url)));
  }
  
  public Result<InputStream,ResponseAttributes> getFlights(String url) throws ClientProtocolException, IOException {
	return buildResponse(this.client.execute(new HttpGet(url)));
  }
  
  public Result<InputStream,ResponseAttributes> saveFlight(Object body, String url) throws ClientProtocolException, IOException {
	HttpPost post = new HttpPost(url);
	LOGGER.warn(body.toString());
	//TODO: post logic goes here
	return buildDummyResponse();
  }
  
  private MediaType getMediaType(final String contentTypeValue) {
	return Optional.ofNullable(contentTypeValue).map(this::parseMediaType).orElse(MediaType.ANY);
  }

  private MediaType parseMediaType(final String contentTypeValue) {
	try {
		return MediaType.parse(contentTypeValue);
	} catch (IllegalArgumentException e) {
		return MediaType.ANY;
	}
  }
  
  private Result<InputStream, ResponseAttributes> buildResponse(HttpResponse httpresponse) throws UnsupportedOperationException, IOException {
	HttpEntity entity = httpresponse.getEntity();
	Builder<InputStream, ResponseAttributes> builder = Result.builder();
	String responseContentType = httpresponse.getHeaders(CONTENT_TYPE).toString();
	builder.mediaType(getMediaType(responseContentType));
	builder.length(entity.getContentLength());
	ResponseAttributes attributes = new ResponseAttributes(httpresponse.getStatusLine().getStatusCode());
	return builder.output(entity.getContent()).attributes(attributes).build();
  }
  
  private Result<InputStream, ResponseAttributes> buildDummyResponse() throws UnsupportedOperationException, IOException {
	String entity = "{\"status\":\"success\",\"message\":\"Flight Saved(Read Only)\"}";
	Builder<InputStream, ResponseAttributes> builder = Result.builder();
	String responseContentType = CONTENT_TYPE_JSON;
	builder.mediaType(getMediaType(responseContentType));
	builder.length(entity.length());
	ResponseAttributes attributes = new ResponseAttributes(201);
	return builder.output(new ByteArrayInputStream(entity.getBytes())).attributes(attributes).build();
  }
  
  public void invalidate() throws IOException {
    client.close();
  }
}
