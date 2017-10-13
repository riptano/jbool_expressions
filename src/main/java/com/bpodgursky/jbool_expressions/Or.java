package com.bpodgursky.jbool_expressions;

import java.util.List;

import com.google.common.base.Optional;
import org.apache.commons.lang.StringUtils;

public class Or<K> extends NExpression<K> {
  public static final String EXPR_TYPE = "or";
  private Optional<String> cachedStringRepresentation = Optional.absent();

  private Or(List<? extends Expression<K>> children) {
    super(children, "OR".hashCode());
  }

  @Override
  public NExpression<K> create(List<? extends Expression<K>> children) {
    return new Or<K>(children);
  }

  public String toString() {
    if (!cachedStringRepresentation.isPresent()) {
      cachedStringRepresentation = Optional.of("(" + StringUtils.join(expressions, " | ") + ")");
    }
    return cachedStringRepresentation.get();
  }

  public static <K> Or<K> of(Expression<K> child1, Expression<K> child2, Expression<K> child3, Expression<K> child4) {
    return of(ExprUtil.<K>list(child1, child2, child3, child4));
  }

  public static <K> Or<K> of(Expression<K> child1, Expression<K> child2, Expression<K> child3) {
    return of(ExprUtil.<K>list(child1, child2, child3));
  }

  public static <K> Or<K> of(Expression<K> child1, Expression<K> child2) {
    return of(ExprUtil.<K>list(child1, child2));
  }

  public static <K> Or<K> of(List<? extends Expression<K>> children) {
    return new Or<K>(children);
  }

  public static <K> Or<K> of(Expression<K> child1) {
    return of(ExprUtil.<K>list(child1));
  }

  @Override
  public String getExprType() {
    return EXPR_TYPE;
  }

  @Override
  public boolean equals(Object maybeExpression)
  {
    if( ! (maybeExpression instanceof Or) )
      return false;

    Or otherOr = (Or) maybeExpression;

    if( otherOr.getChildren().size() != expressions.length )
      return false;

    return this.hash == otherOr.hash;
  }
}
