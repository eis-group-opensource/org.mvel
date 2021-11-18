/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.util;

import org.mvel2.integration.VariableResolverFactory;

public interface CallableProxy {
  public Object call(Object ctx, Object thisCtx, VariableResolverFactory factory, Object[] parameters);
}
