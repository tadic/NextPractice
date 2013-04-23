/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.FType;
import entity.Field;
import entity.ReferenceFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    ReferenceFactory factory;

    public LogicTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        logic = new Logic();
        factory = new ReferenceFactory();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getFields method, of class Logic___Vanha.
     */
    @Test
    public void getFieldsForInproceedings() {
        List expResult = inproceedingsFields();
        List result = logic.getFields("inproceedings");
        assertEquals("Field list size not as expected", result.size(), expResult.size());
    }


//    @Test
//    public void testGetReferenceTypes() {
//        List<String> expTypes = new ArrayList<String>();
//        expTypes.add("article");
//        expTypes.add("book");
//        expTypes.add("inproceedings");
//        List<String> result = new ArrayList<String>(logic.getReferenceTypes());
//        assertEquals(expTypes, result);
//    }

    private List<Field> inproceedingsFields() {
        List<Field> fields = new ArrayList<Field>();
        fields.add(new Field(FType.referenceId, "ReferenceId", true));
        fields.add(new Field(FType.author, "Author", true));
        fields.add(new Field(FType.title, "Title", true));
        fields.add(new Field(FType.booktitle, "Booktitle", true));
        fields.add(new Field(FType.year, "Year", true));

        fields.add(new Field(FType.editor, "Editor", false));
        fields.add(new Field(FType.volume, "VolumeNumber", false));
        fields.add(new Field(FType.series, "Series", false));
        fields.add(new Field(FType.pages, "Pages", false));
        fields.add(new Field(FType.address, "Address", false));
        fields.add(new Field(FType.month, "Month", false));
        fields.add(new Field(FType.organization, "Organization", false));
        fields.add(new Field(FType.publisher, "Publisher", false));
        fields.add(new Field(FType.note, "Note", false));
        fields.add(new Field(FType.key, "Key", false));

        return fields;
    }

    private List<Field> populate(List<Field> fields, int number) {
        for (Field field : fields) {
            field.setValue(field.getValue() + " " + number);
        }
        return fields;
    }
}