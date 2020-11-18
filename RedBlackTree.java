// --== CS400 File Header Information ==--
// Name: Ananya Heroor
// Email: heroor@wisc.edu
// Team: LE
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: N/A
import java.util.LinkedList;

public class RedBlackTree<T extends Comparable<T>> {
  /**
   * This class represents a node holding a single value within a binary tree the parent, left, and
   * right child references are always be maintained.
   */
  protected static class Node<T> {
    public T data;
    public Node<T> parent; // null for root node
    public Node<T> leftChild;
    public Node<T> rightChild;
    public boolean isBlack;

    public Node(T data) {
      this.data = data;
      isBlack = false; //use this boolean to assign colors to nodes
    }

    /**
     * @return true when this node has a parent and is the left child of that parent, otherwise
     *         return false
     */
    public boolean isLeftChild() {
      return parent != null && parent.leftChild == this;
    }

    /**
     * This method performs a level order traversal of the tree rooted at the current node. The
     * string representations of each data value within this tree are assembled into a comma
     * separated string within brackets (similar to many implementations of java.util.Collection).
     * 
     * @return string containing the values of this tree in level order
     */
    @Override
    public String toString() { // display subtree in order traversal
      String output = "[";
      LinkedList<Node<T>> q = new LinkedList<>();
      q.add(this);
      while (!q.isEmpty()) {
        Node<T> next = q.removeFirst();
        if (next.leftChild != null)
          q.add(next.leftChild);
        if (next.rightChild != null)
          q.add(next.rightChild);
        output += next.data.toString();
        if (!q.isEmpty())
          output += ", ";
      }
      return output + "]";
    }
  }

  protected Node<T> root; // reference to root node of tree, null when empty

  /**
   * Performs a naive insertion into a binary search tree: adding the input data value to a new node
   * in a leaf position within the tree. After this insertion, no attempt is made to restructure or
   * balance the tree. This tree will not hold null references, nor duplicate data values.
   * 
   * @param data to be added into this binary search tree
   * @throws NullPointerException     when the provided data argument is null
   * @throws IllegalArgumentException when the tree already contains data
   */
  public void insert(T data) throws NullPointerException, IllegalArgumentException {
    // null references cannot be stored within this tree
    if (data == null)
      throw new NullPointerException("This RedBlackTree cannot store null references.");
    Node<T> newNode = new Node<>(data);
    if (root == null) {
      root = newNode;
      newNode.isBlack = true;
    } // add first node to an empty tree
    else
      insertHelper(newNode, root); // recursively insert into subtree
  }

  /**
   * Recursive helper method to find the subtree with a null reference in the position that the
   * newNode should be inserted, and then extend this tree by the newNode in that position.
   * 
   * @param newNode is the new node that is being added to this tree
   * @param subtree is the reference to a node within this tree which the newNode should be inserted
   *                as a descendent beneath
   * @throws IllegalArgumentException when the newNode and subtree contain equal data references (as
   *                                  defined by Comparable.compareTo())
   */
  private void insertHelper(Node<T> newNode, Node<T> subtree) {
    int compare = newNode.data.compareTo(subtree.data);
    // do not allow duplicate values to be stored within this tree
    if (compare == 0)
      throw new IllegalArgumentException("This RedBlackTree already contains that value.");
    // store newNode within left subtree of subtree
    else if (compare < 0) {
      if (subtree.leftChild == null) { // left subtree empty, add here
        subtree.leftChild = newNode;
        newNode.parent = subtree;
        enforceRBTreePropertiesAfterInsert(newNode);
        // otherwise continue recursive search for location to insert
      } else
        insertHelper(newNode, subtree.leftChild);
    }
    // store newNode within the right subtree of subtree
    else {
      if (subtree.rightChild == null) { // right subtree empty, add here
        subtree.rightChild = newNode;
        newNode.parent = subtree;
        enforceRBTreePropertiesAfterInsert(newNode); //after adding new red node, ensure that properties are not violated
        // otherwise continue recursive search for location to insert
      } else
        insertHelper(newNode, subtree.rightChild);
    }
  }

  /**
   * This method performs a level order traversal of the tree. The string representations of each
   * data value within this tree are assembled into a comma separated string within brackets
   * (similar to many implementations of java.util.Collection, like java.util.ArrayList, LinkedList,
   * etc).
   * 
   * @return string containing the values of this tree in level order
   */
  @Override
  public String toString() {
    return root.toString();
  }

  /**
   * Performs the rotation operation on the provided nodes within this BST. When the provided child
   * is a leftChild of the provided parent, this * method will perform a right rotation (sometimes
   * called a left-right rotation). When the provided child is a rightChild of the provided parent,
   * this method will perform a left rotation (sometimes called a right-left rotation). When the
   * provided nodes are not related in one of these ways, this method will throw an
   * IllegalArgumentException.
   * 
   * @param child  is the node being rotated from child to parent position (between these two node
   *               arguments)
   * @param parent is the node being rotated from parent to child position (between these two node
   *               arguments)
   * @throws IllegalArgumentException when the provided child and parent node references are not
   *                                  initially (pre-rotation) related that way
   */
  private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
    if (parent == null || child == null) { // if parent or child are null then rotation cannot be
                                           // performed
      throw new IllegalArgumentException("Can't perform rotation on null node");
    }
    if (child.equals(parent.rightChild)) {
      // LEFT-ROTATE
      Node<T> y = parent.rightChild; // save the right child in y
      parent.rightChild = y.leftChild; // set the right child to be left child of y
      if (y.leftChild != null) { // if left child of y isn't null
        y.leftChild.parent = parent; // update its parent
      }
      y.parent = parent.parent; // update y's parent
      if (parent.parent == null) { //if the grandparent is null
        root = y; //assign the root
      } else {
        if (parent.equals(parent.parent.leftChild)) {
          parent.parent.leftChild = y;
        } else {
          parent.parent.rightChild = y;
        }
      }
      y.leftChild = parent; // set y's left child to child
      parent.parent = y; // set child's parent to y
    } else if (child.equals(parent.leftChild)) {
      // RIGHT-ROTATE
      Node<T> y = parent.leftChild; // save the left child in y
      parent.leftChild = y.rightChild; // set left child to be right child of y
      if (y.rightChild != null) { // if right child of y isn't null
        y.rightChild.parent = parent; // update parent of y's right child
      }
      y.parent = parent.parent; // update y's parent
      if (parent.parent == null) {
        root = y;
      } else {
        if (parent.equals(parent.parent.leftChild)) {
          parent.parent.leftChild = y;
        } else {
          parent.parent.rightChild = y;
        }
      }
      y.rightChild = parent; // set y's right child to child
      parent.parent = y; // set child's parent to y
    } else {
      throw new IllegalArgumentException("Child isn't a child of parent.");
    }
  }

  /**
   * takes a reference to a newly added red node as its only parameter resolve red child under red
   * parent red black tree property violations that are introduced by inserting new nodes into a red
   * black tree. While doing so, all other red black tree properties must also be preserved.
   */
  private void enforceRBTreePropertiesAfterInsert(Node<T> newNode) {
    boolean isOnSameSide = false; //boolean to use for case 2 and 3 when checking if node is on same or opposite side of parent
    if (newNode.parent.isBlack && !newNode.isBlack) { //if the parent is black and the new node is red
      // Case 1 adding has not violated red property
      return;
    } else if (!newNode.parent.isBlack) { //if the parent is red
      // Case 1
      Node<T> sibling = null; //assign a node to the parent's sibling
      if (newNode.parent == newNode.parent.parent.leftChild) { //if the parent is on the left
        sibling = newNode.parent.parent.rightChild; //assign the sibling of the parent to be the right child of the grandparent's child
        if (newNode == newNode.parent.leftChild) { //if the new node is on the left side
          isOnSameSide = false; //then the new node is on the opposite side of the parent's sibling
        } else {
          isOnSameSide = true; //otherwise, it is on the same side
        }
      } else {
        sibling = newNode.parent.parent.leftChild; //assign the sibling of the parent to be the left child of the grandparent's child
        if (newNode == newNode.parent.leftChild) { //if the new node is on the left side
          isOnSameSide = true; //then the new node is on the same side of the parent's sibling
        } else {
          isOnSameSide = false; //otherwise, it is on the opposite side
        }
      }
      if (sibling != null && !sibling.isBlack) { //if the sibling isn't null and is red
        newNode.parent.isBlack = true; //make the parent of the new node black
        newNode.parent.parent.isBlack = false; //make the grandparent of the new node red
        sibling.isBlack = true; //make the sibling of the parent of the new node black
        if (newNode.parent.parent.parent != null && !newNode.parent.parent.parent.isBlack) { //if the root isn't null and is red
          enforceRBTreePropertiesAfterInsert(newNode.parent.parent); //enforce to ensure that RBT properties are not violated
        } else if (newNode.parent.parent.parent == null) { //if the node above the root is null
          newNode.parent.parent.isBlack = true; //ensure that the root of the tree is black
        }
      } else if ((sibling == null || sibling.isBlack) && isOnSameSide) { //if the sibling is null, black and on the same side of the new node
        // case 3
        rotate(newNode, newNode.parent); //perform a rotation around the new node and its parent
        // Case 2
        rotate(newNode, newNode.parent); //perform a rotation around the new node and its parent
        newNode.isBlack = true; //make the new node black
        newNode.rightChild.isBlack = false; //make the right child of the new node red
        newNode.leftChild.isBlack = false; //make the left child of the new node black
      } else if ((sibling == null || sibling.isBlack) && !isOnSameSide) { //if the sibling is null, black and on the opposite side
        // case 2
        rotate(newNode.parent, newNode.parent.parent); //rotate the parent of the new node and the grandparent
        newNode.parent.isBlack = true; //make the new node's parent black
        newNode.parent.rightChild.isBlack = false; //make the new node's sibling red
        newNode.parent.leftChild.isBlack = false; //make the new node's sibling red
      }
    }

  }

}

