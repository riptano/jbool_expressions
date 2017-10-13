package com.bpodgursky.jbool_expressions;

import java.util.List;

import com.google.common.base.Optional;
import org.apache.commons.lang.StringUtils;

public class And<K> extends NExpression<K> {
  public static final String EXPR_TYPE = "and";
  private Optional<String> cachedStringRepresentation = Optional.absent();


  private And(List<? extends Expression<K>> children) {
    super(children, "AND".hashCode());
  }

  @Override
  public NExpression<K> create(List<? extends Expression<K>> children) {
    return new And<K>(children);
  }

  public String toString() {
    if (!cachedStringRepresentation.isPresent()) {
      cachedStringRepresentation = Optional.of("(" + StringUtils.join(expressions, " & ") + ")");
    }
    return cachedStringRepresentation.get();
  }

  public static <K> And<K> of(Expression<K> child1, Expression<K> child2, Expression<K> child3) {
    return of(ExprUtil.<K>list(child1, child2, child3));
  }

  public static <K> And<K> of(Expression<K> child1, Expression<K> child2) {
    return of(ExprUtil.<K>list(child1, child2));
  }

  public static <K> And<K> of(Expression<K> child1) {
    return of(ExprUtil.<K>list(child1));
  }

  public static <K> And<K> of(List<? extends Expression<K>> children) {
    return new And<K>(children);
  }

  @Override
  public String getExprType() {
    return EXPR_TYPE;
  }

  @Override
  public boolean equals(Object maybeExpression)
  {
    if( ! (maybeExpression instanceof And) )
      return false;

    And otherAnd = (And) maybeExpression;

    if( otherAnd.getChildren().size() != expressions.length )
      return false;

    return this.hash == otherAnd.hash;
  }
}
