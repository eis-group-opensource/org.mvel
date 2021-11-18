/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.tests.core.res;

public class PojoStatic {
  private String value;

  public PojoStatic(String value) {
    this.value = value;
  }

  public PojoStatic() {
  }

  public String getValue() {
    return value;
  }

  public void setValue(String string) {
    this.value = string;
  }
}
