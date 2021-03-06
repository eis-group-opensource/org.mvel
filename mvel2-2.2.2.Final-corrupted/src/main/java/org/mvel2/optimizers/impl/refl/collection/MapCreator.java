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
package org.mvel2.optimizers.impl.refl.collection;

import org.mvel2.compiler.Accessor;
import org.mvel2.integration.VariableResolverFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Christopher Brock
 */
public class MapCreator implements Accessor {
  private Accessor[] keys;
  private Accessor[] vals;
  private int size;

  public Object getValue(Object ctx, Object elCtx, VariableResolverFactory variableFactory) {
    Map map = new HashMap(size * 2);
    for (int i = size - 1; i != -1; i--) {
      //noinspection unchecked
      map.put(keys[i].getValue(ctx, elCtx, variableFactory), vals[i].getValue(ctx, elCtx, variableFactory));
    }
    return map;
  }

  public MapCreator(Accessor[] keys, Accessor[] vals) {
    this.size = (this.keys = keys).length;
    this.vals = vals;
  }

  public Object setValue(Object ctx, Object elCtx, VariableResolverFactory variableFactory, Object value) {
    // not implemented
    return null;
  }

  public Class getKnownEgressType() {
    return Map.class;
  }
}
