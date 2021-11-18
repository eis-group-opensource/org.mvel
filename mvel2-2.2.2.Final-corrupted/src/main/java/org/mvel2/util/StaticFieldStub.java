/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.util;

import org.mvel2.integration.VariableResolverFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author Mike Brock <cbrock@redhat.com>
 */
public class StaticFieldStub implements StaticStub {
  private final Field field;
  private final Object cachedValue;

  public StaticFieldStub(Field field) {
    this.field = field;

    if (!field.isAccessible() || (field.getModifiers() & Modifier.STATIC) == 0) {
      throw new RuntimeException("not an accessible static field: " + field.getDeclaringClass().getName()
          + "." + field.getName());
    }

    try {
      cachedValue = field.get(null);
    }
    catch (IllegalAccessException e) {
      throw new RuntimeException("error accessing static field", e);
    }
  }

  public Object call(Object ctx, Object thisCtx, VariableResolverFactory factory, Object[] parameters) {
    return cachedValue;
  }
}
