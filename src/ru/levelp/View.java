package ru.levelp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


/**
 * Created by pavelpetrov on 20.03.16.
 */
public class View extends JFrame {
    private JLabel display;
    private CalculatorModel model;
    private double result;
    private JRadioButton engineeringViewChekBox;
    private JRadioButton stadrartViewCheckBox;
    private JPanel westPanel;
    private ButtonGroup group;

    private ActionListener arithmeticalListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            model.operat(display.getText(), button.getText());
        }
    };

    public View(CalculatorModel model) {
        this.model = model;
        init();
        model.setView(this);
    }

    private void init() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setMinimumSize(new Dimension(400, 250));
        setLocationRelativeTo(null);
        setTitle("Calculator");

        JPanel numPanel = new JPanel(new GridLayout(4, 4));
        JPanel arithmeticPanel = new JPanel(new GridLayout(2, 2));
        JPanel supportPanel = new JPanel(new GridLayout(1, 7));
        JPanel eastPanel = new JPanel(new GridLayout(2, 2));
        westPanel = new JPanel(new GridLayout(5, 2));
        JPanel nothPanel = new JPanel(new GridLayout(2, 2));

        display = new JLabel("");

        JButton[] numBtns = new JButton[10];
        for (int i = 0; i < numBtns.length; i++) {
            numBtns[i] = new JButton(String.valueOf(i));
            numBtns[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    if (display.getText().equals("0.0")) {
                        display.setText("");
                        display.setText(display.getText() + button.getText());
                    } else
                        display.setText(display.getText() + button.getText());
                }
            });
        }

        JButton dotBtn = new JButton(",");
        dotBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (display.getText().contains(".")) {
                    display.setText(display.getText());
                } else {
                    display.setText(display.getText() + ".");
                }
            }
        });


        JButton plusMinusBtn = new JButton("+/-");

        PlusMinusListener plusMinusListener = new PlusMinusListener();
        plusMinusBtn.addActionListener(plusMinusListener);


        for (int i = 7; i < 10; i++) {
            numPanel.add(numBtns[i]);
        }
        for (int i = 4; i < 7; i++) {
            numPanel.add((numBtns[i]));
        }
        for (int i = 1; i < 4; i++) {
            numPanel.add((numBtns[i]));
        }
        numPanel.add(dotBtn);
        numPanel.add(numBtns[0]);
        numPanel.add(plusMinusBtn);


        JButton addBtn = new JButton("+");
        JButton subBtn = new JButton("-");
        JButton mulBtn = new JButton("*");
        JButton divBtn = new JButton("/");
        JButton resultBtn = new JButton("=");

        arithmeticPanel.add(addBtn);
        arithmeticPanel.add(subBtn);
        arithmeticPanel.add(mulBtn);
        arithmeticPanel.add(divBtn);

        addBtn.addActionListener(arithmeticalListener);
        subBtn.addActionListener(arithmeticalListener);
        resultBtn.addActionListener(arithmeticalListener);
        mulBtn.addActionListener(arithmeticalListener);
        divBtn.addActionListener(arithmeticalListener);


        JButton mcBtn = new JButton("MC");
        JButton mrBtn = new JButton("MR");
        JButton mAddBtn = new JButton("M+");
        JButton msubBtn = new JButton("M-");
        JButton backSpaceBtn = new JButton("C");
        char x = 8592;
        JButton vecto = new JButton(String.valueOf(x));
        DeleteLastListener deleteLastListener = new DeleteLastListener();
        vecto.addActionListener(deleteLastListener);

        mAddBtn.addActionListener(arithmeticalListener);
        msubBtn.addActionListener(arithmeticalListener);
        mrBtn.addActionListener(arithmeticalListener);
        mcBtn.addActionListener(arithmeticalListener);
        DeleteAllListener deleteAllListener = new DeleteAllListener();
        backSpaceBtn.addActionListener(deleteAllListener);

        supportPanel.add(mcBtn);
        supportPanel.add(mrBtn);
        supportPanel.add(mAddBtn);
        supportPanel.add(msubBtn);
        supportPanel.add(backSpaceBtn);
        supportPanel.add(vecto);

        JButton sinBtn = new JButton("sin");
        JButton cosBtn = new JButton("cos");
        JButton tanBtn = new JButton("tan");
        JButton atanBtn = new JButton("atan");
        JButton lnBtn = new JButton("ln");
        JButton lgBtn = new JButton("lg");
        JButton log2Btn = new JButton("log2");
        JButton x2Btn = new JButton("x^2");
        char sqrt1 = 8730;
        JButton sqrtBtn = new JButton(String.valueOf(sqrt1));
        JButton exponentBtn = new JButton("^");

        sinBtn.addActionListener(arithmeticalListener);
        cosBtn.addActionListener(arithmeticalListener);
        tanBtn.addActionListener(arithmeticalListener);
        atanBtn.addActionListener(arithmeticalListener);
        lnBtn.addActionListener(arithmeticalListener);
        lgBtn.addActionListener(arithmeticalListener);
        log2Btn.addActionListener(arithmeticalListener);
        x2Btn.addActionListener(arithmeticalListener);
        sqrtBtn.addActionListener(arithmeticalListener);
        exponentBtn.addActionListener(arithmeticalListener);

        westPanel.add(sinBtn);
        westPanel.add(cosBtn);
        westPanel.add(tanBtn);
        westPanel.add(atanBtn);
        westPanel.add(lnBtn);
        westPanel.add(lgBtn);
        westPanel.add(log2Btn);
        westPanel.add(x2Btn);
        westPanel.add(sqrtBtn);
        westPanel.add(exponentBtn);

        JMenuBar bar = new JMenuBar();
        JMenu calculatorMenu = new JMenu("Menu");
        JMenu viewMenuItem = new JMenu("View");
        engineeringViewChekBox = new JRadioButton("Engineering view", false);
        stadrartViewCheckBox = new JRadioButton("Standart view", true);

        group = new ButtonGroup();
        group.add(engineeringViewChekBox);
        group.add(stadrartViewCheckBox);


        viewMenuItem.add(engineeringViewChekBox);
        viewMenuItem.add(stadrartViewCheckBox);
        calculatorMenu.add(viewMenuItem);
        bar.add(calculatorMenu);
        ChageStandartView chageStandartView = new ChageStandartView();
        engineeringViewChekBox.addItemListener(chageStandartView);
        stadrartViewCheckBox.addItemListener(chageStandartView);

        nothPanel.add(display);
        nothPanel.add(supportPanel);
        eastPanel.add(arithmeticPanel);
        eastPanel.add(resultBtn);
        add(BorderLayout.CENTER, numPanel);
        add(BorderLayout.EAST, eastPanel);
        add(BorderLayout.NORTH, nothPanel);

        setJMenuBar(bar);
        setVisible(true);

        pack();

    }

    public void setResult(double result) {
        this.display.setText(String.valueOf(result));
    }

    private class ChageStandartView implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (engineeringViewChekBox.isSelected()) {
                add(BorderLayout.WEST, westPanel);
                invalidate();
                validate();
                repaint();
            } else {
                remove(westPanel);
                invalidate();
                validate();
                repaint();

            }
        }
    }

    private class DeleteAllListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CalculatorModel calculatorModel = new CalculatorModel();
            calculatorModel.setOperands(null);
            calculatorModel.setOperationStack(null);
            display.setText("");
        }
    }

    private class DeleteLastListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String a = display.getText();

            if (a.equals("0") || a.equals("")) {
                display.setText("0");
            } else {
                char[] b = a.toCharArray();
                char[] c = new char[b.length - 1];
                a = "";
                for (int i = 0; i < c.length; i++) {
                    c[i] = b[i];
                    a += c[i];
                }
                if (c.length == 0) {
                    display.setText("0");
                } else display.setText(a);
            }
        }
    }

    private class PlusMinusListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String act = "";
            char[] a = display.getText().toCharArray();
            if (a[0] == '-') {
                char[] b = new char[a.length - 1];
                for (int i = 0; i < b.length; i++) {
                    b[i] = a[1 + i];
                    act += b[i];

                }
            } else {
                char[] b = new char[a.length + 1];
                b[0] = '-';
                act += b[0];
                for (int i = 0; i < a.length; i++) {
                    b[i + 1] = a[i];
                    act += b[i + 1];

                }
            }
            display.setText(act);
        }
    }
}
