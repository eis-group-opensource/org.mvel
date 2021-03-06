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
import org.mvel2.util.ParseTools;

import java.io.Serializable;

public class CompiledIfNode extends IfNode {

  private Serializable ce;

  public CompiledIfNode(int begin, String name, char[] template, int start, int end, ParserContext context) {
    super(begin, name, template, start, end);
    while (cEnd > cStart && ParseTools.isWhitespace(template[cEnd])) cEnd--;
    if (cStart != cEnd) {
      ce = MVEL.compileExpression(template, cStart, cEnd - start, context);
    }
  }

  public Object eval(TemplateRuntime runtime, TemplateOutputStream appender, Object ctx, VariableResolverFactory factory) {
    if (ce == null || MVEL.executeExpression(ce, ctx, factory, Boolean.class)) {
      return trueNode.eval(runtime, appender, ctx, factory);
    }
    return next != null ? next.eval(runtime, appender, ctx, factory) : null;
  }
}