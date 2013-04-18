/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nsikkila
 */
public class ReferenceFactoryTest {
    
    ReferenceFactory fac = new ReferenceFactory();
    
    public ReferenceFactoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

   @Test
   public void getClassOfInproceedings() {
       Class result = fac.getClassOfype("inproceedings");
       Class exp = Inproceedings.class;
       assertEquals(result, exp);
   }

}
