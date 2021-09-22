package util;

import java.util.Stack;

public class CalculatorHistory {
    
    private Stack<String> operations;

    public CalculatorHistory() {
        operations = new Stack<>();
    }

    public void add(Expression exp, String result) {
        int number1 = (int) exp.number1;
        int number2 = (int) exp.number2;
        if(number1 == exp.number1) {
            if(number2 == exp.number2) {
                operations.push(
                    number1 + " " + exp.operator + " " + number2 + " = " + result
                );
            } else {
                operations.push(
                    number1 + " " + exp.operator + " " + exp.number2 + " = " + result
                );
            }
        } else {
            if(number2 == exp.number2) {
                operations.push(
                    exp.number1 + " " + exp.operator + " " + number2 + " = " + result
                );
            } else {
                operations.push(
                    exp.number1 + " " + exp.operator + " " + exp.number2 + " = " + result
                );
            }
        }
    }

    public String last() {
        if(operations.size() == 0) return "No hay operaciones";
        return operations.toString();
    }

    public String[] getList() {
        String[] operations = new String[this.operations.size()];
        int index = 0;
        for(Object operation : this.operations.toArray()) {
            operations[index] = (String) operation;
            index++;
        }
        return operations;
    }
}
