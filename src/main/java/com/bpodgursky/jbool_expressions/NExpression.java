package com.bpodgursky.jbool_expressions;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

import com.bpodgursky.jbool_expressions.rules.Rule;
import com.bpodgursky.jbool_expressions.rules.RuleSet;

public abstract class NExpression<K> extends Expression<K>{

  public final Expression<K>[] expressions;

  protected NExpression(List<? extends Expression<K>> expressions, Object hashBase){
    if(expressions.isEmpty()){
      throw new IllegalArgumentException("Arguments length 0!");
    }

    this.expressions = expressions.toArray(ExprUtil.<K>expr(0));

    Arrays.sort(this.expressions);

    this.hash = hashBase.hashCode();
    for(Expression e: this.expressions)
    {
      this.hash = this.hash * 31;
      this.hash = this.hash + e.hash;
    }
  }

  @Override
  public Expression<K> apply(List<Rule<?, K>> rules) {
    List<Expression<K>> childCopy = Lists.newArrayList();
    for(Expression<K> expr: expressions){
      childCopy.add(RuleSet.applyAll(expr, rules));
    }
    return create(childCopy);
  }

  public List<Expression<K>> getChildren(){
    return ExprUtil.list(expressions);
  }

  public abstract NExpression<K> create(List<? extends Expression<K>> children);
}
