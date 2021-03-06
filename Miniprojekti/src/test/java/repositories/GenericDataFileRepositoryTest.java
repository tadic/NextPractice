/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entity.Book;
import entity.FType;
import entity.Field;
import entity.Reference;
import entity.Inproceedings;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author jarno
 */
public class GenericDataFileRepositoryTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private File dataFile;

    private void populateTestData(GenericRepository repository) {
        for (int i = 1; i <= 50; i++) {
            Inproceedings inproceedings = new Inproceedings();
            inproceedings.setFieldValue(FType.author, "Author" + i);
            inproceedings.setFieldValue(FType.booktitle, "Booktitle" + i);
            inproceedings.setFieldValue(FType.title, "Title" + i);
            inproceedings.setFieldValue(FType.author.year, "" + (2000 + i));
            repository.create(inproceedings);
        }
    }

    @Test
    public void testGetInstance() throws Exception {
        GenericRepository result = GenericDataFileRepository.getInstance();
        assertNotNull(result);
    }

    @Test
    public void testSaveDataToFile() throws Exception {
        GenericRepository instance = GenericDataFileRepository.getInstance();
        instance.clearAll();
        dataFile = temporaryFolder.newFile("Miniprojekti.dat");
        assertTrue(dataFile.exists());

        Inproceedings inproceedings1 = new Inproceedings();
        inproceedings1.setFieldValue(FType.author, "Stephen King");
        inproceedings1.setFieldValue(FType.title, "Danse Macabre");
        inproceedings1 = instance.create(inproceedings1);

        Inproceedings inproceedings2 = new Inproceedings();
        inproceedings2.setFieldValue(FType.author, "Stephen King");
        inproceedings2.setFieldValue(FType.title, "Riding the Bullet");
        instance.create(inproceedings2);

        instance.saveDataToFile(dataFile);
    }

    @Test
    public void testLoadDataFromFile() throws Exception {
        GenericRepository instance = GenericDataFileRepository.getInstance();
        instance.clearAll();
        dataFile = temporaryFolder.newFile("Miniprojekti.dat");

        Inproceedings inproceedings1 = new Inproceedings();
        inproceedings1.setFieldValue(FType.author, "Stephen King");
        inproceedings1.setFieldValue(FType.title, "Danse Macabre");
        inproceedings1 = instance.create(inproceedings1);
        int id = inproceedings1.getId();

        Inproceedings inproceedings2 = new Inproceedings();
        inproceedings2.setFieldValue(FType.author, "Stephen King");
        inproceedings2.setFieldValue(FType.title, "Riding the Bullet");
        instance.create(inproceedings2);

        instance.saveDataToFile(dataFile);

        instance.clearAll();

        instance.loadDataFromFile(dataFile);

        assertTrue(instance.count(Inproceedings.class) == 2);

        inproceedings1 = instance.findOne(Inproceedings.class, id);

        assertEquals(id, inproceedings1.getId());
        assertEquals("Stephen King", inproceedings1.getFieldValue(FType.author));
        assertEquals("Danse Macabre", inproceedings1.getFieldValue(FType.title));

    }

    @Test
    public void testCount() throws Exception {
        GenericRepository instance = GenericDataFileRepository.getInstance();
        instance.clearAll();
        int expResult = 0;
        int result = instance.count(Inproceedings.class);
        assertEquals(expResult, result);
        populateTestData(instance);
        expResult = 50;
        result = instance.count(Inproceedings.class);
        assertEquals(expResult, result);
    }

    @Test
    public void testFindAllReferences() throws Exception {
        GenericRepository instance = GenericDataFileRepository.getInstance();
        instance.clearAll();

        Inproceedings inproceedings = new Inproceedings();
        inproceedings.setFieldValue(FType.author, "Author1");
        instance.create(inproceedings);
        
        Book book = new Book();
        book.setFieldValue(FType.title, "book");
        instance.create(book);
        
        List<Reference> result = instance.findAll();
        assertEquals(2, result.size());
        assertEquals("Author1", result.get(0).getFieldValue(FType.author));
        assertEquals("book", result.get(1).getFieldValue(FType.title));
    }

    @Test
    public void testFindAllInproceedings() throws Exception {
        GenericRepository instance = GenericDataFileRepository.getInstance();
        instance.clearAll();
        List<Inproceedings> expResult = new ArrayList<Inproceedings>();

        for (int i = 1; i <= 5; i++) {
            Inproceedings inproceedings = new Inproceedings();
            inproceedings.setFieldValue(FType.author, "Author" + i);
            inproceedings.setFieldValue(FType.booktitle, "Booktitle" + i);
            inproceedings.setFieldValue(FType.title, "Title" + i);
            inproceedings.setFieldValue(FType.year, "" + (2000 + i));
            instance.create(inproceedings);
            expResult.add(inproceedings);
        }

        List<Inproceedings> result = instance.findAll(Inproceedings.class);
        assertEquals("Author1", result.get(0).getFieldValue(FType.author));
        assertEquals("Author2", result.get(1).getFieldValue(FType.author));
        assertEquals("Author3", result.get(2).getFieldValue(FType.author));
        assertEquals("Author4", result.get(3).getFieldValue(FType.author));
        assertEquals("Author5", result.get(4).getFieldValue(FType.author));

    }

    @Test
    public void testFindAllShouldReturnEmptyList() throws Exception {
        GenericRepository instance = GenericDataFileRepository.getInstance();
        instance.clearAll();

        List<Inproceedings> result = instance.findAll(Inproceedings.class);
        assertEquals(0, result.size());
    }

    @Test
    public void testFindByAuthor() throws Exception {
        GenericRepository instance = GenericDataFileRepository.getInstance();
        instance.clearAll();

        populateTestData(instance);

        List<Inproceedings> expResult = new ArrayList<Inproceedings>();

        Inproceedings inproceedings1 = new Inproceedings();
        inproceedings1.setFieldValue(FType.author, "Stephen King");
        inproceedings1.setFieldValue(FType.title, "Danse Macabre");
        instance.create(inproceedings1);
        expResult.add(inproceedings1);

        Inproceedings inproceedings2 = new Inproceedings();
        inproceedings2.setFieldValue(FType.author, "Stephen King");
        inproceedings2.setFieldValue(FType.title, "Riding the Bullet");
        instance.create(inproceedings2);
        expResult.add(inproceedings2);

        List result = instance.findByField(Inproceedings.class, FType.author, "Stephen King");

        assertTrue(result.size() == 2);
        assertTrue(result.contains(inproceedings1));
        assertTrue(result.contains(inproceedings2));
    }

    @Test
    public void testFindByAuthorShouldReturnEmptyList() throws Exception {
        GenericRepository instance = GenericDataFileRepository.getInstance();
        instance.clearAll();
        populateTestData(instance);
        List result = instance.findByField(Inproceedings.class, FType.author, "Stephen King");
        assertEquals(0, result.size());
    }

    @Test
    public void testFindOne() throws Exception {
        GenericRepository instance = GenericDataFileRepository.getInstance();
        instance.clearAll();

        Inproceedings inproceedings1 = new Inproceedings();
        inproceedings1.setFieldValue(FType.author, "Stephen King");
        inproceedings1.setFieldValue(FType.title, "Danse Macabre");
        inproceedings1 = instance.create(inproceedings1);
        int id = inproceedings1.getId();

        Inproceedings found = instance.findOne(Inproceedings.class, id);
        assertEquals(id, found.getId());
        assertEquals("Stephen King", found.getFieldValue(FType.author));
        assertEquals("Danse Macabre", found.getFieldValue(FType.title));
    }

    @Test
    public void testFindOneShouldReturnNull() throws Exception {
        GenericRepository instance = GenericDataFileRepository.getInstance();
        instance.clearAll();
        Inproceedings found = instance.findOne(Inproceedings.class, 200);
        assertNull(found);
    }

    @Test
    public void testCreate() throws Exception {
        GenericRepository instance = GenericDataFileRepository.getInstance();
        instance.clearAll();

        Inproceedings inproceedings1 = new Inproceedings();
        inproceedings1.setFieldValue(FType.author, "Stephen King");
        inproceedings1.setFieldValue(FType.title, "Danse Macabre");
        inproceedings1 = instance.create(inproceedings1);
        int id = inproceedings1.getId();

        assertEquals(1, id);
        assertEquals(1, instance.count(Inproceedings.class));

        Inproceedings inproceedings2 = new Inproceedings();
        inproceedings2.setFieldValue(FType.author, "Stephen King");
        inproceedings2.setFieldValue(FType.title, "Riding the Bullet");
        instance.create(inproceedings2);

        assertEquals(2, inproceedings2.getId());
        assertEquals(2, instance.count(Inproceedings.class));

        Inproceedings found = instance.findOne(Inproceedings.class, id);
        assertEquals(id, found.getId());
        assertEquals("Stephen King", found.getFieldValue(FType.author));
        assertEquals("Danse Macabre", found.getFieldValue(FType.title));
    }

    @Test
    public void testUpdate() throws Exception {
        GenericRepository instance = GenericDataFileRepository.getInstance();
        instance.clearAll();

        Inproceedings inproceedings1 = new Inproceedings();
        inproceedings1.setFieldValue(FType.author, "Stephen King");
        inproceedings1.setFieldValue(FType.title, "Danse Macabre");
        inproceedings1 = instance.create(inproceedings1);
        int id = inproceedings1.getId();

        Inproceedings inproceedings2 = new Inproceedings();
        inproceedings2.setFieldValue(FType.author, "Stephen King");
        inproceedings2.setFieldValue(FType.title, "Riding the Bullet");
        instance.create(inproceedings2);

        inproceedings1.setFieldValue(FType.author, "Jari Tervo");
        instance.update(id, inproceedings1);
        inproceedings1 = instance.findOne(Inproceedings.class, id);

        assertEquals("Jari Tervo", inproceedings1.getFieldValue(FType.author));
        assertEquals("Danse Macabre", inproceedings1.getFieldValue(FType.title));
        assertTrue(instance.count(Inproceedings.class) == 2);
    }

    @Test
    public void testDelete() throws Exception {
        GenericRepository instance = GenericDataFileRepository.getInstance();
        instance.clearAll();

        Inproceedings inproceedings1 = new Inproceedings();
        inproceedings1.setFieldValue(FType.author, "Stephen King");
        inproceedings1.setFieldValue(FType.title, "Danse Macabre");
        inproceedings1 = instance.create(inproceedings1);
        int id = inproceedings1.getId();

        Inproceedings inproceedings2 = new Inproceedings();
        inproceedings2.setFieldValue(FType.author, "Stephen King");
        inproceedings2.setFieldValue(FType.title, "Riding the Bullet");
        instance.create(inproceedings2);

        int countStart = instance.count(Inproceedings.class);

        instance.delete(id);

        int countEnd = instance.count(Inproceedings.class);

        assertTrue(countEnd == countStart - 1);
    }
}