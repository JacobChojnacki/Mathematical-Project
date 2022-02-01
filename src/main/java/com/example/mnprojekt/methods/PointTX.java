package com.example.mnprojekt.methods;

import java.util.ArrayList;

public class PointTX {
    private final double time;
    private double x;

    public PointTX(double time, double x) {
        this.time = time;
        this.x = x;
    }

    public double getTime() {
        return time;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public static ArrayList<PointTX> getPointsTX(ArrayList<Double> t, ArrayList<Double> x){
        ArrayList<PointTX> points = new ArrayList<>();
        for (int i = 0; i < t.size(); i++)
            points.add(new PointTX(t.get(i), x.get(i)));
        return points;
    }
}
