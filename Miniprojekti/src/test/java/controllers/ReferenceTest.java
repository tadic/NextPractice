
package controllers;

import entity.Article;
import entity.Book;
import entity.FType;
import entity.Field;
import entity.Inproceedings;
import entity.Reference;
import entity.ReferenceFactory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @article ivantadic
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
    public void testBookConstructor(){
        ReferenceFactory rf = new ReferenceFactory();
        List<Field> list = rf.getFields("book");
        Reference book = new Book(list);
        assertNotNull(book);
        assertNotNull(book.getTags());
        assertEquals("book", book.getReferenceType());
        assertEquals(rf.getFields("book").size(), book.getFields().size());
    }
    
    @Test
    public void testArticleConstructor(){
        ReferenceFactory rf = new ReferenceFactory();
        List<Field> list = rf.getFields("article");
        Reference article = new Article(list);
        assertNotNull(article);
        assertNotNull(article.getTags());
        assertEquals("article", article.getReferenceType());
        assertEquals(rf.getFields("article").size(), article.getFields().size());
    }
    @Test
    public void testIsRegularListContainsSameDefaultID() {
        Reference r = new Inproceedings();
        r.setFieldValue(FType.referenceId, "inpr_10"); 
        r.setFieldValue(FType.author, "Roumani, Hamzeh"); 
        r.setFieldValue(FType.title, "Design guidelines for the lab component of objects-first CS1");
        r.setFieldValue(FType.booktitle, "SIGCSE '02: Proceedings of the 33rd SIGCSE technical symposium on Computer science education" );
        r.setFieldValue(FType.year, "2002");
        List<Reference> list = new ArrayList<Reference>();
        list.add(r);
        
        ref.setFieldValue(FType.referenceId, "");
        ref.isRegular(list);
        
        assertTrue(ref.isRegular(list));
        assertEquals("inpr_11", ref.getFieldValue(FType.referenceId));
    }
        @Test
    public void testIsRegularNormalReferenceAlreadyGivenDefaultIDTwoTimes() {
        Reference r1 = new Inproceedings();
        r1.setFieldValue(FType.referenceId, "inpr_10"); 
        r1.setFieldValue(FType.author, "Roumani, Hamzeh"); 
        r1.setFieldValue(FType.title, "ects-first CS1");
        r1.setFieldValue(FType.booktitle, "SIGcience education" );
        r1.setFieldValue(FType.year, "2002");
        Reference r2 = new Inproceedings();
        r2.setFieldValue(FType.referenceId, "inpr_11"); 
        r2.setFieldValue(FType.author, "Roumani, Hamzeh"); 
        r2.setFieldValue(FType.title, "ects-first CS1");
        r2.setFieldValue(FType.booktitle, "SIGcience education" );
        r2.setFieldValue(FType.year, "2002");
        List<Reference> list = new ArrayList<Reference>();
        list.add(r1);
        list.add(r2);
        
        ref.setFieldValue(FType.referenceId, "");
        
        assertTrue(ref.isRegular(list));
        assertEquals("inpr_12", ref.getFieldValue(FType.referenceId));
    }
    @Test
    public void testisRequiredIfRefNotContainsFieldList(){
        Reference book = new Book(null);
        try {
            book.isRegular(null);      
        } catch (IllegalArgumentException e){
            assertEquals("Reference doesn't contain list of fields!?", e.getMessage());
        }
    }
    @Test
    public void testisRequiredIfRefNotHaveFieldListOfRightSize(){
        Reference book = new Book(new ArrayList<Field>());
        try {
            book.isRegular(null);      
        } catch (IllegalArgumentException e){
            assertEquals("Reference doesn't have right number of fiels!", e.getMessage());
        }
    }
    @Test
    public void testisRequiredIfRefNotHaveRightFields(){
        Reference book = new Book(new ReferenceFactory().getFields("article"));
        book.getFields().add(new Field(FType.address, true));
        try {
            book.isRegular(null);      
        } catch (IllegalArgumentException e){
            assertEquals("Reference doesn't contain right fields!", e.getMessage());
        }
    }
    
    @Test
    public void testIsRegularNormalReferenceWithoutID(){
        ref.setFieldValue(FType.referenceId, "");
        assertTrue(ref.isRegular(null));
        assertEquals("inpr_10", ref.getFieldValue(FType.referenceId));
    }
    
    @Test
    public void testIsUniqueBookWithoutID(){
        Reference book = new Book();
        book.setFieldValue(FType.author, "Author");
        book.setFieldValue(FType.title, "Title");
        book.setFieldValue(FType.publisher, "Publisher");
        book.setFieldValue(FType.year, "2013");
        
        assertTrue(book.isUnique(null));
        assertEquals("book_10", book.getFieldValue(FType.referenceId));
    }
    
    @Test
    public void testIsRegularForExceptionArticleWithEmptyYear(){
        Reference article = new Article();
        article.setFieldValue(FType.author, "Author");
        article.setFieldValue(FType.title, "Title");
        article.setFieldValue(FType.journal, "Journal");
        try {
            article.isRegular(null);      
        } catch (IllegalArgumentException e){
            assertEquals("Must fill all required fields!", e.getMessage());
        }
    }
        @Test
    public void testIsRegularForExceptionArticleWithTooShortAuthor(){
        Reference article = new Article();
        article.setFieldValue(FType.author, "A");
        article.setFieldValue(FType.title, "Title");
        article.setFieldValue(FType.journal, "Journal");
        article.setFieldValue(FType.year, "1999");
        try {
            article.isRegular(null);      
        } catch (IllegalArgumentException e){
            assertEquals("Fields lenght must be greather then 1", e.getMessage());
        }
    }
    @Test
    public void testIsRegularForArticleReferenceNormal(){
        Reference article = new Article();
        article.setFieldValue(FType.author, "Author");
        article.setFieldValue(FType.title, "Title");
        article.setFieldValue(FType.journal, "Journal");
        article.setFieldValue(FType.year, "1898");
        
        assertTrue(article.isRegular(null));
    }
    
    
    @Test
    public void testIsRegularForExceptionReferenceHaveSameIdAsInList() {
        Reference r1 = new ReferenceFactory().createReference("inproceedings");
        r1.setFieldValue(FType.referenceId, "reference"); 
        r1.setFieldValue(FType.author, "Roumani, Hamzeh"); 
        r1.setFieldValue(FType.title, "ects-first CS1");
        r1.setFieldValue(FType.booktitle, "SIGcience education" );
        r1.setFieldValue(FType.year, "2002");
        List<Reference> list = new ArrayList<Reference>();
        list.add(r1);
        ref.setFieldValue(FType.referenceId, "reference");
        try {
            ref.isRegular(list);      
        } catch (IllegalArgumentException e){
            assertEquals("Reference with the same referenceId already exists!", e.getMessage());
        }
    }
    
    @Test
    public void testIsRegularForExceptionEmptyTitle(){
        ref.setFieldValue(FType.title, ""); 
        try {
            ref.isRegular(null);      
        } catch (IllegalArgumentException e){
            assertEquals("Must fill all required fields!", e.getMessage());
        }
    }
    @Test
    public void testIsRegularForExceptionWrongYearIfGreatherThenCurrent(){
        int y = Calendar.getInstance().get(Calendar.YEAR);
        y++;
        String year = Integer.toString(y);
        ref.setFieldValue(FType.year, year);
        try {
            ref.isRegular(null);      
        } catch (IllegalArgumentException e){
            assertEquals("Year value must not be negative or grether then current year!", e.getMessage());
        }
    }

    @Test
    public void testIsRegularForExceptionWrongYearCharacter(){
        ref.setFieldValue(FType.year, "20o9");
        try {
            ref.isRegular(null);      
        } catch (IllegalArgumentException e){
            assertEquals("Year value must be a number", e.getMessage());
        }
    }
    @Test
    public void testIsRegularForExceptionRefTypeIsNull(){
        ref.setReferenceType(null);
        try {
            ref.isRegular(null);      
        } catch (IllegalArgumentException e){
            assertEquals("Reference_type must be NON-NULL!", e.getMessage());
        }
    }
    @Test
    public void testIsRegularForExceptionWrongRefType(){
        ref.setReferenceType("rrr");
        try {
            ref.isRegular(null);      
        } catch (IllegalArgumentException e){
            assertEquals("Reference_type value is illegal!", e.getMessage());
        }
    }
    
    
 
}
