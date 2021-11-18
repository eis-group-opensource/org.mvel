/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.optimizers.impl.refl.nodes;

import org.mvel2.MVEL;
import org.mvel2.integration.PropertyHandler;
import org.mvel2.integration.VariableResolverFactory;


public class PropertyHandlerAccessor extends BaseAccessor {
  private String propertyName;
  private PropertyHandler propertyHandler;
  private Class conversionType;

  public PropertyHandlerAccessor(String propertyName, Class conversionType, PropertyHandler propertyHandler) {
    this.propertyName = propertyName;
    this.conversionType = conversionType;
    this.propertyHandler = propertyHandler;
  }

  public Object getValue(Object ctx, Object elCtx, VariableResolverFactory variableFactory) {
    if (!conversionType.isAssignableFrom(ctx.getClass())) {
      if (nextNode != null) {
        return nextNode.getValue(MVEL.getProperty(propertyName, ctx), elCtx, variableFactory);
      }
      else {
        return MVEL.getProperty(propertyName, ctx);
      }
    }
    try {
      if (nextNode != null) {
        return nextNode.getValue(propertyHandler.getProperty(propertyName, ctx, variableFactory), elCtx, variableFactory);
      }
      else {
        return propertyHandler.getProperty(propertyName, ctx, variableFactory);
      }
    }
    catch (Exception e) {
      throw new RuntimeException("unable to access field", e);
    }
  }

  public Object setValue(Object ctx, Object elCtx, VariableResolverFactory variableFactory, Object value) {
    if (nextNode != null) {
      return nextNode.setValue(propertyHandler.getProperty(propertyName, ctx, variableFactory), ctx, variableFactory, value);
    }
    else {
      return propertyHandler.setProperty(propertyName, ctx, variableFactory, value);
    }
  }

  public Class getKnownEgressType() {
    return Object.class;
  }
}
