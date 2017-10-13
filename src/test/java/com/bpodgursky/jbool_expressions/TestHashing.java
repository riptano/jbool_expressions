package com.bpodgursky.jbool_expressions;

public class TestHashing extends JBoolTestCase
{
    public void testHashing() {
        Variable<String> A = Variable.of("A");
        Variable<String> B = Variable.of("B");

        Variable<Integer> five = Variable.of(5);
        Variable<Integer> two = Variable.of(2);
        Variable<Integer> four = Variable.of(4);
        Variable<Integer> three = Variable.of(3);

        And<Integer> fourAndThree = And.of(four, three);
        And<Integer> fiveAndTwo = And.of(five, two);

        Not<String> notA = Not.of(A);

        And<String> aAndB = And.of(A, B);
        And<String> bAndA = And.of(B, A);

        Or<String> aOrB = Or.of(A, B);
        Or<String> bOrA = Or.of(B, A);

        assertEquals(aAndB, bAndA);
        assertEquals(aOrB, bOrA);

        assertFalse(aOrB.equals(aAndB));
        assertFalse(A.equals(notA));

        assertFalse(fiveAndTwo.equals(fourAndThree));
        assertFalse(Not.of(Not.of(Not.of(notA))).equals(Not.of(notA)));
        assertFalse(Not.of(Not.of(notA)).equals(Not.of(notA)));
    }
}
