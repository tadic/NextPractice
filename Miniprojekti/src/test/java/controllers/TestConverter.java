package controllers;

import entity.Inproceedings;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author ivantadic
 */
public class TestConverter {
    Converter convert; 
    Inproceedings inpro;
        
    @Before
    public void setUp(){
        convert = new Converter();
        inpro = new Inproceedings();
        inpro.setAuthor("Roumani, Hamzeh"); 
        inpro.setTitle("Design guidelines for the lab component of objects-first CS1");
        inpro.setBooktitle("SIGCSE '02: Proceedings of the 33rd SIGCSE technical symposium on Computer science education" );
        inpro.setYear(2002);
        inpro.setEditor("one");
        inpro.setVolumeNumber("two");
        inpro.setSeries("three");
        inpro.setPages("four");
        inpro.setAddress("five");
        inpro.setMonth("six");
        inpro.setOrganization("seven");
        inpro.setPublisher("eight");
        inpro.setNote("nine");
        inpro.setKey("ten");       
    }
    
    @Test
    public void testToBibTexForNormal(){
        String expValue = "@inproceedings{Ro:2002,\n    author = {Roumani, Hamzeh},\n    " +
                "title = {Design guidelines for the lab component of objects-first CS1},\n    " +
                "booktitle = {SIGCSE '02: Proceedings of the 33rd SIGCSE technical symposium on Computer science education},\n    " +
                "year = {2002},\n    editor = {one},\n    volume/number = {two},\n    series = {three},\n    " +
                "pages = {four},\n    address = {five},\n    month = {six},\n    organization = {seven},\n    " +
                "publisher = {eight},\n    note = {nine},\n    key = {ten}\n}";
        //System.out.println(expValue);
        assertEquals(expValue, convert.toBibTex(inpro));
    }
      
    @Test
    public void testToBibTexForSpecialCharacters(){
        inpro.setAuthor("Hassinen, Marko and Mäyrä, Hannu"); 
        String expValue = "@inproceedings{Ha:2002,\n    author = {Hassinen, Marko and M\\\"{a}yr\\\"{a}, Hannu},\n    " +
                "title = {Design guidelines for the lab component of objects-first CS1},\n    " +
                "booktitle = {SIGCSE '02: Proceedings of the 33rd SIGCSE technical symposium on Computer science education},\n    " +
                "year = {2002},\n    editor = {one},\n    volume/number = {two},\n    series = {three},\n    " +
                "pages = {four},\n    address = {five},\n    month = {six},\n    organization = {seven},\n    " +
                "publisher = {eight},\n    note = {nine},\n    key = {ten}\n}";
        assertEquals(expValue, convert.toBibTex(inpro));
    }
    @Test
    public void testToBibTexForGivenReferenceId(){
        inpro.setReferenceId("reference"); 
        String expValue = "@inproceedings{reference,\n    author = {Roumani, Hamzeh},\n    " +
                "title = {Design guidelines for the lab component of objects-first CS1},\n    " +
                "booktitle = {SIGCSE '02: Proceedings of the 33rd SIGCSE technical symposium on Computer science education},\n    " +
                "year = {2002},\n    editor = {one},\n    volume/number = {two},\n    series = {three},\n    " +
                "pages = {four},\n    address = {five},\n    month = {six},\n    organization = {seven},\n    " +
                "publisher = {eight},\n    note = {nine},\n    key = {ten}\n}";
        assertEquals(expValue, convert.toBibTex(inpro));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBibTexForExceptionWrongYear(){
        inpro.setYear(2014);
        String instance = convert.toBibTex(inpro);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBibTexForExceptionTooShortAuthor(){
        inpro.setAuthor("H"); 
        String instance = convert.toBibTex(inpro);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBibTexForExceptionIfAuthorNull(){
        inpro.setAuthor(null);
        String instance = convert.toBibTex(inpro);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBibTexForExceptionIfReferenceIsNull(){
        String instance = convert.toBibTex(null);
    }
    
    
}
