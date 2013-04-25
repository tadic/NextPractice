/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Book;
import entity.FType;
import entity.Field;
import entity.Inbook;
import entity.Reference;
import entity.ReferenceFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JTextField;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author niko
 */
public class LogicTest {

    Logic logic;

    @Before
    public void setUp() {
        logic = new Logic();
        logic.createNewRef("book");
        logic.getRef().setFieldValue(FType.referenceId, "yksi");
        logic.getRef().setFieldValue(FType.author, "kaksi");
        logic.getRef().setFieldValue(FType.title, "kolme");
        logic.getRef().setFieldValue(FType.address, "nelja");
        logic.setFilteredList(new ArrayList<Reference>());
        logic.setListOfRef(new ArrayList<Reference>());
        logic.getListOfRef().add(logic.getRef());
        
    }
    
    @Test
    public void testSetFilter(){
        logic.setFilter("book yksi kaksi");
        assertEquals(logic.getFilteredList().get(0), logic.getRef());
    }
    @Test
    public void testSetFilterForEmptyList(){
        logic.setListOfRef(null);
        logic.setFilter("book yksi kaksi");
        assertNull(logic.getFilteredList());
    }
    @Test
    public void testSetFilterWordNotExsist(){
        logic.setFilter("boook yksi");
        assertEquals(0, logic.getFilteredList().size());
    }
    @Test
    public void testGetSetCurrentRow(){
        logic.setCurrentRow(7);
        assertEquals(7, logic.getCurrentRow());
    }
    @Test
    public void testGetSetOldReference(){
        Reference r = new Book();
        logic.setOldReference(r);
        assertEquals(r, logic.getOldReference());
    }
    @Test
    public void testGetSetOldId(){
        String id = "string";
        logic.setOldId(id);
        assertEquals(id, logic.getOldId());
    }
    @Test
    public void testGetSetOldList(){
        List<Reference> list = new ArrayList<Reference>();
        list.add(new Book());
        logic.setOldList(list);
        assertEquals(list, logic.getOldList());
    }
    @Test
    public void testGetSetRFact(){
        ReferenceFactory rf = new ReferenceFactory();
        logic.setRFactory(rf);
        assertEquals(rf, logic.getRFactory());
    }
    @Test
    public void testGetSetListFields(){
        ArrayList<JTextField> list = new ArrayList<JTextField>();
        list.add(new JTextField());
        logic.setListOfFields(list);
        assertEquals(list, logic.getListOfFields());
    }
    @Test
    public void testGetSetConverter(){
        Converter rf = new Converter();
        logic.setConverter(rf);
        assertEquals(rf, logic.getConverter());
    }
    @Test
    public void testGetFields(){
        logic.setRef(new Inbook());
        List<Field> list1 = logic.getRequiredFields();
        List<Field> list2 = logic.getOptionalFields();
        list1.addAll(list2);
        assertEquals(logic.getFields(logic.getRef().getReferenceType()), list1);
    }
    @Test
    public void testCurrentRefToBibTex(){
        assertEquals(logic.getConverter().toBibTex(logic.getRef()), logic.currentRefToBibTex());
    }


}