package com.example.mnprojekt.methods.inter;

import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

public interface ODEStep {
    double step(double x, double t, double h, ODEEquation ode) throws UnparsableExpressionException, UnknownFunctionException;
}
