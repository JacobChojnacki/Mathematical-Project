package com.example.mnprojekt.methods;

import com.example.mnprojekt.methods.inter.ODEEquation;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

public class CalculateHandler implements ODEEquation {
    private final ExpressionBuilder expressionBuilder;
    private char variable;

    public CalculateHandler(String equation) {
        int i = 0;
        while (i < equation.length()){
            if((equation.charAt(i) >= 60  && equation.charAt(i)<=95)
                    || ((equation.charAt(i) >= 100 && equation.charAt(i) <= 130))){
                variable = equation.charAt(i);
                break;}
            i++;
        }
        expressionBuilder = new ExpressionBuilder(equation);
    }


    @Override
    public double f(double x, double t) throws UnparsableExpressionException, UnknownFunctionException {
        Calculable calculate = expressionBuilder.withVariable(String.valueOf(variable),t)
                .build();
        return calculate.calculate();
    }
}
