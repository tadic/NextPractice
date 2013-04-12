package controllers;

import entity.Inproceedings;
import entity.Reference;
import java.util.Calendar;
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
        ref.setFieldValue("author", "Hassinen, Marko and Mäyrä, Hannu"); 
        String expValue = "@inproceedings{Ro:2002,\n    author = {Hassinen, Marko and M\\\"{a}yr\\\"{a}, Hannu},\n    " +
                "title = {Design guidelines for the lab component of objects-first CS1},\n    " +
                "booktitle = {SIGCSE '02: Proceedings of the 33rd SIGCSE technical symposium on Computer science education},\n    " +
                "year = {2002},\n    editor = {one},\n    volume/number = {two},\n    series = {three},\n    " +
                "pages = {four},\n    address = {five},\n    month = {six},\n    organization = {seven},\n    " +
                "publisher = {eight},\n    note = {nine},\n    key = {ten}\n}";
        assertEquals(expValue, convert.toBibTex(ref));
    }

}