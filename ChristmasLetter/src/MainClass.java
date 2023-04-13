
import java.util.Set;
import java.util.TreeSet;

import controller.Controller;
import model.Gift;
import view.GiftsView;
import view.SenderView;

public class MainClass {

	public static void main(String[] args) {
		SenderView senderView = new SenderView();
		GiftsView giftsView = new GiftsView();
		Set<Gift> gifts = new TreeSet<Gift>();
		Controller controller = new Controller(senderView, giftsView, gifts);
		senderView.setVisible(true);
	}

}
