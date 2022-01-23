package com.example.mnprojekt.methods;

import com.example.mnprojekt.methods.inter.StepHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class ConsolHandler implements StepHandler {
    private ArrayList<Double> xList = new ArrayList<>();
    private ArrayList<Double> tList = new ArrayList<>();

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
    public int getNumberOfPoints(){
        return xList.size();
    }

    @Override
    public void update(double x, double t) {
        xList.add(x);
        tList.add(t);
    }

    public void console() {
        System.out.println(Arrays.toString(xList.toArray()));
        System.out.println(Arrays.toString(tList.toArray()));
    }

    public void print2Columns() {

        for (int i = 0; i < xList.size(); i++) {
            System.out.println(tList.get(i) + "\t" + xList.get(i));
        }
    }
    private void clearData(){
        xList.clear();
        tList.clear();
    }
}
