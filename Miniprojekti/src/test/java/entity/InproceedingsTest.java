/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author niko
 */
public class InproceedingsTest {

    List<Field> popFields = new ArrayList<Field>();

    public InproceedingsTest() {
        popFields.add(new Field("author", "Author", true));
        popFields.add(new Field("title", "Title", true));
        popFields.add(new Field("booktitle", "Booktitle", true));
        popFields.add(new Field("year", "1988", true));

        popFields.add(new Field("editor", "Editor", false));
        popFields.add(new Field("volume/number", "VolumeNumber", false));
        popFields.add(new Field("series", "Series", false));
        popFields.add(new Field("pages", "Pages", false));
        popFields.add(new Field("address", "Address", false));
        popFields.add(new Field("month", "Month", false));
        popFields.add(new Field("organization", "Organization", false));
        popFields.add(new Field("publisher", "Publisher", false));
        popFields.add(new Field("note", "Note", false));
        popFields.add(new Field("key", "Key", false));
    }
    
    @Test
    public void getFieldsTest() {
        Inproceedings inpro = new Inproceedings(popFields);
        assertEquals(inpro.getFields(), popFields);
    }
    
    @Test
    public void fieldValuesTest() {
        Inproceedings inpro = new Inproceedings(popFields);
        for (Field field : popFields) {
            String fieldName = field.getKey();
            String expValue = field.getValue();
            String resultValue = inpro.getFieldValue(fieldName);
            assertEquals("Field "+fieldName+" has incorrect value " + resultValue, expValue, resultValue);
        }
    }
    
    @Test
    public void setFieldValueTest() {
        Inproceedings inpro = new Inproceedings(popFields);
        String fieldName = "booktitle";
        String expResult = "testi";
        inpro.setFieldValue(fieldName, expResult);
        assertEquals(expResult, inpro.getFieldValue(fieldName));
    }

}