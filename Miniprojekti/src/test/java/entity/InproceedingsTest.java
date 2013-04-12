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
        popFields.add(new Field(FType.author, "Author", true));
        popFields.add(new Field(FType.title, "Title", true));
        popFields.add(new Field(FType.booktitle, "Booktitle", true));
        popFields.add(new Field(FType.year, "1988", true));

        popFields.add(new Field(FType.editor, "Editor", false));
        popFields.add(new Field(FType.volume, "VolumeNumber", false));
        popFields.add(new Field(FType.series, "Series", false));
        popFields.add(new Field(FType.pages, "Pages", false));
        popFields.add(new Field(FType.address, "Address", false));
        popFields.add(new Field(FType.month, "Month", false));
        popFields.add(new Field(FType.organization, "Organization", false));
        popFields.add(new Field(FType.publisher, "Publisher", false));
        popFields.add(new Field(FType.note, "Note", false));
        popFields.add(new Field(FType.key, "Key", false));
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
            FType fieldName = field.getKey();
            String expValue = field.getValue();
            String resultValue = inpro.getFieldValue(fieldName);
            assertEquals("Field "+fieldName+" has incorrect value " + resultValue, expValue, resultValue);
        }
    }
    
    @Test
    public void setFieldValueTest() {
        Inproceedings inpro = new Inproceedings(popFields);
        FType fieldName = FType.booktitle;
        String expResult = "testi";
        inpro.setFieldValue(fieldName, expResult);
        assertEquals(expResult, inpro.getFieldValue(fieldName));
    }

}