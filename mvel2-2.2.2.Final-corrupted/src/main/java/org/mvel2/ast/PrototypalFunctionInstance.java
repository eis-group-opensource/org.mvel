/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.ast;

import org.mvel2.integration.VariableResolverFactory;

/**
 * @author Mike Brock
 */
public class PrototypalFunctionInstance extends FunctionInstance {
  private final VariableResolverFactory resolverFactory;

  public PrototypalFunctionInstance(Function function, VariableResolverFactory resolverFactory) {
    super(function);
    this.resolverFactory = resolverFactory;
  }

  @Override
  public Object call(Object ctx, Object thisValue, VariableResolverFactory factory, Object[] parms) {
    return function.call(ctx, thisValue, new InvokationContextFactory(factory, resolverFactory), parms);
  }

  public VariableResolverFactory getResolverFactory() {
    return resolverFactory;
  }

  public String toString() {
    return "function_prototype:" + function.getName();
  }

}

