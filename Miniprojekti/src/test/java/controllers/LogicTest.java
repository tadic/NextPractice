/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Field;
import entity.ReferenceFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
     * Test of getFields method, of class Logic.
     */
    @Test
    public void getFieldsForInproceedings() {
        List expResult = factory.getFields("inproceedings");
        List result = logic.getFields("inproceedings");
        assertEquals("Field list size not as expected", result.size(), expResult.size());
        for (Object object : result) {
            
        }
        
    }

    private List<Field> inproceedingsFields() {
        List<Field> fields = new ArrayList<Field>();
        fields.add(new Field("author", true));
        fields.add(new Field("title", true));
        fields.add(new Field("booktitle", true));
        fields.add(new Field("year", true));

        fields.add(new Field("editor", false));
        fields.add(new Field("volume/number", false));
        fields.add(new Field("series", false));
        fields.add(new Field("pages", false));
        fields.add(new Field("address", false));
        fields.add(new Field("month", false));
        fields.add(new Field("organization", false));
        fields.add(new Field("publisher", false));
        fields.add(new Field("note", false));
        fields.add(new Field("key", false));

        return fields;
    }

    @Test
    public void testGetReferenceTypes() {
        Set<String> types = logic.getReferenceTypes();
    }
//
//    /**
//     * Test of createReference method, of class Logic.
//     */
//    @Test
//    public void testCreateReference_String_List() {
//        System.out.println("createReference");
//        String referenceType = "";
//        List<Field> fields = null;
//        Logic instance = new Logic();
//        Reference expResult = null;
//        Reference result = instance.createReference(referenceType, fields);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getRequiredFields method, of class Logic.
//     */
//    @Test
//    public void testGetRequiredFields() {
//        System.out.println("getRequiredFields");
//        Logic instance = new Logic();
//        String[][] expResult = null;
//        String[][] result = instance.getRequiredFields();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getOptionalFields method, of class Logic.
//     */
//    @Test
//    public void testGetOptionalFields() {
//        System.out.println("getOptionalFields");
//        Logic instance = new Logic();
//        String[][] expResult = null;
//        String[][] result = instance.getOptionalFields();
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of createReference method, of class Logic.
//     */
//    @Test
//    public void testCreateReference_StringArrArr_StringArrArr() {
//        System.out.println("createReference");
//        String[][] required = null;
//        String[][] optional = null;
//        Logic instance = new Logic();
//        Inproceedings expResult = null;
//        Inproceedings result = instance.createReference(required, optional);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}