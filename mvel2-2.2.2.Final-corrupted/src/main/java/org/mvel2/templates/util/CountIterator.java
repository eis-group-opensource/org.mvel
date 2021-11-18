/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.templates.util;

import java.util.Iterator;

/**
 * User: christopherbrock
 * Date: 10-Aug-2010
 * Time: 6:42:20 PM
 */
public class CountIterator implements Iterator {
  int cursor;
  int countTo;

  public CountIterator(int countTo) {
    this.countTo = countTo;
  }

  public boolean hasNext() {
    return cursor < countTo;
  }

  public Object next() {
    return cursor++;
  }

  public void remove() {
  }
}
