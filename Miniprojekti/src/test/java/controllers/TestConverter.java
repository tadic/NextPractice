package controllers;

import controllers.Converter;
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
    }
    
    @Test
    public void toBibTexTestNormal(){
        inpro.setAuthor("Roumani, Hamzeh"); 
        inpro.setTitle("Design guidelines for the lab component of objects-first CS1");
        inpro.setBooktitle("SIGCSE '02: Proceedings of the 33rd SIGCSE technical symposium on Computer science education" );
        inpro.setYear(2002);
        
        String expValue = "@inproceedings{Rou:2002,\n    author = {Roumani, Hamzeh},\n    " +
                "title = {Design guidelines for the lab component of objects-first CS1},\n    " +
                "booktitle = {SIGCSE '02: Proceedings of the 33rd SIGCSE technical symposium on Computer science education},\n    " +
                "year = {2002}\n}";
        
        System.out.println(expValue);
        System.out.println(convert.toBibTex(inpro));
        assertEquals(expValue, convert.toBibTex(inpro));
    }
    
    
    @Test
    public void toBibTexTestSpecCharacters(){
        inpro.setAuthor("Hassinen, Marko and Mäyrä, Hannu"); 
        inpro.setTitle("Learning programming by programming: a case study");
        inpro.setBooktitle("Baltic Sea '06: Proceedings of the 6th Baltic Sea conference on Computing education research: Koli Calling 2006");
        inpro.setYear(2006);
        
        String expValue;
        expValue = "@inproceedings{Has:2006,\n    author = {Hassinen, Marko and M\\\"{a}yr\\\"{a}, Hannu},\n    " +
         "title = {Learning programming by programming: a case study},\n    " +
         "booktitle = {Baltic Sea '06: Proceedings of the 6th Baltic Sea conference on Computing education research: Koli Calling 2006},\n    " +
         "year = {2006}\n}";
        //System.out.println(expValue);
        //System.out.println(convert.toBibTex(inpro));
        assertEquals(expValue, convert.toBibTex(inpro));
    }
    
}
