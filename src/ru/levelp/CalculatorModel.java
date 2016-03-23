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
        if (operation.equals("=")) {
                 operationStack.pop();
                view.setResult(toDo());

        } else if (operationStack.peek().equals("sin")) {
            view.setResult(Math.sin(Math.toRadians(operands.pop())));
        } else if (operation.equals("tan")) {
            view.setResult(Math.tan(Math.toRadians(operands.pop())));
        } else if (operation.equals("atan")) {
            view.setResult(Math.atan(Math.toRadians(operands.pop())));
        } else if (operation.equals("cos")) {
            view.setResult(Math.cos(Math.toRadians(operands.pop())));
        } else if (operation.equals("ln")) {
            view.setResult(Math.log(operands.pop()));
        } else if (operation.equals("lg")) {
            view.setResult(Math.log10(operands.pop()));
        } else if (operation.equals("log2")) {
            view.setResult(Math.log10(operands.pop()) / Math.log10(2));
        } else if (operation.equals("x^2")) {
            view.setResult(Math.pow(operands.pop(), 2));
        } else if (operation.equals(sqr2)) {
            view.setResult(Math.sqrt(operands.pop()));
        }
        else if (operation.equals("M+")){
          memory = memory + operands.pop();
            view.setResult(0);
        }
        else if (operation.equals("M-")){
           memory -= operands.pop();
            view.setResult(0);
        }
        else if (operation.equals("MC")){
           memory = 0;
        }
        else if (operation.equals("MR")){
            view.setResult(memory);
        }
        else if (operation.equals("^")) {
            double a = operands.pop();
            double b = operands.pop();
            view.setResult(Math.pow(b, a));
        }
        else{
            this.operation = operation;
            view.setResult(0);
        }
    }

    public double toDo() {
        double result = 0;
            while (!operationStack.isEmpty())
            if (operationStack.peek().equals("-")) {
                double b = operands.pop();
                double a = operands.pop();
                result = a - b;
                operands.push(result);
                operationStack.pop();

            } else if (operationStack.peek().equals("+")) {
                result = operands.pop() + operands.pop();
                operands.push(result);
                operationStack.pop();

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

