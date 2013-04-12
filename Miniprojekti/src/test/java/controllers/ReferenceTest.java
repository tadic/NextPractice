
package controllers;

import entity.Inproceedings;
import entity.Reference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ivantadic
 */
public class ReferenceTest {
    Reference ref;
    public ReferenceTest() {
    }

    
    @Before
    public void setUp() {
        ref = new Inproceedings();
        ref.setFieldValue("referenceId", "Ro:2002"); 
        ref.setFieldValue("author", "Roumani, Hamzeh"); 
        ref.setFieldValue("title", "Design guidelines for the lab component of objects-first CS1");
        ref.setFieldValue("booktitle", "SIGCSE '02: Proceedings of the 33rd SIGCSE technical symposium on Computer science education" );
        ref.setFieldValue("year", "2002");
        ref.setFieldValue("editor", "one");
        ref.setFieldValue("volume/number", "two");
        ref.setFieldValue("series", "three");
        ref.setFieldValue("pages", "four");
        ref.setFieldValue("address", "five");
        ref.setFieldValue("month", "six");
        ref.setFieldValue("organization", "seven");
        ref.setFieldValue("publisher", "eight");
        ref.setFieldValue("note", "nine");
        ref.setFieldValue("key", "ten");  
    }
    
    @Test
    public void testIsRegularNormal(){
        assertTrue(ref.isRegular(null));
    }
    
    @Test
    public void testIsRegularNormalOnlyRequiredFields(){
        ref.setFieldValue("editor", "");
        ref.setFieldValue("volume/number", "");
        ref.setFieldValue("series", "");
        ref.setFieldValue("pages", "");
        ref.setFieldValue("address", "");
        ref.setFieldValue("month", "");
        ref.setFieldValue("organization", "");
        ref.setFieldValue("publisher", "");
        ref.setFieldValue("note", "");
        ref.setFieldValue("key", ""); 
        assertTrue(ref.isRegular(null));
    }
    
    @Test
    public void testIsRegularNormalReferenceAlreadyGivenDefaultID() {
        Reference r = new Inproceedings();
        r.setFieldValue("referenceId", "Ro:2002"); 
        r.setFieldValue("author", "Roumani, Hamzeh"); 
        r.setFieldValue("title", "Design guidelines for the lab component of objects-first CS1");
        r.setFieldValue("booktitle", "SIGCSE '02: Proceedings of the 33rd SIGCSE technical symposium on Computer science education" );
        r.setFieldValue("year", "2002");
        List<Reference> list = new ArrayList<Reference>();
        list.add(r);
        
        ref.setFieldValue("referenceId", "");
        ref.isRegular(list);
        
        assertTrue(ref.isRegular(list));
        assertEquals("Ro:2002_10", ref.getFieldValue("referenceId"));
    }
        @Test
    public void testIsRegularNormalReferenceAlreadyGivenDefaultIDTwoTimes() {
        Reference r1 = new Inproceedings();
        r1.setFieldValue("referenceId", "Ro:2002"); 
        r1.setFieldValue("author", "Roumani, Hamzeh"); 
        r1.setFieldValue("title", "ects-first CS1");
        r1.setFieldValue("booktitle", "SIGcience education" );
        r1.setFieldValue("year", "2002");
        Reference r2 = new Inproceedings();
        r2.setFieldValue("referenceId", "Ro:2002_10"); 
        r2.setFieldValue("author", "Roumani, Hamzeh"); 
        r2.setFieldValue("title", "ects-first CS1");
        r2.setFieldValue("booktitle", "SIGcience education" );
        r2.setFieldValue("year", "2002");
        List<Reference> list = new ArrayList<Reference>();
        list.add(r1);
        list.add(r2);
        
        ref.setFieldValue("referenceId", "");
        
        assertTrue(ref.isRegular(list));
        assertEquals("Ro:2002_11", ref.getFieldValue("referenceId"));
    }
    @Test
    public void testIsRegularNormalReferenceWithoutID(){
        ref.setFieldValue("referenceId", "");
        assertTrue(ref.isRegular(null));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIsRegularForExceptionReferenceHaveSameIdAsInList() {
        Reference r1 = new Inproceedings();
        r1.setFieldValue("referenceId", "reference"); 
        r1.setFieldValue("author", "Roumani, Hamzeh"); 
        r1.setFieldValue("title", "ects-first CS1");
        r1.setFieldValue("booktitle", "SIGcience education" );
        r1.setFieldValue("year", "2002");

        List<Reference> list = new ArrayList<Reference>();
        list.add(r1);
        
        ref.setFieldValue("referenceId", "reference");
        
        assertTrue(ref.isRegular(list));
        assertEquals("Ro:2002_11", ref.getFieldValue("referenceId"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIsRegularForExceptionEmptyTitle(){
        ref.setFieldValue("title", ""); 
        ref.isRegular(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIsRegularForExceptionWrongYearIfGreatherThenCurrent(){
        int y = Calendar.getInstance().get(Calendar.YEAR);
        y++;
        String year = Integer.toString(y);
        ref.setFieldValue("year", year);
        ref.isRegular(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIsRegularForExceptionTooShortAuthor(){
        ref.setFieldValue("author", "H"); 
        ref.isRegular(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsRegularForExceptionWrongYearCharacter(){
        ref.setFieldValue("year", "20o9");
        assertTrue(ref.isRegular(null));
    }
    
    
 
}
