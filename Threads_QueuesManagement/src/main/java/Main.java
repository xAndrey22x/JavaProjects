import GUI.SimulationFrame;
import GUI.SimulationFrameController;


public class Main {
    public static void main(String[] args) {
        SimulationFrame simulationFrame = new SimulationFrame();
        SimulationFrameController simulationFrameController = new SimulationFrameController(simulationFrame);
        simulationFrame.setVisible(true);
    }
}