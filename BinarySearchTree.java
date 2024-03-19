package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

    private int elementCount = 0;
    public Node<T> root;

    public BinarySearchTree() {
        root = null;
    }
    
    @Override
    public boolean add(T item) {
       if(root == null){
        root = new Node<T>(item);
        elementCount++;
        return true;
       }
       return addHelp(item, root);
    }
    private boolean addHelp (T item, Node<T> current){
        if(current.getVal().equals(item)){
            return true;
        }
        if(current.getVal().compareTo(item) < 0){
            if(current.right == null){
                Node<T> tempNode = new Node<>(item);
                current.right = tempNode;
                elementCount++;
                return false;
            }else{
                return addHelp(item, current.right);
            }
        }
        if(current.getVal().compareTo(item) > 0){
            if(current.left == null){
                Node<T> tempNode = new Node<>(item);
                current.left = tempNode;
                elementCount++;
                return false;
            }else{
                return addHelp(item, current.left);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean addAll(Collection<? extends T> items) {
        boolean oneThingChanged = false;
        for(T item : items){
            if(add(item) == true){
                oneThingChanged = true;
            }
        }
        return oneThingChanged;
    }

    @Override
    public void clear() {
        elementCount = 0;
         root = null;
    }

    @Override
    public boolean contains(T item) {
        if(root == null){
            return false;
        }
        return containsHelp(item, root);
    }
    private boolean containsHelp(T item, Node<T> current){
        if(current.getVal().equals(item)){
            return true;
        }
        if(current.getVal().compareTo(item) > 0){
            if(current.left == null){
                return false;
            }else{
                return containsHelp(item, current.left);
            }
        }
        if(current.getVal().compareTo(item) < 0){
            if(current.right == null){
                return false;
            }else{
                return containsHelp(item, current.right);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean containsAll(Collection<? extends T> items) {
        boolean  containsAllCheck = true;
        for(T item : items){
            if(contains(item) == false){
                containsAllCheck = false;
            }
        }
        return containsAllCheck;
    }

    @Override
    public boolean isEmpty() {
       if(root == null){
        return true;
       }
        return false;
    }

    @Override
    public T first() throws NoSuchElementException {
        if(root == null){
            throw new NoSuchElementException();
        }
        else {
            return firstHelper(root).getVal();
        }
    }

    private Node<T> firstHelper(Node<T> current){
        if(current.left == null){
            return current;
        }
        else{
            return firstHelper(current.left);
        }
    }

    @Override
    public T last() throws NoSuchElementException {
        if(root == null){
            throw new NoSuchElementException();
        }
        else {
            return lastHelper(root).getVal();
        }
    }

    
    private Node<T> lastHelper(Node<T> current){
        if(current.right == null){
            return current;
        }
        else{
            return lastHelper(current.right);
        }
    }

    @Override
    public boolean remove(T item) {
        if(root == null) {
            return false;
        }
        return removeHelp(item, root, null);
    }

    public boolean removeHelp(T itemToRemove, Node<T> currentNode, Node<T> prevNode){
        if(itemToRemove.equals(currentNode.getVal())){
            removeNode(currentNode, prevNode);
            return true;
        }

        if(currentNode.getVal().compareTo(itemToRemove) < 0){
            if(currentNode.right == null){
                return false;
            }else{
                return removeHelp(itemToRemove, currentNode.right, currentNode);
            }
        }
        if(currentNode.getVal().compareTo(itemToRemove) > 0){
            if(currentNode.left == null){
                return false;
            }else{
                return removeHelp(itemToRemove, currentNode.left, currentNode);
            }
        }
        throw new NoSuchElementException();
    }

    private T removeNode(Node<T> nodeToRemove, Node<T> prevNode){
        T temp = null;
        if(nodeToRemove.left == null && nodeToRemove.right == null){
            temp = nodeToRemove.getVal();
            if(prevNode == null){
                root = null;
            }
            else if(prevNode.left != null){
                prevNode.left = null;
            }else{
                prevNode.right = null;
            }
        }
        else if(nodeToRemove.left != null && nodeToRemove.right != null){
            temp = getSuccsessor(nodeToRemove.right, nodeToRemove);
            nodeToRemove.setVal(temp);
        }
        else if(nodeToRemove.right != null){
            temp = nodeToRemove.getVal();
            if(prevNode == null){
               root = nodeToRemove.right;
            }else{
                prevNode.left = nodeToRemove.right;
            }
        }
        else if(nodeToRemove.left != null){
            temp = nodeToRemove.getVal();
            if(prevNode == null){
                root = nodeToRemove.left;
            }else{
                prevNode.right = nodeToRemove.left;
            }
        }
        elementCount--;
        return temp;
    }

    private T getSuccsessor(Node<T> current, Node<T> parentNode){
        if(current.left == null){
            T tempVal = current.getVal();
            parentNode.right = current.right;
            return tempVal;
        }
        else{
            return getSuccsessor(current.left, current);
        }
    }

    @Override
    public boolean removeAll(Collection<? extends T> items) {
        boolean oneThingChanged = false;
        for(T item : items){
            if(remove(item) == true){
                oneThingChanged = true;
            }
        }
        return oneThingChanged;
    }

    @Override
    public int size() {
      return elementCount;
    }

    @Override
    public ArrayList<T> toArrayList() {
        ArrayList<T> items = new ArrayList<>();
        return orderRecrusive(root, items);
    }

    private ArrayList<T> orderRecrusive(Node<T> current, ArrayList<T> returnList){
        if(current == null){
            return returnList;
        }
        orderRecrusive(current.left, returnList);
        orderRecrusive(current.right, returnList);
        returnList.add(current.getVal());
        return returnList;
    }
}
