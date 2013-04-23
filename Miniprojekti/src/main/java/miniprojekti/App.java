package miniprojekti;

import UI.MainGUI;
import UI.MainGUIController;
import UI.MainGUIModel;
import java.awt.EventQueue;

public class App {

    public static void main(String[] args) {
        final MainGUI g = new MainGUI();
        MainGUIModel m = new MainGUIModel();
        final MainGUIController c = new MainGUIController(g, m);

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                g.setVisible(true);
//                c.populateReferencesWithTestData();
            }
        });

    }
}
