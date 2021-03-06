/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
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

package org.mvel2.templates.util;

import org.mvel2.templates.TemplateError;
import org.mvel2.templates.res.Node;
import org.mvel2.templates.res.TerminalNode;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static java.nio.ByteBuffer.allocateDirect;
import static org.mvel2.util.ParseTools.balancedCapture;

public class TemplateTools {
  public static Node getLastNode(Node node) {
    Node n = node;
    while (true) {
      if (n.getNext() instanceof TerminalNode) return n;
      n = n.getNext();
    }
  }

  public static int captureToEOS(char[] expression, int cursor) {
    int length = expression.length;
    while (cursor != length) {
      switch (expression[cursor]) {
        case '(':
        case '[':
        case '{':
          cursor = balancedCapture(expression, cursor, expression[cursor]);
          break;

        case ';':
        case '}':
          return cursor;

      }
      cursor++;
    }

    return cursor;
  }

  public static String readInFile(String file) {
    return readInFile(new File(file));
  }

  public static String readInFile(File file) {
    try {
      FileChannel fc = new FileInputStream(file).getChannel();
      ByteBuffer buf = allocateDirect(10);
      StringBuilder appender = new StringBuilder();
      int read;

      while (true) {
        buf.rewind();
        if ((read = fc.read(buf)) != -1) {
          buf.rewind();
          for (; read != 0; read--) {
            appender.append((char) buf.get());
          }
        }
        else {
          break;
        }
      }

      fc.close();

      return appender.toString();
    }
    catch (FileNotFoundException e) {
      throw new TemplateError("cannot include template '" + file.getName() + "': file not found.");
    }
    catch (IOException e) {
      throw new TemplateError("unknown I/O exception while including '" + file.getName() + "' (stacktrace nested)", e);
    }
  }

  public static String readStream(InputStream instream) {
    try {
      byte[] buf = new byte[10];
      StringBuilder appender = new StringBuilder();
      int read;
      while ((read = instream.read(buf)) != -1) {
        for (int i = 0; i < read; i++) {
          appender.append((char) buf[i]);
        }
      }

      return appender.toString();
    }
    catch (NullPointerException e) {
      if (instream == null) {
        throw new TemplateError("null input stream", e);
      }
      else {
        throw e;
      }
    }
    catch (IOException e) {
      throw new TemplateError("unknown I/O exception while including (stacktrace nested)", e);
    }
  }
}
