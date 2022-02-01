package com.example.mnprojekt.methods;

import com.example.mnprojekt.methods.inter.ODEEquation;
import com.example.mnprojekt.methods.inter.ODEStep;
import com.example.mnprojekt.methods.inter.StepHandler;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

public class ODEIntegrator {
    private final double tLeft;
    private final double tRight;
    private final double x0;
    private final ODEEquation ode;
    private final ODEStep method;
    private final StepHandler stepHandler;

    public ODEIntegrator(double tLeft, double tRight, double x0, ODEEquation ode, ODEStep method,
                         StepHandler stepHandler) {
        this.tLeft = tLeft;
        this.tRight = tRight;
        this.ode = ode;
        this.method = method;
        this.x0 = x0;
        this.stepHandler = stepHandler;
    }

    public void integrate(double h) throws UnparsableExpressionException, UnknownFunctionException {
        double x = x0;
        double t;
        for (t = tLeft; t < tRight; t += h) {
            stepHandler.update(x, t);
            x = method.step(x, t, h, ode);
        }
        stepHandler.update(x, t);
    }
}
