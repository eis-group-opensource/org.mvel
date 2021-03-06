/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
/**
 * MVEL (The MVFLEX Expression Language)
 *
 * Copyright (C) 2007 Christopher Brock, MVFLEX/Valhalla Project and the Codehaus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.mvel2.optimizers.impl.refl.nodes;

import org.mvel2.compiler.Accessor;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.optimizers.AccessorOptimizer;
import org.mvel2.optimizers.OptimizerFactory;

import static org.mvel2.compiler.AbstractParser.getCurrentThreadParserContext;

/**
 * @author Christopher Brock
 */
public class Union implements Accessor {
  private Accessor accessor;
  private char[] nextExpr;
  private int start;
  private int offset;
  private Accessor nextAccessor;

  public Union(Accessor accessor, char[] nextAccessor, int start, int offset) {
    this.accessor = accessor;
    this.start = start;
    this.offset = offset;
    this.nextExpr = nextAccessor;
  }

  public Object getValue(Object ctx, Object elCtx, VariableResolverFactory variableFactory) {
    if (nextAccessor == null) {
      return get(ctx, elCtx, variableFactory);
    }
    else {
      return nextAccessor.getValue(get(ctx, elCtx, variableFactory), elCtx, variableFactory);
    }
  }

  public Object setValue(Object ctx, Object elCtx, VariableResolverFactory variableFactory, Object value) {
    return nextAccessor.setValue(get(ctx, elCtx, variableFactory), elCtx, variableFactory, value);
  }

  private Object get(Object ctx, Object elCtx, VariableResolverFactory variableFactory) {
    if (nextAccessor == null) {
      Object o = accessor.getValue(ctx, elCtx, variableFactory);
      AccessorOptimizer ao = OptimizerFactory.getDefaultAccessorCompiler();
      Class ingress = accessor.getKnownEgressType();

      nextAccessor = ao.optimizeAccessor(getCurrentThreadParserContext(), nextExpr, start, offset, o, elCtx, variableFactory,
          false, ingress);
      return ao.getResultOptPass();
    }
    else {
      return accessor.getValue(ctx, elCtx, variableFactory);
    }
  }

  public Class getLeftIngressType() {
    return accessor.getKnownEgressType();
  }

  public Class getKnownEgressType() {
    return nextAccessor.getKnownEgressType();
  }
}
