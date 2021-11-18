/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.ast;

import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;

public class NewPrototypeNode extends ASTNode {
  private String protoName;

  public NewPrototypeNode(TypeDescriptor t, ParserContext pCtx) {
    super(pCtx);
    this.protoName = t.getClassName();
  }

  @Override
  public Object getReducedValueAccelerated(Object ctx, Object thisValue, VariableResolverFactory factory) {
    return ((Proto) factory.getVariableResolver(protoName).getValue())
        .newInstance(ctx, thisValue, factory);
  }

  @Override
  public Object getReducedValue(Object ctx, Object thisValue, VariableResolverFactory factory) {
    return ((Proto) factory.getVariableResolver(protoName).getValue())
        .newInstance(ctx, thisValue, factory);
  }
}
