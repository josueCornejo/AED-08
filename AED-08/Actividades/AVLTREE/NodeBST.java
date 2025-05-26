package Actividades.AVLTREE;
public class NodeBST<E> {
    protected E data;
    protected NodeBST<E> left, right;
    public NodeBST(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
    public E getData() { return data; }
    public NodeBST<E> getLeft() { return left; }
    public NodeBST<E> getRight() { return right; }

    public void setData(E data) { this.data = data; }
    public void setLeft(NodeBST<E> left) { this.left = left; }
    public void setRight(NodeBST<E> right) { this.right = right; }
}

