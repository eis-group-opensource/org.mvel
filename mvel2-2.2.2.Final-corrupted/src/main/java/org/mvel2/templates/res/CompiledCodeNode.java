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

package org.mvel2.templates.res;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.util.TemplateOutputStream;

import java.io.Serializable;

public class CompiledCodeNode extends Node {
  private Serializable ce;

  public CompiledCodeNode() {
  }

  public CompiledCodeNode(int begin, String name, char[] template, int start, int end, ParserContext context) {
    this.begin = begin;
    this.name = name;
    this.contents = template;
    this.cStart = start;
    this.cEnd = end - 1;
    this.end = end;
    ce = MVEL.compileExpression(template, cStart, cEnd - cStart, context);

//        ce = MVEL.compileExpression(
//                this.contents = subset(template, this.cStart = start, (this.end = this.cEnd = end) - start - 1),
//                   context);
  }

  public Object eval(TemplateRuntime runtime, TemplateOutputStream appender, Object ctx, VariableResolverFactory factory) {
    MVEL.executeExpression(ce, ctx, factory);
    return next != null ? next.eval(runtime, appender, ctx, factory) : null;
  }

  public boolean demarcate(Node terminatingNode, char[] template) {
    return false;
  }

  public String toString() {
    return "CodeNode:" + name + "{" + (contents == null ? "" : new String(contents)) + "} (start=" + begin + ";end=" + end + ")";
  }
}