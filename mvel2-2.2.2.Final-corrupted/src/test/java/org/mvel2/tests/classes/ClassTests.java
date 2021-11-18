/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.tests.classes;

import junit.framework.TestCase;
import org.mvel2.MVEL;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Mike Brock
 */
public class ClassTests extends TestCase {
  private final String dir = "src/test/java/" + getClass().getPackage().getName().replaceAll("\\.", "/");

  public void testScript() throws IOException {
    final Object o = MVEL.evalFile(new File(dir + "/demo.mvel"), new HashMap<String, Object>());
  }

}
