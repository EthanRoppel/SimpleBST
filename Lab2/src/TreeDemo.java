import java.util.ArrayList;
import java.util.Stack;

//Ethan Roppel 41753979
        /*
        I pledge that this submission is solely my work,
        and that I have neither given to nor
        received help from anyone other than the instructor or TAs.
         */

class Node{
   int value;
   Node left, right;

   /**
    * Creates a node with the given value.
    *
    * @param value The value of the node.
    */
   public Node(int value){
      this.value = value;
      left = null;
      right = null;
   }

}


class BinarySearchTree{

   Node root;


   /**
    * Inserts a value into the given tree, maintaining the BST property.
    *
    * @param root The top of the bst being processed.
    * @param value The value being added to the bst.
    * @return The top of the new bst
    */
   public Node insert(Node root, int value){
      //base case
      if(root == null){
         root = new Node(value);
         return root;
      }
      
      //recursive step
      if(value < root.value){
         root.left = insert(root.left, value); 
      }else{
         root.right = insert(root.right, value);
      }
      
      return root;
   }


   /**
    * A method which traverses the input tree in pre order and outputs it to the console.
    *
    * @param root the top of the bst being processed.
    */
   public void preOrderTraversal(Node root){
      if (root == null) {
         System.out.println("");
      };

      Stack<Node> stack = new Stack<Node>();
      ArrayList<Integer> orderList = new ArrayList<Integer>();

      for (Node node = root;;)
      {
         if (node == null)
         {
            if (stack.empty()) break;

            node = stack.pop();
            node = node.right;
         }
         else
         {
            orderList.add(node.value);
            stack.push(node);
            node = node.left;
         }
      }

      int[] order = new int[orderList.size()];
      for (int i = 0; i < order.length; i++)
      {
         System.out.print(orderList.get(i) + " ");
      }

   }


   /**
    * A method which traverses the input tree in order and outputs it to the console.
    *
    * @param root the top of the bst being processed.
    */
   public void inOrderTraversal(Node root){
      if (root == null) {
         System.out.println("");
      };

      Stack<Node> stack = new Stack<Node>();
      ArrayList<Integer> orderList = new ArrayList<Integer>();

      for (Node node = root;;)
      {
         if (node == null)
         {
            if (stack.empty()) break;

            node = stack.pop();
            orderList.add(node.value);
            node = node.right;
         }
         else
         {
            stack.push(node);
            node = node.left;
         }
      }

      int[] order = new int[orderList.size()];
      for (int i = 0; i < order.length; i++)
      {
         System.out.print(orderList.get(i) + " ");
      }

   }


   /**
    * A method which traverses the input tree in post order and outputs it to the console.
    *
    * @param root the top of the bst being processed.
    */
   public void postOrderTraversal(Node root){
      if (root == null) {
         System.out.println("");
      };

      Stack<Node> stack = new Stack<Node>();
      Stack<Integer> stackCtr = new Stack<Integer>();
      ArrayList<Integer> orderList = new ArrayList<Integer>();

      stack.push(root);
      stackCtr.push(0);

      while (!stack.empty())
      {
         int ctr = stackCtr.pop();
         Node node = stack.peek();

         if (ctr == 0)
         {
            // First visit.
            stackCtr.push(1);

            if (node.left != null)
            {
               stack.push(node.left);
               stackCtr.push(0);
            }
         }
         else if (ctr == 1)
         {
            // Second visit.
            // Left subtree done.
            stackCtr.push(2);

            if (node.right != null)
            {
               stack.push(node.right);
               stackCtr.push(0);
            }
         }
         else // ctr >= 2
         {
            // Third visit.
            // Right subtree done.
            stack.pop();
            orderList.add(node.value);
         }
      }

      int[] order = new int[orderList.size()];
      for (int i = 0; i < order.length; i++)
      {
         System.out.print(orderList.get(i) + " ");
      }
   }


   /**
    * A method which determines if a tree contains a value.
    *
    * @param root the top of the bst being processed.
    * @param key the value that may be in the tree.
    * @return true if value is in tree, false if not found.
    */
   public boolean find(Node root, int key){
      if(root == null)
      {
         return false;
      }

      if(root.value == key)
      {
         return true;
      }

      if(key < root.value)
      {
         return find(root.left, key);
      }
      else
      {
         return find(root.right, key);
      }
   }


   /**
    * A method which finds the node with the smallest value in a tree.
    *
    * @param root the top of the bst being processed.
    * @return the value of the smallest entry in the bst.
    */
   public int getMin(Node root) {
      if (root == null) {
         System.out.println("The tree is empty.");
      } else if (root.left == null) {
         return root.value;
      } else {
         return getMin(root.left);
      }
      return 0;
   }



   /**
    * A method which finds the node with the largest value in a tree.
    *
    * @param root the top of the bst being processed.
    * @return the value of the largest entry in the bst.
    */
   public int getMax(Node root){
      if (root == null) {
         System.out.println("The tree is empty.");
      } else if (root.right == null) {
         return root.value;
      } else {
         return getMax(root.right);
      }
      return 0;
   }


   /**
    * Deletes a node from a BST and balances the tree.
    *
    * @param root the top of the bst being processed.
    * @param key the value of the node that needs to be deleted.
    * @return the new bst.
    */
   public Node delete(Node root, int key){
      
      if(root == null){
         return root;
      }else if(key < root.value){
         root.left = delete(root.left, key);
      }else if(key > root.value){
         root.right = delete(root.right, key);
      }else{
         //node has been found
         if(root.left==null && root.right==null){
            //case #1: leaf node
            root = null;
         }else if(root.right == null){
            //case #2 : only left child
            root = root.left;
         }else if(root.left == null){
            //case #2 : only right child
            root = root.right;
         }else{
            //case #3 : 2 children
            root.value = getMax(root.left);
            root.left = delete(root.left, root.value);
         }
      }
      return root;  
   }
   
   
   
}



public class TreeDemo{

   /**
    * The main method which inserts somne values into a bst and tests each function.
    * @param args command line arguments
    */
   public static void main(String[] args){
      BinarySearchTree theBST  = new BinarySearchTree();
      theBST.root = theBST.insert(theBST.root, 24);
      theBST.root = theBST.insert(theBST.root, 80);
      theBST.root = theBST.insert(theBST.root, 18);
      theBST.root = theBST.insert(theBST.root, 9);
      theBST.root = theBST.insert(theBST.root, 90);
      theBST.root = theBST.insert(theBST.root, 22);
            
      System.out.print("in-order :   ");
      theBST.inOrderTraversal(theBST.root);
      System.out.println();

      System.out.print("pre-order :   ");
      theBST.preOrderTraversal(theBST.root);
      System.out.println();

      System.out.print("post-order :   ");
      theBST.postOrderTraversal(theBST.root);
      System.out.println();

      System.out.println("It is " + theBST.find(theBST.root, 8) + " that the tree contains the value 8");

      System.out.println("It is " + theBST.find(theBST.root, 9) + " that the tree contains the value 9");

      System.out.println("The minimum value is:" + theBST.getMin(theBST.root));

      System.out.println("The maximum value is:" + theBST.getMax(theBST.root));

   }  
}