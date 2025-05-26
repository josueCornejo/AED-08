package Actividades.AVLTREE;
import Actividades.Exceptions.ItemNoFound;
public class BSTree<E extends Comparable<E>> {
    protected NodeBST<E> root;
    public BSTree() {
        root = null;
    }

    // Buscar un elemento en el árbol
    public E search(E x) throws ItemNoFound {
        NodeBST<E> res = searchrec(x, root);
        if (res == null)
            throw new ItemNoFound("Item no está en el árbol...");
        return res.getData();
    }

    // Método recursivo de búsqueda
    private NodeBST<E> searchrec(E x, NodeBST<E> node) {
        if (node == null) return null;
        int resC = node.getData().compareTo(x);
        if (resC == 0) return node;
        if (resC > 0) return searchrec(x, node.getLeft());
        else return searchrec(x, node.getRight());
    }

    // Altura del árbol
    public int height() {
        return height(root);
    }

    private int height(NodeBST<E> node) {
        if (node == null) return -1; // convención común
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }
}
