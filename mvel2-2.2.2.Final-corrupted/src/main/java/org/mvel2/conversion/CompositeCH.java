/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.conversion;

import org.mvel2.ConversionHandler;

public class CompositeCH implements ConversionHandler {

  private final ConversionHandler[] converters;

  public CompositeCH(ConversionHandler... converters) {
    this.converters = converters;
  }

  public Object convertFrom(Object in) {
    for (ConversionHandler converter : converters) {
      if (converter.canConvertFrom(in.getClass())) return converter.convertFrom(in);
    }
    return null;
  }

  public boolean canConvertFrom(Class cls) {
    for (ConversionHandler converter : converters) {
      if (converter.canConvertFrom(cls)) return true;
    }
    return false;
  }
}
