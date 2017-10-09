package com.bpodgursky.jbool_expressions;

import java.util.List;

import com.bpodgursky.jbool_expressions.rules.Rule;

public abstract class Expression<K> implements Comparable<Expression> {

  public long hash;

  public int compareTo(Expression o) {
    return Long.compare(this.hash, o.hash);
  }

  @Override
  public boolean equals(Object o){
    return o instanceof Expression &&
           this.hash == ((Expression) o).hash;
  }

  @Override
  public int hashCode() {
    return (int) this.hash;
  }

  public abstract Expression<K> apply(List<Rule<?, K>> rules);

  public abstract String getExprType();
}
