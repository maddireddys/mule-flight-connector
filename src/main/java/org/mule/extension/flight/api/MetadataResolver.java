package org.mule.extension.flight.api;

import static org.mule.metadata.api.model.MetadataFormat.JAVA;

import org.mule.metadata.api.builder.BaseTypeBuilder;
import org.mule.metadata.api.model.AnyType;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.resolving.OutputStaticTypeResolver;

public class MetadataResolver extends OutputStaticTypeResolver {

	  private static final AnyType ANY_TYPE = BaseTypeBuilder.create(JAVA).anyType().build();

	  @Override
	  public String getCategoryName() {
	    return "HTTP";
	  }

	  @Override
	  public MetadataType getStaticMetadata() {
	    return ANY_TYPE;
	  }
	}
