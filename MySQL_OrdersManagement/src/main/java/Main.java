import Presentation.Controller;
import Presentation.View;

/**
 * Main Class where we run the view
 */
public class Main {
    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setVisible(true);
    }
}