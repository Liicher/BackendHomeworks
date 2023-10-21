package edu.hw2.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Expr {
    Logger LOGGER = LogManager.getLogger();
    double evaluate();

    record Constant(double expr) implements Expr {
        @Override
        public double evaluate() {
            LOGGER.info(this.toString());
            return expr;
        }
    }

    record Negate(Expr expr) implements Expr {
        public Negate(double exponent) {
            this(new Constant(exponent));
        }

        @Override
        public double evaluate() {
            if (expr == null) {
                throw new IllegalArgumentException();
            }
            LOGGER.info(this.toString());
            return -expr.evaluate();
        }
    }

    record Exponent(Expr expr, Expr exponent) implements Expr {
        public Exponent(Expr expr, double exponent) {
            this(expr, new Constant(exponent));
        }

        public Exponent(double expr, Expr exponent) {
            this(new Constant(expr), exponent);
        }

        public Exponent(double expr, double exponent) {
            this(new Constant(expr), new Constant(exponent));
        }

        @Override
        public double evaluate() {
            if (exponent == null || expr == null || exponent.evaluate() % 1 != 0) {
                throw new IllegalArgumentException();
            }
            LOGGER.info(this.toString());
            return Math.pow(expr.evaluate(), exponent.evaluate());
        }
    }

    record Addition(Expr exprFirst, Expr exprSecond) implements Expr {
        public Addition(Expr exprFirst, double exprSecond) {
            this(exprFirst, new Constant(exprSecond));
        }

        public Addition(double exprFirst, Expr exprSecond) {
            this(new Constant(exprFirst), exprSecond);
        }

        public Addition(double exprFirst, double exprSecond) {
            this(new Constant(exprFirst), new Constant(exprSecond));
        }

        @Override
        public double evaluate() {
            if (exprFirst == null || exprSecond == null) {
                throw new IllegalArgumentException();
            }
            LOGGER.info(this.toString());
            return exprFirst.evaluate() + exprSecond.evaluate();
        }
    }

    record Multiplication(Expr exprFirst, Expr exprSecond) implements Expr {
        public Multiplication(Expr exprFirst, double exprSecond) {
            this(exprFirst, new Constant(exprSecond));
        }

        public Multiplication(double exprFirst, Expr exprSecond) {
            this(new Constant(exprFirst), exprSecond);
        }

        public Multiplication(double exprFirst, double exprSecond) {
            this(new Constant(exprFirst), new Constant(exprSecond));
        }

        @Override
        public double evaluate() {
            if (exprFirst == null || exprSecond == null) {
                throw new IllegalArgumentException();
            }
            LOGGER.info(this.toString());
            return exprFirst.evaluate() * exprSecond.evaluate();
        }
    }
}



