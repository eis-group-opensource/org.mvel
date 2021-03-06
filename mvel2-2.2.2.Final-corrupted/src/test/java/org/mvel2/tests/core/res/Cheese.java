/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.tests.core.res;

import java.io.Serializable;
import java.util.Date;

/*
 * Copyright 2005 JBoss Inc
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class Cheese
    implements
    Serializable {

  public static final String STILTON = "stilton";

  /**
   *
   */
  private static final long serialVersionUID = -1187540653710115339L;
  private String type;
  private int price;
  private Date useBy;

  public Cheese() {

  }

  public Cheese(final String type,
                final int price) {
    super();
    this.type = type;
    this.price = price;
  }

  public int getPrice() {
    return this.price;
  }

  public String getType() {
    return this.type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public void setPrice(final int price) {
    this.price = price;
  }

  public Date getUseBy() {
    return useBy;
  }

  public void setUseBy(Date useBy) {
    this.useBy = useBy;
  }

  public String toString() {
    return "Cheese( type='" + this.type + "', price=" + this.price + " )";
  }

  public int hashCode() {
    final int PRIME = 31;
    int result = 1;
    result = PRIME * result + price;
    result = PRIME * result + ((type == null) ? 0 : type.hashCode());
    return result;
  }

  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    final Cheese other = (Cheese) obj;
    if (price != other.price) return false;
    if (type == null) {
      if (other.type != null) return false;
    }
    else if (!type.equals(other.type)) return false;
    return true;
  }


}