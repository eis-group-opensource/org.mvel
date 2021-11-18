/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.util;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;

import java.util.Arrays;
import java.util.Set;

/**
 * @author Mike Brock .
 */
public class VariableSpaceCompiler {
  private static final Object[] EMPTY_OBJ = new Object[0];
  
  public static SharedVariableSpaceModel compileShared(String expr, ParserContext pCtx) {
    return compileShared(expr, pCtx, EMPTY_OBJ);
  }
  
  public static SharedVariableSpaceModel compileShared(String expr, ParserContext pCtx, Object[] vars) {
    String[] varNames = pCtx.getIndexedVarNames();

    ParserContext analysisContext = ParserContext.create();
    analysisContext.setIndexAllocation(true);

    MVEL.analysisCompile(expr, analysisContext);

    Set<String> localNames = analysisContext.getVariables().keySet();

    pCtx.addIndexedLocals(localNames);

    String[] locals = localNames.toArray(new String[localNames.size()]);
    String[] allVars = new String[varNames.length + locals.length];

    System.arraycopy(varNames, 0, allVars, 0, varNames.length);
    System.arraycopy(locals, 0, allVars, varNames.length, locals.length);

    return new SharedVariableSpaceModel(allVars, vars);
  }

  public static SimpleVariableSpaceModel compile(String expr, ParserContext pCtx) {
    String[] varNames = pCtx.getIndexedVarNames();

    ParserContext analysisContext = ParserContext.create();
    analysisContext.setIndexAllocation(true);

    MVEL.analysisCompile(expr, analysisContext);

    Set<String> localNames = analysisContext.getVariables().keySet();

    pCtx.addIndexedLocals(localNames);

    String[] locals = localNames.toArray(new String[localNames.size()]);
    String[] allVars = new String[varNames.length + locals.length];

    System.arraycopy(varNames, 0, allVars, 0, varNames.length);
    System.arraycopy(locals, 0, allVars, varNames.length, locals.length);

    return new SimpleVariableSpaceModel(allVars);
  }
}
