/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.tests.core.res;


public enum MyEnum {
  ALTERNATIVE("Alternative"),
  FULL_DOCUMENTATION("FullDocumentation");

  private final String value;

  MyEnum(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static MyEnum fromValue(String v) {
    for (MyEnum c : MyEnum.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }
}
