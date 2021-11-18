/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.ast;

import org.mvel2.ParserContext;

public abstract class BooleanNode extends ASTNode {
  protected ASTNode left;
  protected ASTNode right;

  protected BooleanNode(ParserContext pCtx) {
    super(pCtx);
  }

  public ASTNode getLeft() {
    return this.left;
  }

  public ASTNode getRight() {
    return this.right;
  }

  public void setLeft(ASTNode node) {
    this.left = node;
  }

  public void setRight(ASTNode node) {
    this.right = node;
  }

  public abstract void setRightMost(ASTNode right);

  public abstract ASTNode getRightMost();
}
