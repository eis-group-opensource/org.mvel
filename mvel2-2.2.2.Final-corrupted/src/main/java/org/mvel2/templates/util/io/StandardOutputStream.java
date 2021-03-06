/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.templates.util.io;

import org.mvel2.templates.util.TemplateOutputStream;

import java.io.IOException;
import java.io.OutputStream;

public class StandardOutputStream implements TemplateOutputStream {
  private OutputStream outputStream;

  public StandardOutputStream(OutputStream outputStream) {
    this.outputStream = outputStream;
  }

  public TemplateOutputStream append(CharSequence c) {
    try {
      for (int i = 0; i < c.length(); i++) {
        outputStream.write(c.charAt(i));
      }

      return this;
    }
    catch (IOException e) {
      throw new RuntimeException("failed to write to stream", e);
    }
  }

  public TemplateOutputStream append(char[] c) {
    try {

      for (char i : c) {
        outputStream.write(i);
      }
      return this;
    }
    catch (IOException e) {
      throw new RuntimeException("failed to write to stream", e);
    }
  }

  @Override
  public String toString() {
    return null;
  }
}
