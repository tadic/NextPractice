package com.mycompany.miniprojekti;

import controllers.Converter;
import entity.Inproceedings;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author ivantadic
 */
public class TestConverter {
    
    @Test
    public void toBibTexTest(){
        Converter convert = new Converter();
        Inproceedings inpro = new Inproceedings();
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
    
}
