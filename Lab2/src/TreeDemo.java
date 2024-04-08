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
    * @param root the top of the bst being processed
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
         System.out.println(orderList.get(i));
      }

   }


   /**
    * A method which traverses the input tree in order and outputs it to the console.
    *
    * @param root the top of the bst being processed
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
         System.out.println(orderList.get(i));
      }

   }


   /**
    * A method which traverses the input tree in post order and outputs it to the console.
    *
    * @param root the top of the bst being processed
    */
   public void postOrderTraversal(Node root){
      if (root == null) {
         System.out.println("");
      };

      Stack<Node> stack = new Stack<Node>();
      ArrayList<Integer> orderList = new ArrayList<Integer>();
      Stack<Node> visited = new Stack<Node>();

      Node node = root;
      while (node != null || !stack.isEmpty()) {
         while (node != null) {
            stack.push(node);
            node = node.left;
         }

         node = stack.peek();
         if (node.right != null && !visited.contains(node.right)) {
            node = node.right;
         } else {
            node = stack.pop();
            orderList.add(node.value);
            visited.push(node);
            node = null;
         }
      }

      for (int value : orderList) {
         System.out.println(value);
      }
   }


   /**
    * A method which determines if a tree contains a value.
    *
    * @param root the top of the bst being processed
    * @param key the value that may be in the tree
    * @return true if value is in tree, false if not found
    */
   public boolean find(Node root, int key){
      Node cur = root;
      while (cur != null) {
         if (key < cur.value) {
            cur = cur.left;
         } else if (key == cur.value) {
            return true;
         } else {
            cur = cur.right;
         }
      }
      return false;
   }
   
   
   
   /*
   a method to find the node in the tree
   with a smallest key
   */
   public int getMin(Node root){
      //implement me
   }
  
  
  
   /*
   a method to find the node in the tree
   with a largest key
   */
   public int getMax(Node root){
	  //implement me
   }
   
   
   
   /*
   this method will not compile until getMax
   is implemented
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
   public static void main(String[] args){
      BinarySearchTree t1  = new BinarySearchTree();
      t1.insert(24);
      t1.insert(80);
      t1.insert(18);
      t1.insert(9);
      t1.insert(90);
      t1.insert(22);
            
      System.out.print("in-order :   ");
      t1.inOrderTraversal(t1.root);
      System.out.println();
           
      
   }  
}