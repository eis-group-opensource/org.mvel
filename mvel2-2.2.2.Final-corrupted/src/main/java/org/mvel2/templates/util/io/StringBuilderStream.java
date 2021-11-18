/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.templates.util.io;

import org.mvel2.templates.util.TemplateOutputStream;

public class StringBuilderStream implements TemplateOutputStream {
  private StringBuilder appender;

  public StringBuilderStream(StringBuilder appender) {
    this.appender = appender;
  }

  public TemplateOutputStream append(CharSequence c) {
    appender.append(c);
    return this;
  }

  public TemplateOutputStream append(char[] c) {
    appender.append(c);
    return this;
  }

  @Override
  public String toString() {
    return appender.toString();
  }
}
