package entity;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import repositories.GenericDataFileRepository;
import repositories.GenericRepository;

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
        ref.setFieldValue(FType.booktitle, "SIGCSE '02: Proceedings of the 33rd SIGCSE technical symposium on Computer science education");
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
    public void testSetGetFieldValueIfNotExists() {
        ref.setFieldValue(FType.howpublished, "value");
        assertNull(ref.getFieldValue(FType.howpublished));
    }

    @Test
    public void testIsRegularNormal() {
        try {
            assertTrue(ref.isRegular());
        } catch (Exception ex) {
            Logger.getLogger(ReferenceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testIsRegularNormalOnlyRequiredFields() {
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
        try {
            assertTrue(ref.isRegular());
        } catch (Exception ex) {
            Logger.getLogger(ReferenceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testBookConstructor() {
        ReferenceFactory rf = new ReferenceFactory();
        List<Field> list = rf.getFields("book");
        Reference book = new Book(list);
        assertNotNull(book);
        assertEquals("book", book.getReferenceType());
        assertEquals(rf.getFields("book").size(), book.getFields().size());
    }
    @Test
    public void testInproceedingsConstructor() {
        ReferenceFactory rf = new ReferenceFactory();
        List<Field> inproceedings = rf.getFields("inproceedings");
        Reference book = new Inproceedings(inproceedings);
        assertNotNull(book);
        assertEquals("inproceedings", book.getReferenceType());
        assertEquals(rf.getFields("inproceedings").size(), book.getFields().size());
    }
    @Test
    public void testBookletConstructor() {
        ReferenceFactory rf = new ReferenceFactory();
        List<Field> list = rf.getFields("booklet");
        Reference booklet = new Booklet(list);
        assertNotNull(booklet);
        assertEquals("booklet", booklet.getReferenceType());
        assertEquals(rf.getFields("booklet").size(), booklet.getFields().size());
    }
    @Test
    public void testConferenceConstructor() {
        ReferenceFactory rf = new ReferenceFactory();
        List<Field> list = rf.getFields("conference");
        Reference conference =  new Conference(list);
        assertNotNull(conference);
        assertEquals("conference", conference.getReferenceType());
        assertEquals(rf.getFields("conference").size(), conference.getFields().size());
    }
    @Test
    public void testInbookConstructor() {
        ReferenceFactory rf = new ReferenceFactory();
        List<Field> list = rf.getFields("inbook");
        Reference inbook =  new Inbook(list);
        assertNotNull(inbook);
        assertEquals("inbook", inbook.getReferenceType());
        assertEquals(rf.getFields("inbook").size(), inbook.getFields().size());
    }
    @Test
    public void testIncolectionConstructor() {
        ReferenceFactory rf = new ReferenceFactory();
        List<Field> list = rf.getFields("incollection");
        Reference incollection =  new Incollection(list);
        assertNotNull(incollection);
        assertEquals("incollection", incollection.getReferenceType());
        assertEquals(rf.getFields("incollection").size(), incollection.getFields().size());
    }
    @Test
    public void testManualConstructor() {
        ReferenceFactory rf = new ReferenceFactory();
        List<Field> list = rf.getFields("manual");
        Reference manual =  new Manual(list);
        assertNotNull(manual);
        assertEquals("manual", manual.getReferenceType());
        assertEquals(rf.getFields("manual").size(), manual.getFields().size());
    }
    @Test
    public void testMastersthesisConstructor() {
        ReferenceFactory rf = new ReferenceFactory();
        List<Field> list = rf.getFields("mastersthesis");
        Reference mastersthesis =  new Mastersthesis(list);
        assertNotNull(mastersthesis);
        assertEquals("mastersthesis", mastersthesis.getReferenceType());
        assertEquals(rf.getFields("mastersthesis").size(), mastersthesis.getFields().size());
    }
    @Test
    public void testMiscConstructor() {
        ReferenceFactory rf = new ReferenceFactory();
        List<Field> list = rf.getFields("misc");
        Reference misc =  new Misc(list);
        assertNotNull(misc);
        assertEquals("misc", misc.getReferenceType());
        assertEquals(rf.getFields("misc").size(), misc.getFields().size());
    }
    @Test
    public void testPhdthesisConstructor() {
        ReferenceFactory rf = new ReferenceFactory();
        List<Field> list = rf.getFields("phdthesis");
        Reference phdthesis =  new Phdthesis(list);
        assertNotNull(phdthesis);
        assertEquals("phdthesis", phdthesis.getReferenceType());
        assertEquals(rf.getFields("phdthesis").size(), phdthesis.getFields().size());
    }
    @Test
    public void testProceedingsConstructor() {
        ReferenceFactory rf = new ReferenceFactory();
        List<Field> list = rf.getFields("proceedings");
        Reference proceedings =  new Proceedings(list);
        assertNotNull(proceedings);
        assertEquals("proceedings", proceedings.getReferenceType());
        assertEquals(rf.getFields("proceedings").size(), proceedings.getFields().size());
    }
    @Test
    public void testTechreportConstructor() {
        ReferenceFactory rf = new ReferenceFactory();
        List<Field> list = rf.getFields("techreport");
        Reference techreport =  new Techreport(list);
        assertNotNull(techreport);
        assertEquals("techreport", techreport.getReferenceType());
        assertEquals(rf.getFields("techreport").size(), techreport.getFields().size());
    }
    @Test
    public void testUnpublishedConstructor() {
        ReferenceFactory rf = new ReferenceFactory();
        List<Field> list = rf.getFields("unpublished");
        Reference unpublished =  new Unpublished(list);
        assertNotNull(unpublished);
        assertEquals("unpublished", unpublished.getReferenceType());
        assertEquals(rf.getFields("unpublished").size(), unpublished.getFields().size());
    }
    @Test
    public void testArticleConstructor() {
        ReferenceFactory rf = new ReferenceFactory();
        List<Field> list = rf.getFields("article");
        Reference article = new Article(list);
        assertNotNull(article);
        assertEquals("article", article.getReferenceType());
        assertEquals(rf.getFields("article").size(), article.getFields().size());
    }

    @Test
    public void testIsRegularListContainsSameDefaultID() throws Exception {
        ReferenceFactory rf = new ReferenceFactory();
        List<Field> fields = rf.getFields("inproceedings");
        Reference r = rf.createReference("inproceedings", fields);
        r.setFieldValue(FType.referenceId, "inpr_10");
        r.setFieldValue(FType.author, "Roumani, Hamzeh");
        r.setFieldValue(FType.title, "Design guidelines for the lab component of objects-first CS1");
        r.setFieldValue(FType.booktitle, "SIGCSE '02: Proceedings of the 33rd SIGCSE technical symposium on Computer science education");
        r.setFieldValue(FType.year, "2002");
        List<Reference> list = new ArrayList<Reference>();
        list.add(r);

        ref.setFieldValue(FType.referenceId, "");
        assertTrue(ref.isUnique(list, null));

        assertEquals("inpr_11", ref.getFieldValue(FType.referenceId));
    }

    @Test
    public void testIsRegularNormalReferenceAlreadyGivenDefaultIDTwoTimes() throws Exception {
        Reference r1 = new Inproceedings();
        r1.setFieldValue(FType.referenceId, "inpr_10");
        r1.setFieldValue(FType.author, "Roumani, Hamzeh");
        r1.setFieldValue(FType.title, "ects-first CS1");
        r1.setFieldValue(FType.booktitle, "SIGcience education");
        r1.setFieldValue(FType.year, "2002");
        Reference r2 = new Inproceedings();
        r2.setFieldValue(FType.referenceId, "inpr_11");
        r2.setFieldValue(FType.author, "Roumani, Hamzeh");
        r2.setFieldValue(FType.title, "ects-first CS1");
        r2.setFieldValue(FType.booktitle, "SIGcience education");
        r2.setFieldValue(FType.year, "2002");
        List<Reference> list = new ArrayList<Reference>();
        list.add(r1);
        list.add(r2);

        ref.setFieldValue(FType.referenceId, "");

        assertTrue(ref.isUnique(list, null));
        assertEquals("inpr_12", ref.getFieldValue(FType.referenceId));
    }

    @Test
    public void testisRequiredIfRefNotContainsFieldList() throws Exception {
        Reference book = new Book(null);
        try {
            book.isRegular();
        } catch (IllegalArgumentException e) {
            assertEquals("Reference doesn't contain list of fields!?", e.getMessage());
        }
    }

    @Test
    public void testGetFieldsFromRefFactory(){
        ReferenceFactory rf = new ReferenceFactory();
        assertEquals(1, rf.getFields("misc", true).size());
        assertEquals(FType.referenceId, rf.getFields("misc", true).get(0).getKey());
    }
    @Test
    public void testisRequiredIfRefNotHaveFieldListOfRightSize() throws Exception {
        Reference book = new Book(new ArrayList<Field>());
        try {
            book.isRegular();
        } catch (IllegalArgumentException e) {
            assertEquals("Reference doesn't have right number of fiels!", e.getMessage());
        }
    }

    @Test
    public void testisRequiredIfRefNotHaveRightFields() throws Exception {
        Reference book = new Book(new ReferenceFactory().getFields("article"));
        book.getFields().add(new Field(FType.address, true));
        try {
            book.isRegular();
        } catch (IllegalArgumentException e) {
            assertEquals("Reference doesn't contain right fields!", e.getMessage());
        }
    }

    @Test
    public void testIsRegularNormalReferenceWithoutID() throws Exception{
        ref.setFieldValue(FType.referenceId, "");
        assertTrue(ref.isUnique(null, null));
        assertEquals("inpr_10", ref.getFieldValue(FType.referenceId));
    }
    
    @Test
    public void testIsUniqueBookWithoutID(){
        GenericRepository repo = GenericDataFileRepository.getInstance();
        Reference book = new Book();
        book.setFieldValue(FType.author, "Author");
        book.setFieldValue(FType.title, "Title");
        book.setFieldValue(FType.publisher, "Publisher");
        book.setFieldValue(FType.year, "2013");        
        assertTrue(book.isUnique(repo.findAll(), book.getFieldValue(FType.referenceId)));
        assertEquals("book_10", book.getFieldValue(FType.referenceId));
    }
    
    @Test
    public void testIsRegularForExceptionArticleWithEmptyYear() throws Exception {
        Reference article = new Article();
        article.setFieldValue(FType.author, "Author");
        article.setFieldValue(FType.title, "Title");
        article.setFieldValue(FType.journal, "Journal");
        try {
            article.isRegular();
        } catch (IllegalArgumentException e) {
            assertEquals("Must fill all required fields!", e.getMessage());
        }
    }

    @Test
    public void testIsRegularForExceptionArticleWithTooShortAuthor() throws Exception {
        Reference article = new Article();
        article.setFieldValue(FType.author, "A");
        article.setFieldValue(FType.title, "Title");
        article.setFieldValue(FType.journal, "Journal");
        article.setFieldValue(FType.year, "1999");
        try {
            article.isUnique(null, null);
            article.isRegular();
        } catch (IllegalArgumentException e) {
            assertEquals("Fields lenght must be greater then 1", e.getMessage());
        }
    }

    @Test
    public void testIsUniqueForArticleReferenceNormal() throws Exception {
        Reference article = new Article();
        article.setFieldValue(FType.author, "Author");
        article.setFieldValue(FType.title, "Title");
        article.setFieldValue(FType.journal, "Journal");
        article.setFieldValue(FType.year, "1898");

        assertTrue(article.isUnique(null, null));
    }
    @Test
    public void testIsUniqueForSameOldId() throws Exception {
        String oldId = "referenceID";
        ref.setFieldValue(FType.referenceId, oldId);
        assertTrue(ref.isUnique(null, oldId));
    }
    @Test
    public void testIsUniqueIfNotInTheList() throws Exception {
        assertTrue(ref.isUnique(null, null));
    }

    @Test
    public void testIsUniqueIfExists() throws Exception {
        ArrayList<Reference> list = new ArrayList<Reference>();
        list.add(ref);
         try {
            ref.isUnique(list, null);
            assertFalse(true);
        } catch (IllegalArgumentException e) {
            assertEquals("Reference with the same referenceId already exists!", e.getMessage());
        }
    }
    @Test
    public void testIsRegularReferenceNotHaveYearField() throws Exception {
        Reference r1 = new ReferenceFactory().createReference("manual");
        r1.setFieldValue(FType.referenceId, "CS1");
        r1.setFieldValue(FType.title, "ects-first CS1");
        assertTrue(r1.isRegular());
    }
    @Test
    public void testToString() throws Exception {
        String expected = "inproceedings---, Ro:2002, Roumani, Hamzeh, Design guidelines for the lab compo";
        assertEquals(expected, ref.toString());
    }
    @Test
    public void testToStringWithShorterRef() throws Exception {
        ref.setFieldValue(FType.title, "");
        ref.setFieldValue(FType.booktitle, "");
        String expected = "inproceedings---, Ro:2002, Roumani, Hamzeh, , , 2002                           ";
        assertEquals(expected, ref.toString());
    }
    @Test
    public void testHasMethod() throws Exception {
        ref.setId(49); 
        int exp = 350;
        assertEquals(exp, ref.hashCode());
    }
    @Test
    public void testIsRegularForExceptionReferenceHaveSameIdAsInList() throws Exception {
        Reference r1 = new ReferenceFactory().createReference("inproceedings");
        r1.setFieldValue(FType.referenceId, "reference");
        r1.setFieldValue(FType.author, "Roumani, Hamzeh");
        r1.setFieldValue(FType.title, "ects-first CS1");
        r1.setFieldValue(FType.booktitle, "SIGcience education");
        r1.setFieldValue(FType.year, "2002");
        List<Reference> list = new ArrayList<Reference>();
        list.add(r1);
        ref.setFieldValue(FType.referenceId, "reference");
        try {
            ref.isUnique(list, null);
            assertFalse(true);
        } catch (IllegalArgumentException e) {
            assertEquals("Reference with the same referenceId already exists!", e.getMessage());
        }
    }

    @Test
    public void testIsRegularForExceptionEmptyTitle() throws Exception {
        ref.setFieldValue(FType.title, "");
        try {
            ref.isRegular();
        } catch (IllegalArgumentException e) {
            assertEquals("Must fill all required fields!", e.getMessage());
        }
    }

    @Test
    public void testIsRegularForExceptionWrongYearIfGreatherThenCurrent() throws Exception {
        int y = Calendar.getInstance().get(Calendar.YEAR);
        y++;
        String year = Integer.toString(y);
        ref.setFieldValue(FType.year, year);
        try {
            ref.isRegular();
        } catch (IllegalArgumentException e) {
            assertEquals("Year value must not be negative or grether then current year!", e.getMessage());
        }
    }

    @Test
    public void testIsRegularForExceptionWrongYearCharacter() throws Exception {
        ref.setFieldValue(FType.year, "20o9");
        try {
            ref.isRegular();
        } catch (IllegalArgumentException e) {
            assertEquals("Year value must be a number", e.getMessage());
        }
    }

    @Test
    public void testIsRegularForExceptionRefTypeIsNull() throws Exception {
        ref.setReferenceType(null);
        try {
            ref.isRegular();
        } catch (IllegalArgumentException e) {
            assertEquals("Reference_type must be NON-NULL!", e.getMessage());
        }
    }
    
    @Test
    public void testIsRegularForExceptionWrongRefType() throws Exception {
        ref.setReferenceType("rrr");
        try {
            ref.isRegular();
        } catch (IllegalArgumentException e) {
            assertEquals("Reference_type value is illegal!", e.getMessage());
        }
    }
    @Test
    public void testEqualsForNULL(){
        assertFalse(ref.equals(null));
    }
    
    @Test
    public void testEqualsForWrongClass(){
        assertFalse(ref.equals(new Book()));
    }
}
