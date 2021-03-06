/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.tests.core.res;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Base {
  public String data = "cat";
  public String number = "101";
  public List<String> list;
  public String[] array;
  public List<Thing> things;
  public Boolean fun = false;
  public String sentence = "The quick brown fox jumps over the lazy dog!";
  public Foo foo = new Foo();

  public boolean ackbar = false;

  public Map funMap = new HashMap();
  public Map<String, Foo> fooMap = new HashMap<String, Foo>();

  public String barfoo;

  public String defnull = null;

  public int sarahl;

  public Object[] testArray = new Object[]{new Foo(), new Bar()};

  public String[] stringArray = new String[]{"hello", "there", "how", "are", "you"};
  public int[] intArray = new int[]{5, 3, 2, 1, 0};


  public Base() {
    this.list = new ArrayList<String>();
    list.add("Happy");
    list.add("Happy!");
    list.add("Joy");
    list.add("Joy!");

    this.array = (String[]) this.list.toArray(new String[this.list.size()]);

    things = new ArrayList<Thing>();
    things.add(new Thing("Bob"));
    things.add(new Thing("Smith"));
    things.add(new Thing("Cow"));


    funMap.put("foo", new Foo());
    funMap.put("foo_bar", new Foo());

    fooMap.put("foo", foo);

  }


  public Foo getFoo() {
    return foo;
  }

  public boolean equalityCheck(Object a, Object b) {
    return a.equals(b);
  }

  public void populate() {
    barfoo = "sarah";
  }

  public String funMethod(String[] array) {
    return array[0];
  }

  public int sum(int[] nums) {
    int sum = 0;
    for (int i : nums) sum += i;
    return sum;
  }

  public String readBack(String test) {
    return test;
  }

  public String appendTwoStrings(String a, String b) {
    return a + b;
  }

  public String getDATA() {
    return data;
  }

  public boolean ord(boolean val, int num) {
    System.out.println("num: " + num);
    return val;
  }

  public boolean equals(Object o) {
    return o instanceof Base;
  }

  public Map<String, Foo> getFooMap() {
    return fooMap;
  }

  public int getIntValue() {
    return 10;
  }

  public void setExplanation(String data) {
    this.data = data;
  }

  public String getExplanation() {
    return this.data;
  }
}
