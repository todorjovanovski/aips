package lab9._3_bstHeight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BNode<E extends Comparable<E>> {
    public E info;
    public BNode<E> left;
    public BNode<E> right;

    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }
}

class BinarySearchTree<E extends Comparable<E>> {
    private BNode<E> root;

    public BinarySearchTree() {
        root = null;
    }

    private BNode<E> insert(E x, BNode<E> t) {
        if (t == null) {
            t = new BNode<E>(x, null, null);
        } else if (x.compareTo(t.info) < 0) {
            t.left = insert(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            t.right = insert(x, t.right);
        } // Duplicate; do nothing

        return t;
    }

    public void insert(E x) {
        root = insert(x, root);
    }

    private BNode<E> findMin(BNode<E> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    public E findMin() {
        return findMin(root).info;
    }

    private BNode<E> findMax(BNode<E> t) {
        if (t == null) {
            return null;
        } else if (t.right == null) {
            return t;
        }
        return findMax(t.right);
    }

    public E findMax() {
        return findMax(root).info;
    }

    private BNode<E> find(E x, BNode<E> t) {
        if (t == null) return null;
        if (x.compareTo(t.info) < 0) {
            return find(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            return find(x, t.right);
        } else {
            return t; // Match
        }
    }

    public BNode<E> find(E x) {
        return find(x, root);
    }

    private BNode<E> remove(Comparable<E> x, BNode<E> t) {
        if (t == null) return t; // Item not found; do nothing
        if (x.compareTo(t.info) < 0) {
            t.left = remove(x, t.left);
        } else if (x.compareTo(t.info) > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) { // Two children
            t.info = findMin(t.right).info;
            t.right = remove(t.info, t.right);
        } else {
            if (t.left != null) return t.left;
            else return t.right;
        }
        return t;
    }

    public void remove(E x) {
        root = remove(x, root);
    }

    public int maxDepth(BNode<E> node) {
        if (node == null) return 0;
        else {
            int leftDepth = maxDepth(node.left);
            int rightDepth = maxDepth(node.right);

            if (leftDepth > rightDepth) return (leftDepth + 1);
            else return (rightDepth + 1);
        }
    }

    public void listOfCurrentLevel(BNode<E> node, int level, List<E> list) {
        if (node == null) return;
        if (level == 0) {
            list.add(node.info);
        } else if (level > 0) {
            listOfCurrentLevel(node.left, level - 1, list);
            listOfCurrentLevel(node.right, level - 1, list);
        }
    }

    public BNode<E> getRoot() {
        return root;
    }
}

public class TestBSTHeight {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < n; i++) {
            bst.insert(scanner.nextInt());
        }

        BNode<Integer> node = bst.find(scanner.nextInt());
        int maxDepth = bst.maxDepth(node);
        System.out.println(maxDepth);
        List<Integer> list = new ArrayList<>();
        bst.listOfCurrentLevel(bst.getRoot(), maxDepth, list);
        System.out.println(list.size());
    }
}
