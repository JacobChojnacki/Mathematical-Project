package com.example.mnprojekt.methods;

import com.example.mnprojekt.methods.inter.ODEEquation;
import com.example.mnprojekt.methods.methodsChoice.Euler;
import com.example.mnprojekt.methods.save.SaveToFileHandler;

public class Main {
    public static void main(String[] args) {
        ODEEquation ode1 = (x, t) -> -2 * t * t * t + 12 * t * t - 20 * t + 8.5;

        ConsolHandler consolHandler = new ConsolHandler();

        ODEIntegrator integrator1 = new ODEIntegrator(0, 4, 1, ode1, new Euler(),
                consolHandler);

        ODEEquation ode2 = new ODEEquation() {
            @Override
            public double f(double x, double t) {
                return -2 * t * t * t + 12 * t * t - 20 * t + 8.5;
            }
        };

        integrator1.integrate(0.5);

        consolHandler.print2Columns();

        SaveToFileHandler save = new SaveToFileHandler();




//        consolHandler.console();
//        consolHandler.print2Columns();
//        System.out.println("*************************");
//        System.out.println(executionTime);
    }
}
