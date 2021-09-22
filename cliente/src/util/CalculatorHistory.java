package util;

import java.util.LinkedList;

public class CalculatorHistory {
    
    private LinkedList<String> operaciones;

    public CalculatorHistory() {
        operaciones = new LinkedList<>();
    }

    public void add(Expression exp, String result) {
        int number1 = (int) exp.number1;
        int number2 = (int) exp.number2;
        if(number1 == exp.number1) {
            if(number2 == exp.number2) {
                operaciones.add(
                    number1 + " " + exp.operator + " " + number2 + " = " + result
                );
            } else {
                operaciones.add(
                    number1 + " " + exp.operator + " " + exp.number2 + " = " + result
                );
            }
        } else {
            if(number2 == exp.number2) {
                operaciones.add(
                    exp.number1 + " " + exp.operator + " " + number2 + " = " + result
                );
            } else {
                operaciones.add(
                    exp.number1 + " " + exp.operator + " " + exp.number2 + " = " + result
                );
            }
        }
    }

    public String last() {
        if(operaciones.size() == 0) return "No hay operaciones";
        return operaciones.getLast();
    }
}
