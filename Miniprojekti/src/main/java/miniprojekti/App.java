package miniprojekti;

import UI.GUI;
import UI.GUI_007;
import controllers.Converter;
import controllers.LogicInterface;
import controllers.Logic_007;
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

        Logic_007 logic = new Logic_007();

        new GUI_007(logic);
        

//        List<Field> inpro = logic.getFields("inproceedings");
//        inpro.get(1).setValue("niko1");
//        inpro.get(2).setValue("niko1");
//        inpro.get(3).setValue("niko1");
//        inpro.get(4).setValue("2001");
//
//        logic.createReference("inproceedings", inpro);
//
//
//        List<Field> inpro2 = logic.getFields("inproceedings");
//        inpro2.get(1).setValue("niko2");
//        inpro2.get(2).setValue("niko2");
//        inpro2.get(3).setValue("niko2");
//        inpro2.get(4).setValue("1988");
//
//        logic.createReference("inproceedings", inpro2);
//
//        List<Reference> ref = logic.getAllReferences();
//
//        System.out.println("listassa : " + ref.size());
//
//        for (Reference reference : ref) {
//            System.out.println(reference.getFieldValue(FType.author));
//        }
//
//        System.out.println("listaa");
//        System.out.println(inpro.get(1).getValue());
//        System.out.println(inpro2.get(1).getValue());
//
//
//
//
//        try {
//            logic.convertAllToBibtex("fileee");
//        } catch (Exception ex) {
//            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
//        }





    }
}
