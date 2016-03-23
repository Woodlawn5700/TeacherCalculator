package ru.levelp;

/**
 * Created by pavelpetrov on 20.03.16.
 */
public class Main {
    public static void main(String[] args) {

        CalculatorModel model = new CalculatorModel();
        new View(model);
    }
}
