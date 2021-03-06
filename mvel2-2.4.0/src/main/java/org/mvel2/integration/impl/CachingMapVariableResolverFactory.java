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

package org.mvel2.integration.impl;

import org.mvel2.UnresolveablePropertyException;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked"})
public class CachingMapVariableResolverFactory extends BaseVariableResolverFactory {
  /**
   * Holds the instance of the variables.
   */
  protected Map<String, Object> variables;

  public CachingMapVariableResolverFactory(Map variables) {
    this.variables = variables;
  }

  public VariableResolver createVariable(String name, Object value) {
    VariableResolver vr;

    try {
      (vr = getVariableResolver(name)).setValue(value);
      return vr;
    }
    catch (UnresolveablePropertyException e) {
      addResolver(name, vr = new SimpleSTValueResolver(value, null, true));
      return vr;
    }
  }

  public VariableResolver createVariable(String name, Object value, Class<?> type) {
    VariableResolver vr;
    try {
      vr = getVariableResolver(name);
    }
    catch (UnresolveablePropertyException e) {
      vr = null;
    }

    if (vr != null && vr.getType() != null) {
      throw new RuntimeException("variable already defined within scope: " + vr.getType() + " " + name);
    }
    else {
      addResolver(name, vr = new SimpleSTValueResolver(value, type, true));
      return vr;
    }
  }

  public VariableResolver getVariableResolver(String name) {
    VariableResolver vr = variableResolvers.get(name);
    if (vr != null) {
      return vr;
    }
    else if (variables.containsKey(name)) {
      variableResolvers.put(name, vr = new SimpleSTValueResolver(variables.get(name), null));
      return vr;
    }
    else if (nextFactory != null) {
      return nextFactory.getVariableResolver(name);
    }

    throw new UnresolveablePropertyException("unable to resolve variable '" + name + "'");
  }


  public boolean isResolveable(String name) {
    return (variableResolvers.containsKey(name))
        || (variables != null && variables.containsKey(name))
        || (nextFactory != null && nextFactory.isResolveable(name));
  }

  protected VariableResolver addResolver(String name, VariableResolver vr) {
    variableResolvers.put(name, vr);
    return vr;
  }

  public void externalize() {
    for (Map.Entry<String, VariableResolver> entry : variableResolvers.entrySet()) {
      if (entry.getValue().getFlags() == -1) variables.put(entry.getKey(), entry.getValue().getValue());
    }
  }

  public boolean isTarget(String name) {
    return variableResolvers.containsKey(name);
  }

  public Set<String> getKnownVariables() {
    if (nextFactory == null) {
      if (variables != null) return new HashSet<String>(variables.keySet());
      return new HashSet<String>(0);
    }
    else {
      if (variables != null) return new HashSet<String>(variables.keySet());
      return new HashSet<String>(0);
    }
  }

  public void clear() {
    variableResolvers.clear();
    variables.clear();
  }
}
