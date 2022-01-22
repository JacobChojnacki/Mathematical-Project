package com.example.mnprojekt.methods.inter;

public interface ODEStep {
    double step(double x, double t, double h, ODEEquation ode);
}
