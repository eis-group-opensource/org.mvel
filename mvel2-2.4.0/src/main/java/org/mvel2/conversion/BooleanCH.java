/**
 * MVEL 2.0
 * Copyright (C) 2007 The Codehaus
 * Mike Brock, Dhanji Prasanna, John Graham, Mark Proctor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mvel2.conversion;

import org.mvel2.ConversionException;
import org.mvel2.ConversionHandler;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.valueOf;

public class BooleanCH implements ConversionHandler {
  private static final Map<Class, Converter> CNV =
      new HashMap<Class, Converter>();

  private static Converter stringConverter = new Converter() {
    public Object convert(Object o) {
      return !(((String) o).equalsIgnoreCase("false")
          || (((String) o).equalsIgnoreCase("no"))
          || (((String) o).equalsIgnoreCase("off"))
          || ("0".equals(o))
          || ("".equals(o)));
    }
  };

  public Object convertFrom(Object in) {
    if (!CNV.containsKey(in.getClass())) throw new ConversionException("cannot convert type: "
        + in.getClass().getName() + " to: " + Boolean.class.getName());
    return CNV.get(in.getClass()).convert(in);
  }


  public boolean canConvertFrom(Class cls) {
    return CNV.containsKey(cls);
  }

  static {
    CNV.put(String.class,
        stringConverter
    );

    CNV.put(Object.class,
        new Converter() {
          public Object convert(Object o) {
            return stringConverter.convert(valueOf(o));
          }
        }
    );

    CNV.put(Boolean.class,
        new Converter() {
          public Object convert(Object o) {
            return o;
          }
        }
    );

    CNV.put(Integer.class,
        new Converter() {
          public Boolean convert(Object o) {
            return (((Integer) o) > 0);
          }
        }
    );

    CNV.put(Float.class,
        new Converter() {
          public Boolean convert(Object o) {
            return (((Float) o) > 0);
          }
        }
    );

    CNV.put(Double.class,
        new Converter() {
          public Boolean convert(Object o) {
            return (((Double) o) > 0);
          }
        }
    );

    CNV.put(Short.class,
        new Converter() {
          public Boolean convert(Object o) {
            return (((Short) o) > 0);
          }
        }
    );

    CNV.put(Long.class,
        new Converter() {
          public Boolean convert(Object o) {
            return (((Long) o) > 0);
          }
        }
    );

    CNV.put(boolean.class,
        new Converter() {

          public Boolean convert(Object o) {
            return Boolean.valueOf((Boolean) o);
          }
        }
    );

    CNV.put(BigDecimal.class,
        new Converter() {

          public Boolean convert(Object o) {
            return Boolean.valueOf(((BigDecimal) o).doubleValue() > 0);
          }
        }
    );

  }
}
