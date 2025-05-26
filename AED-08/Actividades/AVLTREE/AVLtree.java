package Actividades.AVLTREE;

import java.util.LinkedList;
import java.util.Queue;

public class AVLtree<E extends Comparable<E>> extends BSTree<E> {
    private boolean height;

    public void insert(E x) throws Exception {
        height = false;
        root = insertRec(x, (NodeAVL<E>) root);
    }

    private NodeAVL<E> insertRec(E x, NodeAVL<E> node) throws Exception {
        if (node == null) {
            height = true;
            return new NodeAVL<>(x);
        }

        int cmp = x.compareTo(node.getData());
        if (cmp == 0) throw new Exception("Item duplicado");
        if (cmp < 0) {
            node.setLeft(insertRec(x, (NodeAVL<E>) node.getLeft()));
            if (height) node = balanceLeft(node);
        } else {
            node.setRight(insertRec(x, (NodeAVL<E>) node.getRight()));
            if (height) node = balanceRight(node);
        }
        return node;
    }

    private NodeAVL<E> balanceLeft(NodeAVL<E> node) {
        switch (node.bf) {
            case 1:
                node.bf = 0;
                height = false;
                break;
            case 0:
                node.bf = -1;
                break;
            case -1:
                NodeAVL<E> left = (NodeAVL<E>) node.getLeft();
                if (left.bf == -1) {
                    node = rotateSR(node);
                    node.bf = 0;
                    ((NodeAVL<E>) node.getRight()).bf = 0;
                } else {
                    node.setLeft(rotateSL(left));
                    node = rotateSR(node);
                    adjustBFInsert(node);
                }
                height = false;
                break;
        }
        return node;
    }

    private NodeAVL<E> balanceRight(NodeAVL<E> node) {
        switch (node.bf) {
            case -1:
                node.bf = 0;
                height = false;
                break;
            case 0:
                node.bf = 1;
                break;
            case 1:
                NodeAVL<E> right = (NodeAVL<E>) node.getRight();
                if (right.bf == 1) {
                    node = rotateSL(node);
                    node.bf = 0;
                    ((NodeAVL<E>) node.getLeft()).bf = 0;
                } else {
                    node.setRight(rotateSR(right));
                    node = rotateSL(node);
                    adjustBFInsert(node);
                }
                height = false;
                break;
        }
        return node;
    }

    private void adjustBFInsert(NodeAVL<E> node) {
        switch (node.bf) {
            case 1:
                ((NodeAVL<E>) node.getLeft()).bf = -1;
                ((NodeAVL<E>) node.getRight()).bf = 0;
                break;
            case -1:
                ((NodeAVL<E>) node.getLeft()).bf = 0;
                ((NodeAVL<E>) node.getRight()).bf = 1;
                break;
            default:
                ((NodeAVL<E>) node.getLeft()).bf = 0;
                ((NodeAVL<E>) node.getRight()).bf = 0;
        }
        node.bf = 0;
    }

    public void remove(E x) throws Exception {
        height = false;
        root = removeRec(x, (NodeAVL<E>) root);
    }

    private NodeAVL<E> removeRec(E x, NodeAVL<E> node) throws Exception {
        if (node == null) {
            throw new Exception("Elemento no encontrado en el árbol");
        }

        int cmp = x.compareTo(node.getData());
        if (cmp < 0) {
            node.setLeft(removeRec(x, (NodeAVL<E>) node.getLeft()));
            if (height) node = rebalanceRight(node);
        } else if (cmp > 0) {
            node.setRight(removeRec(x, (NodeAVL<E>) node.getRight()));
            if (height) node = rebalanceLeft(node);
        } else {
            if (node.getLeft() == null || node.getRight() == null) {
                node = (node.getLeft() != null) ? (NodeAVL<E>) node.getLeft() : (NodeAVL<E>) node.getRight();
                height = true;
            } else {
                NodeAVL<E> min = findMin((NodeAVL<E>) node.getRight());
                node.setData(min.getData());
                node.setRight(removeRec(min.getData(), (NodeAVL<E>) node.getRight()));
                if (height) node = rebalanceLeft(node);
            }
        }
        return node;
    }

    private NodeAVL<E> findMin(NodeAVL<E> node) {
        while (node.getLeft() != null) {
            node = (NodeAVL<E>) node.getLeft();
        }
        return node;
    }

    private NodeAVL<E> rebalanceLeft(NodeAVL<E> node) {
        switch (node.bf) {
            case -1:
                node.bf = 0;
                height = true;
                break;
            case 0:
                node.bf = 1;
                height = false;
                break;
            case 1:
                NodeAVL<E> right = (NodeAVL<E>) node.getRight();
                int bf = right.bf;
                if (bf >= 0) {
                    node = rotateSL(node);
                    if (bf == 0) {
                        node.bf = -1;
                        ((NodeAVL<E>) node.getLeft()).bf = 1;
                        height = false;
                    } else {
                        node.bf = 0;
                        height = true;
                    }
                } else {
                    node.setRight(rotateSR(right));
                    node = rotateSL(node);
                    node.bf = 0;
                    ((NodeAVL<E>) node.getLeft()).bf = 0;
                    height = true;
                }
                break;
        }
        return node;
    }

    private NodeAVL<E> rebalanceRight(NodeAVL<E> node) {
        switch (node.bf) {
            case 1:
                node.bf = 0;
                height = true;
                break;
            case 0:
                node.bf = -1;
                height = false;
                break;
            case -1:
                NodeAVL<E> left = (NodeAVL<E>) node.getLeft();
                int bf = left.bf;
                if (bf <= 0) {
                    node = rotateSR(node);
                    if (bf == 0) {
                        node.bf = 1;
                        ((NodeAVL<E>) node.getRight()).bf = -1;
                        height = false;
                    } else {
                        node.bf = 0;
                        height = true;
                    }
                } else {
                    node.setLeft(rotateSL(left));
                    node = rotateSR(node);
                    node.bf = 0;
                    ((NodeAVL<E>) node.getRight()).bf = 0;
                    height = true;
                }
                break;
        }
        return node;
    }

    private NodeAVL<E> rotateSL(NodeAVL<E> node) {
        NodeAVL<E> hijo = (NodeAVL<E>) node.getRight();
        node.setRight(hijo.getLeft());
        hijo.setLeft(node);
        return hijo;
    }

    private NodeAVL<E> rotateSR(NodeAVL<E> node) {
        NodeAVL<E> hijo = (NodeAVL<E>) node.getLeft();
        node.setLeft(hijo.getRight());
        hijo.setRight(node);
        return hijo;
    }

    public void recorridoPorAmplitud() {
        if (root == null) {
            System.out.println("Árbol vacío");
            return;
        }

        Queue<NodeBST<E>> cola = new LinkedList<>();
        cola.add(root);

        while (!cola.isEmpty()) {
            NodeAVL<E> actual = (NodeAVL<E>) cola.poll();
            System.out.print(actual + " ");
            if (actual.getLeft() != null) cola.add(actual.getLeft());
            if (actual.getRight() != null) cola.add(actual.getRight());
        }
        System.out.println();
    }
}
