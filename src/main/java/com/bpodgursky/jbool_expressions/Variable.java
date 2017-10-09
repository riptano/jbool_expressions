package com.bpodgursky.jbool_expressions;

import java.util.List;

import com.bpodgursky.jbool_expressions.rules.Rule;

public class Variable<K> extends Expression<K> {
  public static final String EXPR_TYPE = "variable";

  private final K value;

  private Variable(K value){
    this.value = value;
    this.hash = value.hashCode();
  }

  public K getValue(){
    return value;
  }

  public String toString(){
    return value.toString();
  }

  @Override
  public Expression<K> apply(List<Rule<?, K>> rules) {
    return this;
  }

  public static <K> Variable<K> of(K value){
    return new Variable<K>(value);
  }

  @Override
  public String getExprType() {
    return EXPR_TYPE;
  }

  @Override
  public boolean equals(Object maybeVariable)
  {
    if( ! (maybeVariable instanceof Variable) )
      return false;

    return this.hash == ((Variable) maybeVariable).hash;
  }
}
