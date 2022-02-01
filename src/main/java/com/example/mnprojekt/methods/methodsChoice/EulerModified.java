package com.example.mnprojekt.methods.methodsChoice;

import com.example.mnprojekt.methods.inter.ODEEquation;
import com.example.mnprojekt.methods.inter.ODEStep;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

public class EulerModified implements ODEStep {
    @Override
    public double step(double x, double t, double h, ODEEquation ode) throws UnparsableExpressionException, UnknownFunctionException {
            double xMid = x + ode.f(t,x)*h/2;
            return ode.f(xMid,t+h/2) * h + x;
        }
}
