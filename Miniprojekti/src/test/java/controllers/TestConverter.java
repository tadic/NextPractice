package controllers;

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

}