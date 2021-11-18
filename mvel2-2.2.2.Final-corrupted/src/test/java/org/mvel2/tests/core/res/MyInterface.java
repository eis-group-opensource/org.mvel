/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.tests.core.res;


public interface MyInterface {
  public enum MY_ENUM {
    ONE, TWO, THREE, FOUR
  }

  ;

  public boolean isType(MY_ENUM myenum);

  public void setType(MY_ENUM myenum, boolean flag);

  public static enum STATIC_ENUM {
    FOO, BAR;
  }

  public static interface MyInnerInterface {
    public static enum INNER_STATIC_ENUM {
      FOO, BAR;
    }
  }
}

