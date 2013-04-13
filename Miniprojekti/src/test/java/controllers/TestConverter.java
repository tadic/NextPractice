package controllers;

import entity.Article;
import entity.Book;
import entity.FType;
import entity.Inproceedings;
import entity.Reference;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author ivantadic
 */
public class TestConverter {
    Converter convert; 
    Reference ref;
        
    @Before
    public void setUp(){
        convert = new Converter();
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
    public void testToBibTexForNormal(){
        String expValue = "@inproceedings{Ro:2002,\n    author = {Roumani, Hamzeh},\n    " +
                "title = {Design guidelines for the lab component of objects-first CS1},\n    " +
                "booktitle = {SIGCSE '02: Proceedings of the 33rd SIGCSE technical symposium on Computer science education},\n    " +
                "year = {2002},\n    editor = {one},\n    volume/number = {two},\n    series = {three},\n    " +
                "pages = {four},\n    address = {five},\n    month = {six},\n    organization = {seven},\n    " +
                "publisher = {eight},\n    note = {nine},\n    key = {ten}\n}";
                System.out.println(expValue);
                System.out.println(convert.toBibTex(ref));
               
        assertEquals(expValue, convert.toBibTex(ref));
    }
      
    @Test
    public void testToBibTexForSpecialCharacters(){
        ref.setFieldValue(FType.author, "Hassinen, Marko and Mäyrä, Hannu"); 
        String expValue = "@inproceedings{Ro:2002,\n    author = {Hassinen, Marko and M\\\"{a}yr\\\"{a}, Hannu},\n    " +
                "title = {Design guidelines for the lab component of objects-first CS1},\n    " +
                "booktitle = {SIGCSE '02: Proceedings of the 33rd SIGCSE technical symposium on Computer science education},\n    " +
                "year = {2002},\n    editor = {one},\n    volume/number = {two},\n    series = {three},\n    " +
                "pages = {four},\n    address = {five},\n    month = {six},\n    organization = {seven},\n    " +
                "publisher = {eight},\n    note = {nine},\n    key = {ten}\n}";
        assertEquals(expValue, convert.toBibTex(ref));
    }
    @Test
    public void testToBibTexForNormalBook(){
        Reference book = new Book();
        book.setFieldValue(FType.referenceId, "book_11");
        book.setFieldValue(FType.author, "Author");
        book.setFieldValue(FType.title, "Title");
        book.setFieldValue(FType.publisher, "Publisher: Publisher...");
        book.setFieldValue(FType.year, "2013");
        String expValue = "@book{book_11,\n    author = {Author},\n    " +
                "title = {Title},\n    " +
                "publisher = {Publisher: Publisher...},\n    " +
                "year = {2013}\n}";
        // System.out.println(expValue);
        //System.out.println(convert.toBibTex(article));
        assertEquals(expValue, convert.toBibTex(book));
    }
    @Test
    public void testToBibTexForSpecSharsArticle(){
        Reference article = new Article();
        article.setFieldValue(FType.referenceId, "arti_11");
        article.setFieldValue(FType.author, "Author");
        article.setFieldValue(FType.title, "TiÅåtÖleÄ");
        article.setFieldValue(FType.journal, "Journal...pöpää");
        article.setFieldValue(FType.year, "2013");
        String expValue = "@article{arti_11,\n    author = {Author},\n    " +
                "title = {Ti\\\"AA\\\"aat\\\"{O}le\\\"{A}},\n    " +
                "journal = {Journal...p\\\"{o}p\\\"{a}\\\"{a}},\n    " +
                "year = {2013}\n}";
        System.out.println(expValue);
        System.out.println(convert.toBibTex(article));
        assertEquals(expValue, convert.toBibTex(article));
    }

}