/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.util;

import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.IndexVariableResolver;
import org.mvel2.integration.impl.IndexedVariableResolverFactory;
import org.mvel2.integration.impl.SimpleValueResolver;

/**
 * @author Mike Brock .
 */
public class SimpleVariableSpaceModel extends VariableSpaceModel {
  public SimpleVariableSpaceModel(String[] varNames) {
    this.allVars = varNames;
  }

  public VariableResolverFactory createFactory(Object[] vals) {
    VariableResolver[] resolvers = new VariableResolver[allVars.length];
    for (int i = 0; i < resolvers.length; i++) {
      if (i >= vals.length) {
        resolvers[i] = new SimpleValueResolver(null);
      }
      else {
        resolvers[i] = new IndexVariableResolver(i, vals);
      }
    }

    return new IndexedVariableResolverFactory(allVars, resolvers);
  }
}
