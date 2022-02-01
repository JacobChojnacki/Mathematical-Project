package com.example.mnprojekt.methods.methodsChoice;

import com.example.mnprojekt.methods.inter.ODEEquation;
import com.example.mnprojekt.methods.inter.ODEStep;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

public class RungegoKutty implements ODEStep {
    @Override
    public double step(double x, double t, double h, ODEEquation ode) throws UnparsableExpressionException, UnknownFunctionException {
        double k1 = ode.f(x,t);
        double k2 = ode.f(x+0.5*h,t+0.5*k1*h);
        double k3 = ode.f(x+0.5*h,t+0.5*k2*h);
        double k4 = ode.f(x+h,t+k3*h);
        return t+(h/6.0)*(k1+2*k2+2*k3+k4);
    }
}
