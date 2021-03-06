/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.optimizers.impl.refl.nodes;

import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.GlobalListenerFactory;
import org.mvel2.integration.VariableResolverFactory;


public class Notify implements AccessorNode {
  private String name;
  private AccessorNode nextNode;

  public Notify(String name) {
    this.name = name;
  }

  public Object getValue(Object ctx, Object elCtx, VariableResolverFactory vrf) {
    GlobalListenerFactory.notifyGetListeners(ctx, name, vrf);
    return nextNode.getValue(ctx, elCtx, vrf);
  }

  public Object setValue(Object ctx, Object elCtx, VariableResolverFactory variableFactory, Object value) {
    GlobalListenerFactory.notifySetListeners(ctx, name, variableFactory, value);
    return nextNode.setValue(ctx, elCtx, variableFactory, value);
  }

  public AccessorNode getNextNode() {
    return nextNode;
  }

  public AccessorNode setNextNode(AccessorNode nextNode) {
    return this.nextNode = nextNode;
  }

  public Class getKnownEgressType() {
    return Object.class;
  }
}
