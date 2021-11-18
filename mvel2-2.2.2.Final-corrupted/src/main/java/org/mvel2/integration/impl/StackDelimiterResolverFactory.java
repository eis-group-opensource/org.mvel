/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.integration.impl;

import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;

import java.util.Set;

/**
 * @author Mike Brock
 */
public class StackDelimiterResolverFactory extends StackDemarcResolverFactory {
  public StackDelimiterResolverFactory(VariableResolverFactory delegate) {
    super(delegate);
  }

  public VariableResolver createVariable(String name, Object value) {
    VariableResolverFactory delegate = getDelegate();
    VariableResolverFactory nextFactory = delegate.getNextFactory();
    delegate.setNextFactory(null);
    VariableResolver resolver = delegate.createVariable(name, value);
    delegate.setNextFactory(nextFactory);
    return resolver;
  }
}
