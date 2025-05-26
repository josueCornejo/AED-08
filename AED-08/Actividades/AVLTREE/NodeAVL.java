package Actividades.AVLTREE;
public class NodeAVL<E> extends NodeBST<E> {
    protected int bf;  // Balance Factor (factor de equilibrio)

    public NodeAVL(E data) {
        super(data);   // llama al constructor de NodeBST
        this.bf = 0;   // el factor de equilibrio inicia en 0
    }

    @Override
    public String toString() {
        return data + " (bf=" + bf + ")";
    }
}
