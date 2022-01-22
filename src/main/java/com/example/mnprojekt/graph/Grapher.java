package com.example.mnprojekt.graph;

import javafx.scene.chart.XYChart;

import java.util.function.Function;

public class Grapher {

    private XYChart graph;
    private double range;

    public Grapher(XYChart graph, double range) {
        this.graph = graph;
        this.range = range;
    }

    public XYChart getGraph() {
        return graph;
    }

    public void setGraph(XYChart graph) {
        this.graph = graph;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public void makePlot(Function <Double,Double> function){
        XYChart.Series series = new XYChart.Series<>();
        for(double x=-range; x<=range; x+=0.1) plotPoint(x,function.apply(x),series);
        graph.getData().add(series);
    }

    private  void plotPoint(double  x, double y, XYChart.Series series){
        series.getData().add(new XYChart.Data(x,y));
    }

    public void clear(){
        graph.getData().clear();
    }
}
