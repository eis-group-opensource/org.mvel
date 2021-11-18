/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.tests.core.res.res2;

public class Outer {

  public Inner getInner() {
    return new Inner();
  }

  public class Inner extends Object {

    public int getValue() {
      return 2;
    }
  }
}
