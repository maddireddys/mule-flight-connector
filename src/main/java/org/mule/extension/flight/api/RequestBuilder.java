package org.mule.extension.flight.api;

import static org.mule.runtime.api.util.MultiMap.emptyMultiMap;
import static java.util.Collections.emptyMap;
import static java.util.Collections.unmodifiableMap;
import static java.util.regex.Matcher.quoteReplacement;
import static java.lang.String.format;

import java.util.Map;

import org.mule.runtime.api.metadata.TypedValue;
import org.mule.runtime.api.util.MultiMap;
import org.mule.runtime.extension.api.annotation.param.Content;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

public class RequestBuilder {
	  @Parameter
	  @Content(primary = true)
	  private TypedValue<Object> body;

	  @Parameter
	  @Optional
	  @Content
	  protected MultiMap<String, String> headers = emptyMultiMap();

	  @Parameter
	  @Optional
	  @Content
	  @DisplayName("URI Parameters")
	  private Map<String, String> uriParams = emptyMap();

	  @Parameter
	  @Optional
	  @Content
	  @DisplayName("Query Parameters")
	  private MultiMap<String, String> queryParams = emptyMultiMap();

	  @Parameter
	  @Optional
	  private String correlationId;
	  
	 
	  public TypedValue<Object> getBody() {
	    return body;
	  }

	  
	  public void setBody(TypedValue<Object> body) {
	    this.body = body;
	  }

	  
	  public MultiMap<String, String> getHeaders() {
	    return headers;
	  }

	  
	  public void setHeaders(MultiMap<String, String> headers) {
	    this.headers = headers;
	  }
	  
	  public String replaceUriParams(String path) {
		    for (String uriParamName : uriParams.keySet()) {
		      String uriParamValue = uriParams.get(uriParamName);

		      if (uriParamValue == null) {
		        throw new NullPointerException(format("Expression {%s} evaluated to null.", uriParamName));
		      }
		      
		      path = path.replaceAll("\\{" + uriParamName + "\\}", quoteReplacement(uriParamValue));
		    }
		    return path;
		  }
	  
	  public MultiMap<String, String> getQueryParams() {
		    return queryParams.toImmutableMultiMap();
		  }

		  public Map<String, String> getUriParams() {
		    return unmodifiableMap(uriParams);
		  }

		  public void setQueryParams(MultiMap<String, String> queryParams) {
		    this.queryParams = queryParams;
		  }

		  public void setUriParams(Map<String, String> uriParams) {
		    this.uriParams = uriParams;
		  }


		public String getCorrelationId() {
			return correlationId;
		}


		public void setCorrelationId(String correlationId) {
			this.correlationId = correlationId;
		}
}
