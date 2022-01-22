package com.example.mnprojekt.methods.methodsChoice;

import com.example.mnprojekt.methods.inter.ODEEquation;
import com.example.mnprojekt.methods.inter.ODEStep;

public class Euler implements ODEStep {
    @Override
    public double step(double x, double t, double h, ODEEquation ode) {
        return ode.f(x, t) * h + x;
    }
}
