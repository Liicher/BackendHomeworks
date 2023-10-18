package edu.hw2.task1;

import edu.hw2.task3.ConnectionException;
import edu.hw2.task3.FaultyConnectionManager;
import edu.hw2.task3.PopularCommandExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static edu.hw2.task1.Expr.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExprTest {

    @Test
    @DisplayName("Task example")
    void example() {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));

        assertThat(res.evaluate()).isEqualTo(37);
    }

    @Test
    @DisplayName("Invalid inputs")
    void invalidInputExponent() {
        assertThrows(IllegalArgumentException.class, () -> {new Exponent(null, 3).evaluate();});
        assertThrows(IllegalArgumentException.class, () -> {new Exponent(3, null).evaluate();});
        assertThrows(IllegalArgumentException.class, () -> {new Exponent(3, 1.2).evaluate();});

        assertThrows(IllegalArgumentException.class, () -> {new Negate(null).evaluate();});

        assertThrows(IllegalArgumentException.class, () -> {new Addition(null, 1.2).evaluate();});
        assertThrows(IllegalArgumentException.class, () -> {new Addition(1, null).evaluate();});

        assertThrows(IllegalArgumentException.class, () -> {new Multiplication(null, 1.2).evaluate();});
        assertThrows(IllegalArgumentException.class, () -> {new Multiplication(1, null).evaluate();});
    }
}
