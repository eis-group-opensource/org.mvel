/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.ast;

import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;

import java.util.HashMap;

/**
 * @author Mike Brock
 */
public class NewObjectPrototype extends ASTNode {
  private Function function;

  public NewObjectPrototype(ParserContext pCtx, Function function) {
    super(pCtx);
    this.function = function;
  }

  @Override
  public Object getReducedValue(Object ctx, Object thisValue, VariableResolverFactory factory) {
    final MapVariableResolverFactory resolverFactory = new MapVariableResolverFactory(new HashMap<String, Object>(), factory);
    function.getCompiledBlock().getValue(ctx, thisValue, resolverFactory);
    return new PrototypalFunctionInstance(function, resolverFactory);
  }

  @Override
  public Object getReducedValueAccelerated(Object ctx, Object thisValue, VariableResolverFactory factory) {
    return getReducedValue(ctx, thisValue, factory);
  }
}
