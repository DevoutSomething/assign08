package assign08;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class BinarySearchTest {

   BinarySearchTree<Integer> tree = new BinarySearchTree<>();
   BinarySearchTree<Integer> smallTree = new BinarySearchTree<>();


   @BeforeEach
   void setup(){
      tree = new BinarySearchTree<>();
      tree.add(4);
      tree.add(2);
      tree.add(3);
      tree.add(1);
      tree.add(0);
      tree.add(5);
      tree.add(8);
      tree.add(7);
      tree.add(6);

      smallTree = new BinarySearchTree<>();
      smallTree.add(4);
      smallTree.add(7);
    

   }

   @Test
   public void testFirst(){
      
      Integer num = 8;
      assertEquals(tree.last(), num);
   }

   @Test
   public void removeEightTest(){
      tree.remove(8);
      assertFalse(tree.contains(8));
      assertTrue(tree.contains(0));
      assertTrue(tree.contains(1));
      assertTrue(tree.contains(3));
      assertTrue(tree.contains(5));
      assertTrue(tree.contains(6));
      assertTrue(tree.contains(7));
      assertTrue(tree.contains(2));
   }
   @Test
   public void testRemoveTwoItems(){
      smallTree.remove(7);
      smallTree.remove(4);
      assertFalse(smallTree.contains(4));
      assertFalse(smallTree.contains(7));
      assertTrue(smallTree.isEmpty());
   }
   
   @Test 
   public void testRemoveRoot(){
      tree.remove(4);
      assertFalse(tree.contains(4));
      assertTrue(tree.contains(2));
      assertTrue(tree.contains(3));
      assertTrue(tree.contains(0));
      assertTrue(tree.contains(1));
      tree.remove(2);
      assertTrue(tree.contains(0));
      assertTrue(tree.contains(1));
      assertTrue(tree.contains(3));
      assertTrue(tree.contains(5));
      assertTrue(tree.contains(6));
      assertTrue(tree.contains(7));
      assertTrue(tree.contains(8));
   }

   @Test
   public void testLast(){
      Integer num = 0;
      assertEquals(tree.first(), num);
   }

   @Test
   public void testContains(){
      
      assertTrue(tree.contains(4));
      assertTrue(tree.contains(7));
      assertTrue(tree.contains(0));
   }

   @Test
   public void testRemoves(){
      tree.remove(4);
      assertEquals(false, tree.contains(4));
   }

   @Test
   public void testClear(){
      tree.clear();
      assertEquals(tree.size(), 0);

   }

}
