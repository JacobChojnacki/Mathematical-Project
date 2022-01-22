package com.example.mnprojekt.methods;

import com.example.mnprojekt.methods.inter.ODEEquation;
import com.example.mnprojekt.methods.inter.ODEStep;
import com.example.mnprojekt.methods.inter.StepHandler;

public class ODEIntegrator {
    private double tLeft;
    private double tRight;
    private double x0;
    private ODEEquation ode;
    private ODEStep method;
    private StepHandler stepHandler;

    public ODEIntegrator(double tLeft, double tRight, double x0, ODEEquation ode, ODEStep method,
                         StepHandler stepHandler) {
        this.tLeft = tLeft;
        this.tRight = tRight;
        this.ode = ode;
        this.method = method;
        this.x0 = x0;
        this.stepHandler = stepHandler;
    }

    public void integrate(double h) {
        double x = x0;
        double t;
        for (t = tLeft; t < tRight; t += h) {
            stepHandler.update(x, t);
            x = method.step(x, t, h, ode);
        }
        stepHandler.update(x, t);
    }
}
