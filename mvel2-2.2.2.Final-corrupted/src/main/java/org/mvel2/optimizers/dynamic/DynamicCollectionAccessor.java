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

package org.mvel2.optimizers.dynamic;

import org.mvel2.ParserContext;
import org.mvel2.compiler.AbstractParser;
import org.mvel2.compiler.Accessor;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.optimizers.OptimizerFactory;

import static java.lang.System.currentTimeMillis;

public class DynamicCollectionAccessor implements DynamicAccessor {
  private Object rootObject;
  private Class colType;

  private char[] property;
  private int start;
  private int offset;

  private long stamp;
  private int type;

  private int runcount;

  private boolean opt = false;

  private Accessor _safeAccessor;
  private Accessor _accessor;

  public DynamicCollectionAccessor(Object rootObject, Class colType, char[] property, int start, int offset, int type, Accessor _accessor) {
    this.rootObject = rootObject;
    this.colType = colType;
    this._safeAccessor = this._accessor = _accessor;
    this.type = type;

    this.property = property;
    this.start = start;
    this.offset = offset;

    stamp = currentTimeMillis();
  }

  public Object getValue(Object ctx, Object elCtx, VariableResolverFactory variableFactory) {
    if (!opt) {
      if (++runcount > DynamicOptimizer.tenuringThreshold) {
        if ((currentTimeMillis() - stamp) < DynamicOptimizer.timeSpan) {
          opt = true;

          return optimize(AbstractParser.getCurrentThreadParserContext(), ctx, elCtx, variableFactory);
        }
        else {
          runcount = 0;
          stamp = currentTimeMillis();
        }
      }
    }

    return _accessor.getValue(ctx, elCtx, variableFactory);
  }

  public Object setValue(Object ctx, Object elCtx, VariableResolverFactory variableFactory, Object value) {
    runcount++;
    return _accessor.setValue(ctx, elCtx, variableFactory, value);
  }

  private Object optimize(ParserContext pCtx, Object ctx, Object elCtx, VariableResolverFactory variableResolverFactory) {

    if (DynamicOptimizer.isOverloaded()) {
      DynamicOptimizer.enforceTenureLimit();
    }

    _accessor = OptimizerFactory.getAccessorCompiler("ASM").optimizeCollection(pCtx, rootObject, colType,
        property, start, offset, ctx, elCtx, variableResolverFactory);
    return _accessor.getValue(ctx, elCtx, variableResolverFactory);
  }


  public void deoptimize() {
    this._accessor = this._safeAccessor;
    opt = false;
    runcount = 0;
    stamp = currentTimeMillis();
  }

  public long getStamp() {
    return stamp;
  }

  public int getRuncount() {
    return runcount;
  }

  public Class getKnownEgressType() {
    return colType;
  }
}