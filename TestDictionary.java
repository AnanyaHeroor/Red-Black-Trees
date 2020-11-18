// --== CS400 File Header Information ==--
// Name: Ananya Heroor
// Email: heroor@wisc.edu
// Team: LE
// Role: Test Engineer 1
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: JUnit Tests for Data Wrangler 1 (InitialWordTree, Word, word.txt), Front End 1 (DictModifier.java) and Back End 1's (RBTtoGUI) classes
import org.junit.Test;
import static org.junit.Assert.*;
//import Word;
/**
 * This class contains the JUnit tests for our RBT Dictionary Application
 * This specifically tests Data Wrangler 1, Front End 1, and Back End 1's classes
 * @author ananyaheroor
 *
 */
public class TestDictionary {
  
  @Test
  /**
   * This method tests Data Wrangler 1's InitialWordTree class
   * This is testing if it can read the file specifically the importingTree method
   */
  public void testFileRead() {
    InitialWordTree test = new InitialWordTree(); //new test 
    if (!test.importingTree()) { //if the test is not imported correctly
      fail("Couldn't read the file, error in importingTree method"); //Then the file was not loaded and read properly
    }
  }
  
  @Test
  /**
   * This method tests Data Wrangler 1's InitialWordTree class
   * This is testing if it can write the file specifically the exportingTree method
   */
  public void testFileWrite() {
    InitialWordTree test = new InitialWordTree(); //new test
    test.importingTree(); //import the file
    test.changeFileDestination("abc.txt"); //create a new file to rewrite the old one
    if(!test.exportingTree()) { //if the test was not imported correctly
      fail("Couldn't write the file, error in exportingTree method"); //then the file was not properly overwritten
    }
  }
  
 @Test
  /**
   * This method tests the Back End Developer 1's RBTtoGUI class
   * This specifically tests the add and size methods
   */
  public void testSize() {
    RBTtoGUI t1 = new RBTtoGUI(); //create new dictionary
    t1.add("LE", new Word ("cat", "small feline")); //add new words and their definition to the dictionary
    t1.add("LE", new Word ("dog", "small canine"));
    t1.add("LE", new Word("fish", "water vertebrate"));
    //size should be 3
    if (t1.size() != 3) { //if size does not equal 3 then the size method doesn't work
      fail("Returned incorrect size of the tree");
    }
  }
  
 @Test
 /**
  * This method tests the Back End Developer 1's RBTtoGUI class
  * This specifically tests the search method
  */
  public void testSearch() {
   RBTtoGUI t2 = new RBTtoGUI(); //create new tree
   t2.add("LE", new Word ("cat", "small feline")); //add new words and their definition to the dictionary
   t2.add("LE", new Word ("dog", "small canine"));
   t2.add("LE", new Word("fish", "water vertebrate"));
   if(!t2.search("cat").equals("small feline") && 
       !t2.search("dog").equals("small canine") && 
       !t2.search("fish").equals("water vertebrate")) { //using search, the dictionary should contain the words and definitions that were just added
     fail("Search method did not correctly get the tree"); //If search doesn't return what was added, it doesn't work
   }
 }
 @Test
 /**
  * This method tests the Back End Developer 1's RBTtoGUI class
  * This specifically tests the getTree method
  */
  public void testGet() {
   RBTtoGUI t5 = new RBTtoGUI(); //create new tree
   t5.add("LE", new Word ("cat", "small feline")); //add new words and their definition to the dictionary
   t5.add("LE", new Word ("dog", "small canine"));
   t5.add("LE", new Word("fish", "water vertebrate"));
   //check that the tree contains the words added
   if(!t5.getTree().toString().contains("cat") && !t5.getTree().toString().contains("dog") && !t5.getTree().toString().contains("fish")) {
     fail("getTree did not correctly return the tree"); //If getTree doesn't return updated tree, it doesn't work
   }
   
 }
 
  @Test
  /**
   * This method tests the Back End Developer 1's RBTtoGUI class
   * This specifically tests the clear method
   */
  public void testClear() {
    RBTtoGUI t3 = new RBTtoGUI(); //create new tree
    t3.add("LE", new Word ("cat", "small feline")); //add to the new tree with password, word, and definition
    t3.add("LE", new Word ("dog", "small canine"));
    t3.add("LE", new Word("fish", "water vertebrate"));
    t3.clear("LE"); //clear the dictionary by typing the password
   if(t3.size() != 0) { //the size of the dictionary should be 0 when it is cleared
     fail("Tree was not correctly cleared"); //if the size is not 0 then the clear method doesn't work
    }
  }
}
  


