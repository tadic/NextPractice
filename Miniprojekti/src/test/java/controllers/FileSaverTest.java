/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Inproceedings;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mikahutt
 */
public class FileSaverTest {

    private FileSaver saver;
    private Converter converter;
    private Inproceedings test1;
    private Inproceedings test2;
    private File testFile1;
    private File testFile2;
    private Scanner scanner1;
    private Scanner scanner2;

    public FileSaverTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        converter = new Converter();
        saver = new FileSaver(converter);
        test1 = new Inproceedings();
        test2 = new Inproceedings();
        setInproceeding1(test1);
        setInproceeding2(test2);

        saver.saveToFile("testi1.txt", test1);
        testFile1 = new File("testi1.txt");

        saver.saveToFile("testi2.txt", test2);
        testFile2 = new File("testi2.txt");
        
        scanner1 = new Scanner(testFile1);
        scanner2 = new Scanner(testFile2);
        
    }

    @After
    public void tearDown() {
        testFile1.delete();
        testFile2.delete();
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void fileGetsSaved() throws IOException {
        assertTrue(testFile1.exists());
        assertTrue(testFile2.exists());
    }

    @Test
    public void fileContainsSomething() {
        assertTrue(testFile1.length() != 0);
        assertTrue(testFile2.length() != 0);
    }
    
    @Test
    public void firstLineGetsSavedRight() throws IOException {
        String firstLineInFile1 = scanner1.nextLine();
        String firstLineInFile2 = scanner2.nextLine();
        
        assertTrue(converter.toBibTex(test1).startsWith(firstLineInFile1));
        assertTrue(converter.toBibTex(test2).startsWith(firstLineInFile2));
    }
    
    
    @Test
    public void fileContainsEverything() {
        String contentOfFile1 = "";
        while (scanner1.hasNext()){
            contentOfFile1 += scanner1.nextLine();
        }
        // Removes all white space
        assertEquals(converter.toBibTex(test1).replaceAll("\\s", ""),contentOfFile1.replaceAll("\\s",""));
    }

    private void setInproceeding1(Inproceedings test1) {
        test1.setAuthor("Matti Luukkainen");
        test1.setBooktitle("Why other boys won't play with me");
        test1.setTitle("What does this mean");
        test1.setYear(1993);
    }

    private void setInproceeding2(Inproceedings test2) {
        test2.setAuthor("Arto Vihavainen");
        test2.setBooktitle("Adding sugar to my friends coffee and 100 other idiotic habbits of mine");
        test2.setTitle("I don't understand this either");
        test2.setYear(2013);
    }
}
