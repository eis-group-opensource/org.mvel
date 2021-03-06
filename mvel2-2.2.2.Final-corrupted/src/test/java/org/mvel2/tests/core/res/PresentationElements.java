/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.tests.core.res;

import java.util.List;

public class PresentationElements {
  private List<String> names;

  public List<String> getNames() {
    return names;
  }

  public void setNames(List<String> names) {
    this.names = names;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((names == null) ? 0 : names.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (!(obj instanceof PresentationElements)) return false;
    PresentationElements other = (PresentationElements) obj;
    if (names == null) {
      if (other.names != null) return false;
    }
    else if (!names.equals(other.names)) return false;
    return true;
  }


}
