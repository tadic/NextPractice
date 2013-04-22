package miniprojekti;

import UI.GUI;
import UI.GUINewReferences;
import UI.MainGUI;
import UI.MainGUIController;
import UI.MainGUIModel;
import controllers.Converter;
import controllers.Logic___Vanha;
import controllers.LogicInterface;
import controllers.Logic;
import entity.FType;
import entity.Field;
import entity.Reference;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public static void main(String[] args) {
   //    new GUINewReferences();

        

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
