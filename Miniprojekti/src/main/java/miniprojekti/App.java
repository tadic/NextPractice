package miniprojekti;

import UI.GUI;
import controllers.Converter;
import controllers.Logic;
import controllers.LogicInterface;
import entity.FType;
import entity.Field;
import entity.Reference;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public static void main(String[] args) {
        LogicInterface logic = new Logic();

        List<Field> fields = new ArrayList<Field>();

        fields.add(new Field(FType.referenceId, "Author", true));
        fields.add(new Field(FType.author, "Author", true));
        fields.add(new Field(FType.title, "Title", true));
        fields.add(new Field(FType.booktitle, "Booktitle", true));
        fields.add(new Field(FType.year, "1988", true));


        Reference in = logic.createReference("inproceedings", fields);
        
        Converter converter = new Converter();
        try {
            logic.convertLoadedToBibtex();
                            GUI gui = new GUI(logic,converter);
                            gui.initGUI();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
