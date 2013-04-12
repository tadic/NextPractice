
package controllers;

import entity.FType;
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
        ref.setFieldValue(FType.referenceId, "Ro:2002"); 
        ref.setFieldValue(FType.author, "Roumani, Hamzeh"); 
        ref.setFieldValue(FType.title, "Design guidelines for the lab component of objects-first CS1");
        ref.setFieldValue(FType.booktitle, "SIGCSE '02: Proceedings of the 33rd SIGCSE technical symposium on Computer science education" );
        ref.setFieldValue(FType.year, "2002");
        ref.setFieldValue(FType.editor, "one");
        ref.setFieldValue(FType.volume, "two");
        ref.setFieldValue(FType.series, "three");
        ref.setFieldValue(FType.pages, "four");
        ref.setFieldValue(FType.address, "five");
        ref.setFieldValue(FType.month, "six");
        ref.setFieldValue(FType.organization, "seven");
        ref.setFieldValue(FType.publisher, "eight");
        ref.setFieldValue(FType.note, "nine");
        ref.setFieldValue(FType.key, "ten");  
    }
    
    @Test
    public void testIsRegularNormal(){
        assertTrue(ref.isRegular(null));
    }
    
    @Test
    public void testIsRegularNormalOnlyRequiredFields(){
        ref.setFieldValue(FType.editor, "");
        ref.setFieldValue(FType.volume, "");
        ref.setFieldValue(FType.series, "");
        ref.setFieldValue(FType.pages, "");
        ref.setFieldValue(FType.address, "");
        ref.setFieldValue(FType.month, "");
        ref.setFieldValue(FType.organization, "");
        ref.setFieldValue(FType.publisher, "");
        ref.setFieldValue(FType.note, "");
        ref.setFieldValue(FType.key, "");  
        assertTrue(ref.isRegular(null));
    }
    
    @Test
    public void testIsRegularNormalReferenceAlreadyGivenDefaultID() {
        Reference r = new Inproceedings();
        r.setFieldValue(FType.referenceId, "Ro:2002"); 
        r.setFieldValue(FType.author, "Roumani, Hamzeh"); 
        r.setFieldValue(FType.title, "Design guidelines for the lab component of objects-first CS1");
        r.setFieldValue(FType.booktitle, "SIGCSE '02: Proceedings of the 33rd SIGCSE technical symposium on Computer science education" );
        r.setFieldValue(FType.year, "2002");
        List<Reference> list = new ArrayList<Reference>();
        list.add(r);
        
        ref.setFieldValue(FType.referenceId, "");
        ref.isRegular(list);
        
        assertTrue(ref.isRegular(list));
        assertEquals("Ro:2002_10", ref.getFieldValue(FType.referenceId));
    }
        @Test
    public void testIsRegularNormalReferenceAlreadyGivenDefaultIDTwoTimes() {
        Reference r1 = new Inproceedings();
        r1.setFieldValue(FType.referenceId, "Ro:2002"); 
        r1.setFieldValue(FType.author, "Roumani, Hamzeh"); 
        r1.setFieldValue(FType.title, "ects-first CS1");
        r1.setFieldValue(FType.booktitle, "SIGcience education" );
        r1.setFieldValue(FType.year, "2002");
        Reference r2 = new Inproceedings();
        r2.setFieldValue(FType.referenceId, "Ro:2002_10"); 
        r2.setFieldValue(FType.author, "Roumani, Hamzeh"); 
        r2.setFieldValue(FType.title, "ects-first CS1");
        r2.setFieldValue(FType.booktitle, "SIGcience education" );
        r2.setFieldValue(FType.year, "2002");
        List<Reference> list = new ArrayList<Reference>();
        list.add(r1);
        list.add(r2);
        
        ref.setFieldValue(FType.referenceId, "");
        
        assertTrue(ref.isRegular(list));
        assertEquals("Ro:2002_11", ref.getFieldValue(FType.referenceId));
    }
    @Test
    public void testIsRegularNormalReferenceWithoutID(){
        ref.setFieldValue(FType.referenceId, "");
        assertTrue(ref.isRegular(null));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIsRegularForExceptionReferenceHaveSameIdAsInList() {
        Reference r1 = new Inproceedings();
        r1.setFieldValue(FType.referenceId, "reference"); 
        r1.setFieldValue(FType.author, "Roumani, Hamzeh"); 
        r1.setFieldValue(FType.title, "ects-first CS1");
        r1.setFieldValue(FType.booktitle, "SIGcience education" );
        r1.setFieldValue(FType.year, "2002");

        List<Reference> list = new ArrayList<Reference>();
        list.add(r1);
        
        ref.setFieldValue(FType.referenceId, "reference");
        
        assertTrue(ref.isRegular(list));
        assertEquals("Ro:2002_11", ref.getFieldValue(FType.referenceId));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIsRegularForExceptionEmptyTitle(){
        ref.setFieldValue(FType.title, ""); 
        ref.isRegular(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIsRegularForExceptionWrongYearIfGreatherThenCurrent(){
        int y = Calendar.getInstance().get(Calendar.YEAR);
        y++;
        String year = Integer.toString(y);
        ref.setFieldValue(FType.year, year);
        ref.isRegular(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIsRegularForExceptionTooShortAuthor(){
        ref.setFieldValue(FType.author, "H"); 
        ref.isRegular(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsRegularForExceptionWrongYearCharacter(){
        ref.setFieldValue(FType.year, "20o9");
        assertTrue(ref.isRegular(null));
    }
    
    
 
}
