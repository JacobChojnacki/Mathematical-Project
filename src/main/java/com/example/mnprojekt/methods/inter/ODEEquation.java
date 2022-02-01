package com.example.mnprojekt.methods.inter;

import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

/**
 * Podstawowy interfejs do pisania rownan
 */
public interface ODEEquation {
    double f(double x, double t) throws UnparsableExpressionException, UnknownFunctionException;
}
