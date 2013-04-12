package miniprojekti;

import UI.GUI;
import controllers.Converter;
import controllers.Logic;
import controllers.LogicInterface;
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

        fields.add(new Field("author", "Author", true));
        fields.add(new Field("title", "Title", true));
        fields.add(new Field("booktitle", "Booktitle", true));
        fields.add(new Field("year", "1988", true));

        fields.add(new Field("editor", "Editor", false));
        fields.add(new Field("volume/number", "VolumeNumber", false));
        fields.add(new Field("series", "Series", false));
        fields.add(new Field("pages", "Pages", false));
        fields.add(new Field("address", "Address", false));
        fields.add(new Field("month", "Month", false));
        fields.add(new Field("organization", "Organization", false));
        fields.add(new Field("publisher", "Publisher", false));
        fields.add(new Field("note", "Note", false));
        fields.add(new Field("key", "Key", false));

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
