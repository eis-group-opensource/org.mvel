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

import org.mvel2.integration.VariableResolverFactory;

import java.io.Serializable;

public interface ExecutableStatement extends Accessor, Serializable, Cloneable {
  public Object getValue(Object staticContext, VariableResolverFactory factory);

  public void setKnownIngressType(Class type);

  public void setKnownEgressType(Class type);

  public Class getKnownIngressType();

  public Class getKnownEgressType();

  public boolean isExplicitCast();

  public boolean isConvertableIngressEgress();

  public void computeTypeConversionRule();

  public boolean intOptimized();

  public boolean isLiteralOnly();

  public boolean isEmptyStatement();
}
