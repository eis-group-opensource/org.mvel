/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2;

/**
 * @author Mike Brock .
 */
public class ScriptRuntimeException extends RuntimeException {
  public ScriptRuntimeException() {
  }

  public ScriptRuntimeException(String message) {
    super(message);
  }

  public ScriptRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public ScriptRuntimeException(Throwable cause) {
    super(cause);
  }
}
