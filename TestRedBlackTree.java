// --== CS400 File Header Information ==--
// Name: Ananya Heroor
// Email: heroor@wisc.edu
// Team: LE
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: N/A
import org.junit.Test;
import static org.junit.Assert.*;

public class TestRedBlackTree {
  
  
  @Test
  /**
   * Test Case 1: ensure that when the parent's sibling is red, 
   * the RBT method successfully recolors the tree
   * The new node's parent and that parent's sibling initially will be red
   * Both the parent and sibling should be recolored to black
   * The root of the tree should be recolored to red (keeping in 
   * mind how this would propagate up the tree if there were more nodes above
   */
  public void testParentSiblingRed() {
    RedBlackTree <Integer> t1 = new RedBlackTree<Integer>(); //create a new Red Black Tree
    t1.insert(46); //insert nodes into the tree (this is the root)
    t1.insert(27); //This is the parent of the new node
    t1.insert(73); //This is the parent's sibling
    t1.insert(19); //This is the new node that we are inserting
    System.out.println("t1: "+t1.toString()); //Check to see that the nodes have been successfully entered in the right order
    if(t1.root.isBlack && t1.root.leftChild.isBlack && t1.root.rightChild.isBlack) { //Conditions: the recolored tree should be these colors
      //
    } else {
      fail("Colors are wrong"); //If the recolored tree doesn't match these conditions, then there is an issue with our recoloring
    }
    if(t1.root.data == 46 && t1.root.leftChild.data == 27 && t1.root.rightChild.data == 73) //Conditions: the tree should contain these values at their specified locations
    {
      // pass
    } else {
      fail("Numbers are wrong"); //If the new tree doesn't match these values, then there is an issue with our RBT class
    }
    
    
    
  }
  
  @Test
  /**
   * Test Case 2: When the parent of the new node's sibling is black and on
   * the opposite side of the new node
   * First the method should rotate the parent of the new node and the root
   * Then, the method should recolor the tree
   * Ensure that the same black height is on the subtrees as the initial tree
   */
  public void testParentSiblingBlackOpposite() {
    RedBlackTree <Integer> t2 = new RedBlackTree<Integer>(); //Create a new RBT
    t2.insert(45); //Insert nodes into the tree
    t2.insert(26);
    t2.insert(72);
    t2.insert(18);
    t2.insert(31);
    t2.insert(68);
    t2.insert(91);
    t2.insert(13);
    t2.insert(20);
    t2.insert(28);
    t2.insert(88);
    t2.insert(19);
    t2.insert(21);
    System.out.println("t2: "+t2.toString()); //Check to see that the nodes have been successfully entered in the right order
    if(t2.root.isBlack && t2.root.leftChild.isBlack && t2.root.rightChild.isBlack && t2.root.rightChild.rightChild.isBlack && !t2.root.leftChild.leftChild.isBlack) {
    //Conditions above: the recolored tree should be these colors
      //pass
    } else {
      fail("Colors are wrong"); //If the recolored tree doesn't match these conditions, then there is an issue with our recoloring
    }
    if(t2.root.data == 45 && t2.root.leftChild.data == 26 && t2.root.rightChild.data == 72 && t2.root.rightChild.rightChild.data == 91 && t2.root.leftChild.leftChild.rightChild.leftChild.data == 19) {
      //Conditions above: the tree should contain these values at their specified locations
      //pass
    } else{
     fail ("Numbers are wrong"); //If the new tree doesn't match these values, then there is an issue with our RBT class
    }
  }
  
  @Test
  /**
   * Test Case 3: When the parent of the new node's sibling is black and on
   * the same side of the new node
   * First the method should rotate the parent of the new node and the root
   * Then the resulting tree will fit a case 2 violation
   * follow the case 2 rotation
   * Then, the method should recolor the tree
   * Ensure that the same black height is on the subtrees as the initial tree
   */
  public void testParentSiblingBlackSame() {
    RedBlackTree <Integer> t3 = new RedBlackTree<Integer>(); //Create a new RBT
    t3.insert(45); //Insert nodes into the tree
    t3.insert(26);
    t3.insert(72);
    t3.insert(18);
    t3.insert(31);
    t3.insert(68);
    t3.insert(91);
    t3.insert(13);
    t3.insert(20);
    t3.insert(28);
    t3.insert(88);
    t3.insert(19);
    t3.insert(21);
    t3.insert(22);
    System.out.println("t3: "+t3.toString()); //Check to see that the nodes have been successfully entered in the right order
    if(t3.root.isBlack && t3.root.leftChild.isBlack && t3.root.rightChild.isBlack && t3.root.rightChild.rightChild.isBlack && !t3.root.leftChild.leftChild.isBlack) {
      //Conditions above: the recolored tree should be these colors
      //pass
    } else {
      fail("Colors are wrong"); //If the recolored tree doesn't match these conditions, then there is an issue with our recoloring
    }
    if(t3.root.data == 45 && t3.root.leftChild.data == 20 && t3.root.rightChild.data == 72 && t3.root.rightChild.rightChild.data == 91 && t3.root.leftChild.leftChild.data == 18) {
    //Conditions above: the tree should contain these values at their specified locations
      //pass
    } else{
     fail ("Numbers are wrong"); //If the new tree doesn't match these values, then there is an issue with our RBT class
    }
  }
  
  @Test
  /**
   * Test case when the grandparent, parent and parent's sibling is red
   * This should result in two rotations and a recoloring
   * ensure that it is balanced
   */
  public void testGrandParentRedParentSiblingRed() {
    RedBlackTree <Integer> t4 = new RedBlackTree<Integer>(); //Create a new RBT
    t4.insert(55); //Insert nodes into the tree
    t4.insert(50);
    t4.insert(60);
    t4.insert(46);
    t4.insert(27);
    t4.insert(48);
    t4.insert(19);
    System.out.println("t4: "+t4.toString()); //Check to see that the nodes have been successfully entered in the right order
    if(t4.root.isBlack && !t4.root.leftChild.isBlack && t4.root.rightChild.isBlack && t4.root.leftChild.leftChild.isBlack && t4.root.leftChild.rightChild.isBlack ) {
    //Conditions above: the recolored tree should be these colors
      //pass
    } else {
      fail("Colors are wrong"); //If the recolored tree doesn't match these conditions, then there is an issue with our recoloring
    }
    if(t4.root.data == 55 && t4.root.leftChild.data == 46 && t4.root.rightChild.data == 60 && t4.root.leftChild.leftChild.data == 27) {
    //Conditions above: the tree should contain these values at their specified locations
      //pass
    } else{
     fail ("Numbers are wrong"); //If the new tree doesn't match these values, then there is an issue with our RBT class
    }
  }

}
