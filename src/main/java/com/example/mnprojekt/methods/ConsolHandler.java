package com.example.mnprojekt.methods;

import com.example.mnprojekt.methods.inter.StepHandler;

import java.util.ArrayList;

public class ConsolHandler implements StepHandler {
    private final ArrayList<Double> xList = new ArrayList<>();
    private final ArrayList<Double> tList = new ArrayList<>();

    public double getXValue(int i){
        return xList.get(i);
    }

    public ArrayList<Double> getxList() {
        return xList;
    }

    public ArrayList<Double> gettList() {
        return tList;
    }

    public double getTValue(int i){
        return tList.get(i);
    }

    @Override
    public void update(double x, double t) {
        xList.add(x);
        tList.add(t);
    }

    public void clearData(){
        xList.clear();
        tList.clear();
    }
}
