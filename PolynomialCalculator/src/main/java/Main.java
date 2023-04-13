import BusinessLogic.Operations;
import GraficalUserInterface.CalculatorController;
import GraficalUserInterface.CalculatorView;

public class Main {
    public static void main(String[] args) {
        CalculatorView calculatorView = new CalculatorView();
        Operations op = new Operations();
        CalculatorController calculatorController = new CalculatorController(calculatorView, op);
        calculatorView.setVisible(true);
    }
}