package com.example.mnprojekt.methods.methodsChoice;

import com.example.mnprojekt.methods.inter.ODEEquation;
import com.example.mnprojekt.methods.inter.ODEStep;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

public class Euler implements ODEStep {
    @Override
    public double step(double x, double t, double h, ODEEquation ode) throws UnparsableExpressionException, UnknownFunctionException {
        return ode.f(x, t) * h + x;
    }
}
