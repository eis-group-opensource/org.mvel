/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
/**
 * MVEL 2.0
 * Copyright (C) 2007 The Codehaus
 * Mike Brock, Dhanji Prasanna, John Graham, Mark Proctor
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
 */

package org.mvel2.compiler;

import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.optimizers.OptimizerFactory;

import java.io.Serializable;

import static org.mvel2.optimizers.OptimizerFactory.getThreadAccessorOptimizer;

public class CompiledAccExpression implements ExecutableStatement, Serializable {
  private char[] expression;
  private int start;
  private int offset;

  private transient Accessor accessor;
  private ParserContext context;
  private Class ingressType;

  public CompiledAccExpression(char[] expression, Class ingressType, ParserContext context) {
    this(expression, 0, expression.length, ingressType, context);
  }

  public CompiledAccExpression(char[] expression, int start, int offset, Class ingressType, ParserContext context) {
    this.expression = expression;
    this.start = start;
    this.offset = offset;

    this.context = context;
    this.ingressType = ingressType != null ? ingressType : Object.class;
  }

  public Object setValue(Object ctx, Object elCtx, VariableResolverFactory vrf, Object value) {
    if (accessor == null) {
      if (ingressType == Object.class && value != null) ingressType = value.getClass();
      accessor = getThreadAccessorOptimizer()
          .optimizeSetAccessor(context, expression, 0, expression.length, ctx, ctx, vrf, false, value, ingressType);

    }
    else {
      accessor.setValue(ctx, elCtx, vrf, value);
    }
    return value;
  }

  public Object getValue(Object staticContext, VariableResolverFactory factory) {
    if (accessor == null) {
      try {
        accessor = getThreadAccessorOptimizer()
            .optimizeAccessor(context, expression, 0, expression.length, staticContext, staticContext, factory, false, ingressType);
        return getValue(staticContext, factory);
      }
      finally {
        OptimizerFactory.clearThreadAccessorOptimizer();
      }
    }
    return accessor.getValue(staticContext, staticContext, factory);
  }

  public void setKnownIngressType(Class type) {
    this.ingressType = type;
  }

  public void setKnownEgressType(Class type) {

  }

  public Class getKnownIngressType() {
    return ingressType;
  }

  public Class getKnownEgressType() {
    return null;
  }

  public boolean isConvertableIngressEgress() {
    return false;
  }

  public void computeTypeConversionRule() {
  }

  public boolean intOptimized() {
    return false;
  }

  public boolean isLiteralOnly() {
    return false;
  }

  public Object getValue(Object ctx, Object elCtx, VariableResolverFactory variableFactory) {
    if (accessor == null) {
      try {
        accessor = getThreadAccessorOptimizer().optimizeAccessor(context, expression, start, offset, ctx, elCtx,
            variableFactory, false, ingressType);
        return getValue(ctx, elCtx, variableFactory);
      }
      finally {
        OptimizerFactory.clearThreadAccessorOptimizer();
      }
    }
    return accessor.getValue(ctx, elCtx, variableFactory);
  }

  public Accessor getAccessor() {
    return accessor;
  }

  public boolean isEmptyStatement() {
    return accessor == null;
  }

  public boolean isExplicitCast() {
    return false;
  }
}
