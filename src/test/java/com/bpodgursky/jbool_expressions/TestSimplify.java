package com.bpodgursky.jbool_expressions;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import com.bpodgursky.jbool_expressions.rules.Rule;
import com.bpodgursky.jbool_expressions.rules.SimplifyNExprChildren;

public class TestSimplify extends JBoolTestCase {

  public void testSimplify() {

    //  A | (A & B)  = A
    assertSimplify("A", "A | (A & B)");

    //  A & (A | B) = A
    assertSimplify("A", "A & (A | B)");

    //  other more nested cases
    assertSimplify("A", "A & (A | B | C)");
    assertSimplify("(A & C)", "(A & C) | ((A & C) & B & D)");
    assertSimplify("(A & C)", "A & C | (A & C & B & D)");
    assertSimplify("(A | C)", "(A | C) & ((A | C) | B | D)");
    assertSimplify("(A | C)", "(A | C) & (A | C | B | D)");
    assertSimplify("!A", "(!A) & ((!A) | B | D)");

    //  make sure it doesn't catch the opposite and/or case for whatever reason
    assertSimplify("((A & C) | (B & D & (A | C)))", "((A | C) & B & D) | (A & C)");


    // test in isolation to catch a few potential errors
    ArrayList<Rule<?, String>> rules = Lists.<Rule<?, String>>newArrayList(new SimplifyNExprChildren<String>());

    assertApply("(A & (A & B))", "(A & B) & A", rules);
    assertApply("(A)", "A & (A | B)", rules);
    assertApply("(A)", "A | (A & B)", rules);
    assertApply("(A | (A | B))", "(A | B) | A", rules);

    // test CollapseNegation rules
    assertSimplify("(A | C | D)", "A | (!A & C) | D");
    assertSimplify("(C | D | (A & E))", "(A & E) | (!(A & E) & C) | D");
  }

  public void testPOS(){
    assertToPos("(A & (!C | !D | !E | !F))","A & (!A | !C | !D | !E | !F)");
    assertToSop("(D | !A)", "!((!D | !A) & A)");
    assertToSop("(A | !D)", "((!D & !A) | A)");
  }
}
