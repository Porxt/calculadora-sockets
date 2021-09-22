package util;

import java.util.HashMap;

public class Expression {

    public final String operation;
    public final double number1;
    public final double number2;
    public final String operator;

    public Expression(String operation, String operator, double number1, double number2) {
        this.operation = operation;
        this.operator = operator;
        this.number1 = number1;
        this.number2 = number2;
    }

    public static Expression build(String expression) {
        int index = -1;
        boolean isPow = false;
        String symbol = "";
        HashMap<String, String> operationName = new HashMap<>();
        operationName.put("**", "pow");
        operationName.put("%", "mod");
        operationName.put("+", "sum");
        operationName.put("-", "sub");
        operationName.put("*", "mul");
        operationName.put("/", "div");
        if(expression.matches("^\\d+(\\.\\d+)?([+\\-*/%]|\\*\\*)\\d+(\\.\\d+)?$")) {
            for(String operator : operationName.keySet()) {
                index = expression.indexOf(operator);
                if(index != -1) {
                    symbol = operator;
                    if(operator.equals("**")) {
                        isPow = true;
                    }
                    break;
                }
            }
        } else return null;
        return new Expression(
            operationName.get(symbol),
            symbol,
            Double.parseDouble(expression.substring(0, index)),
            Double.parseDouble(expression.substring(isPow ? index + 2 : index + 1))
        );
    }

    @Override
    public String toString() {
        return operation + ":" + number1 + "," + number2;
    }
}
