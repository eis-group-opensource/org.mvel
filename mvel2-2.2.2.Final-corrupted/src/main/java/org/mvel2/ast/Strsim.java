/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.ast;

import org.mvel2.CompileException;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.CompilerTools;

import static org.mvel2.util.ParseTools.similarity;

public class Strsim extends ASTNode {
  private ASTNode stmt;
  private ASTNode soundslike;

  public Strsim(ASTNode stmt, ASTNode clsStmt, ParserContext pCtx) {
    super(pCtx);
    this.stmt = stmt;
    this.soundslike = clsStmt;
    CompilerTools.expectType(clsStmt, String.class, true);
  }

  public Object getReducedValueAccelerated(Object ctx, Object thisValue, VariableResolverFactory factory) {
    return similarity(String.valueOf(soundslike.getReducedValueAccelerated(ctx, thisValue, factory)),
        ((String) stmt.getReducedValueAccelerated(ctx, thisValue, factory)));
  }

  public Object getReducedValue(Object ctx, Object thisValue, VariableResolverFactory factory) {
    try {
      String i = String.valueOf(soundslike.getReducedValue(ctx, thisValue, factory));
      if (i == null) throw new ClassCastException();

      String x = (String) stmt.getReducedValue(ctx, thisValue, factory);
      if (x == null) throw new CompileException("not a string: " + stmt.getName(), stmt.getExpr(), getStart());

      return similarity(i, x);
    }
    catch (ClassCastException e) {
      throw new CompileException("not a string: " + soundslike.getName(), soundslike.getExpr(), soundslike.getStart());
    }

  }

  public Class getEgressType() {
    return Boolean.class;
  }
}
