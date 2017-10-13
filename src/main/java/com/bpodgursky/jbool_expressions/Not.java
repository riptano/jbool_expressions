package com.bpodgursky.jbool_expressions;

import java.util.List;

import com.google.common.base.Optional;

import com.bpodgursky.jbool_expressions.rules.Rule;
import com.bpodgursky.jbool_expressions.rules.RuleSet;

public class Not<K> extends Expression<K> {
  public static final String EXPR_TYPE = "not";
  private Optional<String> cachedStringRepresentation = Optional.absent();

  private final Expression<K> e;

  private Not(Expression<K> e) {
    this.e = e;
    this.hash = e.hash * 13;
  }

  public Expression<K> getE() {
    return e;
  }

  public String toString() {
    if (!cachedStringRepresentation.isPresent()) {
      cachedStringRepresentation = Optional.of("!" + e);
    }
    return cachedStringRepresentation.get();
  }

  @Override
  public Expression<K> apply(List<Rule<?, K>> rules) {
    return new Not<K>(RuleSet.applyAll(e, rules));
  }

  public static <K> Not<K> of(Expression<K> e) {
    return new Not<K>(e);
  }

  @Override
  public String getExprType() {
    return EXPR_TYPE;
  }

  @Override
  public boolean equals(Object maybeNot)
  {
    if( ! (maybeNot instanceof Not) )
      return false;

    return this.hash == ((Not) maybeNot).hash;
  }
}
