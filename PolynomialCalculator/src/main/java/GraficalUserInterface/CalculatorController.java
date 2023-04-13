package GraficalUserInterface;

import BusinessLogic.Operations;
import DataModels.Buttons;
import DataModels.Polynomial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {

    private CalculatorView calculatorView;
    private Operations op;

    public CalculatorController(CalculatorView calculatorView, Operations op) {
        this.op = op;
        this.calculatorView = calculatorView;
        this.calculatorView.addButtonListener(new operationsPerformed(Buttons.ADD));
        this.calculatorView.subtractButtonListener(new operationsPerformed(Buttons.SUB));
        this.calculatorView.multiplicationButtonListener(new operationsPerformed(Buttons.MULTI));
        this.calculatorView.divideButtonListener(new operationsPerformed(Buttons.DIVIDE));
        this.calculatorView.derivativeButtonListener(new operationsPerformed(Buttons.DERIVATIVE));
        this.calculatorView.integralButtonListener(new operationsPerformed(Buttons.INTEGRAL));
    }

    class operationsPerformed implements ActionListener {
        private Buttons button;

        public operationsPerformed(Buttons button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String polynom1 = calculatorView.getFirstPoly();
            String polynom2 = calculatorView.getSecondPoly();
            Polynomial polynomial1 = null, polynomial2 = null;
            boolean verified = true;
            if (this.button.equals(Buttons.ADD) || this.button.equals(Buttons.SUB) ||
                    this.button.equals(Buttons.MULTI) || this.button.equals(Buttons.DIVIDE)) {
                try {
                    polynomial1 = op.checkStringInput(polynom1);
                    polynomial2 = op.checkStringInput(polynom2);
                } catch (Exception ex) {
                    calculatorView.errorMessage(ex.getMessage());
                    verified = false;
                }
            } else if ((calculatorView.isFirstPolyCheck() || calculatorView.isSecondPolyCheck()) &&
                    !(calculatorView.isFirstPolyCheck() && calculatorView.isSecondPolyCheck()))
                try {
                    if (calculatorView.isFirstPolyCheck())
                        polynomial1 = op.checkStringInput(polynom1);
                    else if (calculatorView.isSecondPolyCheck())
                        polynomial2 = op.checkStringInput(polynom2);
                } catch (Exception ex) {
                    calculatorView.errorMessage(ex.getMessage());
                    verified = false;
                }
            else {
                calculatorView.errorMessage("Select one checkbox!");
                verified = false;
            }
            if (verified)
                switch (this.button) {
                    case ADD -> calculatorView.resultMessage(op.polyAddition(polynomial1, polynomial2));
                    case SUB -> calculatorView.resultMessage(op.polySubtraction(polynomial1, polynomial2));
                    case MULTI -> calculatorView.resultMessage(op.polyMultiplication(polynomial1, polynomial2));
                    case DIVIDE -> {
                        try {
                            calculatorView.resultMessageDivide(op.polyDivide(polynomial1, polynomial2));
                        } catch (Exception ex) {
                            calculatorView.errorMessage(ex.getMessage());
                        }
                    }
                    case DERIVATIVE -> {
                        if (calculatorView.isFirstPolyCheck())
                            calculatorView.resultMessage(op.polyDerivative(polynomial1));
                        else calculatorView.resultMessage(op.polyDerivative(polynomial2));
                    }
                    case INTEGRAL -> {
                        if (calculatorView.isFirstPolyCheck())
                            calculatorView.resultMessageIntegrate(op.polyIntegrate(polynomial1));
                        else calculatorView.resultMessageIntegrate(op.polyIntegrate(polynomial2));
                    }
                    default -> System.out.println("INVALID");
                }
        }
    }
}
