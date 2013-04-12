/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.FType;
import entity.Field;
import entity.Inproceedings;
import entity.Reference;
import entity.ReferenceFactory;
import java.util.ArrayList;
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
     * Test of getFields method, of class Logic.
     */
    @Test
    public void getFieldsForInproceedings() {
        List expResult = inproceedingsFields();
        List result = logic.getFields("inproceedings");
        assertEquals("Field list size not as expected", result.size(), expResult.size());
        for (Object object : result) {
        }

    }

    @Test
    public void testGetReferenceTypes() {
        List<String> expTypes = new ArrayList<String>();
        expTypes.add("inproceedings");
        List<String> result = new ArrayList<String>(logic.getReferenceTypes());
        assertEquals(expTypes, result);
    }

    @Test
    public void createInproceedings() {
        String referenceType = "inproceedings";
        List<Field> fields = inproceedingsFields();
        Object expClass = Inproceedings.class;

        Reference result = logic.createReference(referenceType, fields);
        assertEquals("Returned class not same as expected", result.getClass(), expClass);
        assertEquals("Fields of created reference are wrong", result.getFields(), fields);
    }

    @Test
    public void legacyTestRequiredFields() {
        String[][] expected = {{"referenceId", ""},{"author", ""}, {"title", ""}, {"booktitle", ""}, {"year", ""}};
        String[][] result = logic.getRequiredFields();

        assertEquals(expected.length, result.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i][0], result[i][0]);
            assertEquals(expected[i][1], result[i][1]);
        }
    }

    @Test
    public void legacyTestOptionalFields() {
        String[][] expected = {{"editor", ""}, {"volume/number", ""}, {"series", ""}, {"pages", ""}, {"address", ""},
            {"month", ""}, {"organization", ""}, {"publisher", ""}, {"note", ""}, {"key", ""}};
        String[][] result = logic.getOptionalFields();

        assertEquals(expected.length, result.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i][0], result[i][0]);
            assertEquals(expected[i][1], result[i][1]);
        }
    }

    @Test
    public void legacyCreateInproceedings() {
        String[][] req = {{"referenceId", "ReferenceId"},{"author", "Author"}, {"title", "Title"}, {"booktitle", "Booktitle"}, {"year", "Year"}};
        String[][] opt = {{"editor", "Editor"}, {"volume", "VolumeNumber"}, {"series", "Series"}, {"pages", "Pages"}, {"address", "Address"},
            {"month", "Month"}, {"organization", "Organization"}, {"publisher", "Publisher"}, {"note", "Note"}, {"key", "Key"}};
        Object expClass = Inproceedings.class;
        List<Field> expFields = inproceedingsFields();

        Reference result = logic.createReference(req, opt);

        assertEquals("Returned class not same as expected", result.getClass(), expClass);
        assertEquals("Fields of created reference are wrong", result.getFields(), expFields);
    }

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
}