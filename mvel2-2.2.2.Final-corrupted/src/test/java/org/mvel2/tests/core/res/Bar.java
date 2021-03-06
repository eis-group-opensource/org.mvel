/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.tests.core.res;

import java.util.ArrayList;
import java.util.List;

public class Bar {
  private String name = "dog";
  private boolean woof = true;
  private int age = 14;
  private String assignTest = "";
  private List<Integer> testList = new ArrayList<Integer>();
  private Integer[] intarray = new Integer[1];

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isWoof() {
    return woof;
  }

  public void setWoof(boolean woof) {
    this.woof = woof;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public boolean isFoo(Object obj) {
    return obj instanceof Foo;
  }

  public String getAssignTest() {
    return assignTest;
  }

  public void setAssignTest(String assignTest) {
    this.assignTest = assignTest;
  }


  public List<Integer> getTestList() {
    return testList;
  }

  public void setTestList(List<Integer> testList) {
    this.testList = testList;
  }

  public String happy() {
    return "happyBar";
  }

  public static int staticMethod() {
    return 1;
  }

  public Integer[] getIntarray() {
    return intarray;
  }

  public void setIntarray(Integer[] intarray) {
    this.intarray = intarray;
  }

  public boolean equals(Object o) {
    return o instanceof Bar;
  }
}
