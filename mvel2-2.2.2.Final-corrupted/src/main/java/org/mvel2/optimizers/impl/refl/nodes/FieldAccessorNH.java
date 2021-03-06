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

import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.PropertyHandler;
import org.mvel2.integration.VariableResolverFactory;

import java.lang.reflect.Field;

import static org.mvel2.DataConversion.convert;

public class FieldAccessorNH implements AccessorNode {
  private AccessorNode nextNode;
  private Field field;
  private boolean coercionRequired = false;
  private PropertyHandler nullHandler;

  public FieldAccessorNH(Field field, PropertyHandler handler) {
    this.field = field;
    this.nullHandler = handler;
  }

  public Object getValue(Object ctx, Object elCtx, VariableResolverFactory vars) {
    try {
      Object v = field.get(ctx);
      if (v == null) v = nullHandler.getProperty(field.getName(), elCtx, vars);


      if (nextNode != null) {
        return nextNode.getValue(v, elCtx, vars);
      }
      else {
        return v;
      }
    }
    catch (Exception e) {
      throw new RuntimeException("unable to access field", e);
    }
  }

  public Object setValue(Object ctx, Object elCtx, VariableResolverFactory variableFactory, Object value) {
    try {
      if (nextNode != null) {
        return nextNode.setValue(ctx, elCtx, variableFactory, value);
      }
      else if (coercionRequired) {
        field.set(ctx, value = convert(ctx, field.getClass()));
        return value;
      }
      else {
        field.set(ctx, value);
        return value;
      }
    }
    catch (IllegalArgumentException e) {
      if (!coercionRequired) {
        coercionRequired = true;
        return setValue(ctx, elCtx, variableFactory, value);
      }
      throw new RuntimeException("unable to bind property", e);
    }
    catch (Exception e) {
      throw new RuntimeException("unable to access field", e);
    }
  }

  public Field getField() {
    return field;
  }

  public void setField(Field field) {
    this.field = field;
  }

  public AccessorNode getNextNode() {
    return nextNode;
  }

  public AccessorNode setNextNode(AccessorNode nextNode) {
    return this.nextNode = nextNode;
  }

  public Class getKnownEgressType() {
    return field.getClass();
  }
}