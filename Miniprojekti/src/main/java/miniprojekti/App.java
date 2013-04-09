package miniprojekti;

import controllers.Logic;
import controllers.LogicInterface;
import entity.Field;
import entity.Inproceedings;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        LogicInterface logic = new Logic();
        System.out.println("\n\n\n");
        //Hakee tunnetut viitetyypit
        for (String s : logic.getReferenceTypes()) {
            System.out.println(s);
            
        }
        
        //Hakee viitetyypin kentät
        for (Field f : logic.getFields("inproceedings")) {
            System.out.println(f.getKey() + " " + f.isRequired());
        }
        
        //Luo viitteen, kaikkiin fieldeihin "testi", tulostaa kaksi fieldin arvoa
        List<Field> fields = logic.getFields("inproceedings");
        for (Field field : fields) {
            field.setValue("testi");
        }
        Inproceedings inpro = logic.createReference("inproceeding", fields);
        
        System.out.println(inpro.getFieldValue("author"));
        System.out.println(inpro.getFieldValue("title"));
    }
}
