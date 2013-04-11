/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import controllers.Logic;
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
public class FieldTest {
    Field fullField;
    Field copy;
    Field nullField;
    Field noValueField;
    Field reqField;
    Field optField;
    
    
    public FieldTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        fullField = new Field("author", "Author", true);
        copy = new Field("author", "Author", true);
        nullField = null;
        noValueField = new Field("booktitle", true);
        reqField = new Field("year", "1988", true);
        optField = new Field("editor", "Editor", false);
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void changeKey() {
        String exp = "tatti";
        
        fullField.setKey(exp);
        
        assertEquals(fullField.getKey(), exp);       
    }
    
    @Test
    public void hasValue() {
        assertTrue(fullField.hasValue());
    }
    
    @Test
    public void noValue() {
        assertFalse(noValueField.hasValue());
    }
    
    @Test
    public void isRequired() {
        assertTrue(reqField.isRequired());
    }
    
    @Test
    public void isNotRequired() {
        assertFalse(optField.isRequired());
    }
    
    @Test
    public void nullEqual() {
        assertFalse(fullField.equals(null));
    }
    
    @Test
    public void differentClassEqual() {
        assertFalse(fullField.equals(new Logic()));
    }
    
    @Test
    public void copyEqual() {
        assertTrue(fullField.equals(copy));
    }
    
    @Test
    public void differentKeyEqual() {
        String testKey = "vahvero";
        copy.setKey(testKey);
        assertFalse(fullField.equals(copy));
    }
    
    @Test
    public void differentValueEqual() {
        String testValue = "kissa";
        copy.setValue(testValue);
        assertFalse(fullField.equals(copy));
    }
    
    @Test
    public void differentRequEqual() {
        copy.setRequired(false);
        assertFalse(fullField.equals(copy));
    }

}