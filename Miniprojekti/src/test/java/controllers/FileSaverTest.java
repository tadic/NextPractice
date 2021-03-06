/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.FType;
import entity.Inproceedings;
import entity.Reference;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    private File testFile3;
    private Scanner scanner1;
    private Scanner scanner2;
    private Scanner scanner3;
    private List<Reference> list;
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
        list = new ArrayList<Reference>();

        setInproceeding1(test1);
        setInproceeding2(test2);

        list.add(test1);
        list.add(test2);
        saver.saveToFile("testi1.txt", test1);
        testFile1 = new File("testi1.txt");

        saver.saveToFile("testi2.txt", test2);
        testFile2 = new File("testi2.txt");
        
        saver.saveToFile("testi3.txt", list);
        testFile3 = new File("testi3.txt");
        
        scanner1 = new Scanner(testFile1);
        scanner2 = new Scanner(testFile2);
        scanner3 = new Scanner(testFile3);
        
    }

    @After
    public void tearDown() {
        testFile1.delete();
        testFile2.delete();
        testFile3.delete();
    }


    @Test
    public void fileGetsSaved() throws IOException {
        assertTrue(testFile1.exists());
        assertTrue(testFile2.exists());
        assertTrue(testFile3.exists());
    }

    @Test
    public void fileContainsSomething() {
        assertTrue(testFile1.length() != 0);
        assertTrue(testFile2.length() != 0);
        assertTrue(testFile3.length() != 0);
    }
    
    @Test
    public void firstLineGetsSavedRight() throws IOException {
        String firstLineInFile1 = scanner1.nextLine();
        String firstLineInFile2 = scanner2.nextLine();
        String firstLineInFile3 = scanner3.nextLine();
        
        assertTrue(converter.toBibTex(test1).startsWith(firstLineInFile1));
        assertTrue(converter.toBibTex(test2).startsWith(firstLineInFile2));
        assertTrue(converter.toBibTex(list.get(0)).startsWith(firstLineInFile3));
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
    
    @Test
    public void fileContainsEverything_List() {
        String contentOfFile = "";
        while (scanner3.hasNext()){
            contentOfFile += scanner3.nextLine();
        }
        // Removes all white space
        String expected = converter.toBibTex(list.get(0)) + converter.toBibTex(list.get(1));
        assertEquals(expected.replaceAll("\\s", ""),contentOfFile.replaceAll("\\s",""));
    }
    
    

    private void setInproceeding1(Inproceedings test1) {
        test1.setFieldValue(FType.author, "Matti Luukkainen");
        test1.setFieldValue(FType.booktitle,"Why other boys won't play with me");
        test1.setFieldValue(FType.title, "What does this mean");
        test1.setFieldValue(FType.year, "1993");
    }

    private void setInproceeding2(Inproceedings test2) {
        test2.setFieldValue(FType.author, "Arto Vihavainen");
        test2.setFieldValue(FType.booktitle,"Adding sugar to my friends coffee and 100 other idiotic habbits of mine");
        test2.setFieldValue(FType.title, "I don't understand this either");
        test2.setFieldValue(FType.year, "2013");
    }
}
