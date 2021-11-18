/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.tests.core.res;

import java.util.Calendar;
import java.util.Date;

public class PDFFieldUtil {
  public int calculateAge(Date gebDat) {
    long milliAge = System.currentTimeMillis() - gebDat.getTime();
    Calendar geburtsTag = Calendar.getInstance();
    geburtsTag.setTimeInMillis(milliAge);
    return geburtsTag.get(Calendar.YEAR) - 1970;
  }
}
