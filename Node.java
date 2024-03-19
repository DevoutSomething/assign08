package assign08;
public class Node <T> {
    public Node<T> left;
    public Node<T> right;
    private T val;

    public Node<T> getLeft(){
        return left;
    }
    public Node<T> getRight(){
        return left;
    }
    public Node(T val){
        this.val = val;
    }
    public T getVal(){
        return val;
    }
    public void setVal(T val){
        this.val = val;
    }
}
