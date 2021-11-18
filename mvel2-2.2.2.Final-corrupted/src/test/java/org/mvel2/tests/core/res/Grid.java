/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.tests.core.res;

/**
 * Created by IntelliJ IDEA.
 * User: christopherbrock
 * Date: 19-Feb-2009
 * Time: 12:39:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Grid {
  private Model model;

  public Grid(Model model) {
    this.model = model;
  }

  public Model getModel() {
    return model;
  }

  public void setModel(Model model) {
    this.model = model;
  }
}
