/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.tests.core.res;

import java.util.ArrayList;
import java.util.List;

public class DefaultKnowledgeHelper implements KnowledgeHelper {

  private WorkingMemory workingMemory;

  public DefaultKnowledgeHelper() {

  }

  public DefaultKnowledgeHelper(WorkingMemory workingMemory) {
    this.workingMemory = workingMemory;
  }

  public WorkingMemory getWorkingMemory() {
    return this.workingMemory;
  }

  public List retracted = new ArrayList();

  public void insert(Object object) {
  }

  public void retract(Object object) {
    retracted.add(object);
  }

  public void retract(FactHandle handle) {
    retracted.add(handle);
  }

}
