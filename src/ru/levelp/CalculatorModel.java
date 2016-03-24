package ru.levelp;

import java.util.Stack;

/**
 * Created by pavelpetrov on 20.03.16.
 */
public class CalculatorModel {
    private View view;
    private String operation;
    private Stack<Double> operands;
    private Stack<String> operationStack;

    public void setOperationStack(Stack<String> operationStack) {
        this.operationStack = operationStack;
    }

    public void setOperands(Stack<Double> operands) {
        this.operands = operands;
    }

    private double memory;

    public void setView(View view) {
        this.view = view;
    }

    public CalculatorModel() {
        operationStack = new Stack<>();
        operands = new Stack<>();
        memory = 0;
    }

    public void operat(String operand1, String operation) {
        double op1 = Double.parseDouble(operand1);
        operands.push(op1);
        operationStack.push(operation);
        char sqr1 = 8730;
        String sqr2 = String.valueOf(sqr1);
        double myResul = 0;
        if (operation.equals("=")) {
            operationStack.pop();
            myResul = toDo();

        } else if (operationStack.peek().equals("sin")) {
            myResul = Math.sin(Math.toRadians(operands.pop()));
            operationStack.pop();
        } else if (operation.equals("tan")) {
            myResul = Math.tan(Math.toRadians(operands.pop()));
            operationStack.pop();
        } else if (operation.equals("atan")) {
            myResul = Math.atan(Math.toRadians(operands.pop()));
            operationStack.pop();
        } else if (operation.equals("cos")) {
            myResul = Math.cos(Math.toRadians(operands.pop()));
            operationStack.pop();
        } else if (operation.equals("ln")) {
            myResul = Math.log(operands.pop());
            operationStack.pop();
        } else if (operation.equals("lg")) {
            myResul = Math.log10(operands.pop());
            operationStack.pop();
        } else if (operation.equals("log2")) {
            myResul = Math.log10(operands.pop()) / Math.log10(2);
            operationStack.pop();
        } else if (operation.equals("x^2")) {
            myResul = Math.pow(operands.pop(), 2);
            operationStack.pop();
        } else if (operation.equals(sqr2)) {
            myResul = Math.sqrt(operands.pop());
            operationStack.pop();
        } else if (operation.equals("M+")) {
            memory = memory + operands.pop();
            operationStack.pop();
            myResul = 0;
        } else if (operation.equals("M-")) {
            operationStack.pop();
            memory -= operands.pop();
            myResul = 0;
        } else if (operation.equals("MC")) {
            operationStack.pop();
            memory = 0;
        } else if (operation.equals("MR")) {
            operationStack.pop();
            myResul = memory;

        } else if (operation.equals("^")) {
            double a = operands.pop();
            double b = operands.pop();
            myResul = Math.pow(b, a);
            operationStack.pop();
        } else {
            this.operation = operation;
            myResul = 0;
            operationStack.pop();
        }

        view.setResult(myResul);

    }

    public double toDo() {
        double result = 0;
        while (!operationStack.isEmpty())
            if (operationStack.peek().equals("-")) {
                double b = operands.pop();

                double a = operands.pop();
                operationStack.pop();

                if (!operationStack.isEmpty()) {
                    if (operationStack.peek().equals("-")) {
                        result = a + b;
                    } else {
                        result = a - b;
                    }
                } else {
                    result = a - b;
                }
                operands.push(result);


            } else if (operationStack.peek().equals("+")) {
                double b = operands.pop();
                double a = operands.pop();
                operationStack.pop();
                if (!operationStack.isEmpty()) {
                    if (operationStack.peek().equals("-")) {
                        result = a - b;
                    } else {
                        result = a + b;
                    }
                } else {
                    result = a + b;
                }

                operands.push(result);


            } else if (operationStack.peek().equals("*")) {
                result = operands.pop() * operands.pop();
                operands.push(result);
                operationStack.pop();

            } else if (operationStack.peek().equals("/")) {
                double b = operands.pop();
                double a = operands.pop();
                result = a / b;
                operands.push(result);
                operationStack.pop();
            }

        return result;
    }


    /*
    отслежить ввод второго операнда
    изсенить метод операте длясуммы и показ пезультата при ровно
     */
}

