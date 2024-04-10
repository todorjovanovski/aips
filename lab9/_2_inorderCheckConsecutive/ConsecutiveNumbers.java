package lab9._2_inorderCheckConsecutive;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class BNode<E> {

    public E info;
    public BNode<E> left;
    public BNode<E> right;

    static int LEFT = 1;
    static int RIGHT = 2;

    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }

    public BNode() {
        this.info = null;
        left = null;
        right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

}

class BTree<E extends Comparable<E>> {

    public BNode<E> root;

    public BTree() {
        root = null;
    }

    public BTree(E info) {
        root = new BNode<E>(info);
    }

    public void makeRoot(E elem) {
        root = new BNode<>(elem);
    }

    public void makeRootNode(BNode<E> node) {
        root = node;
    }

    public BNode<E> addChild(BNode<E> node, int where, E elem) {
        BNode<E> tmp = new BNode<E>(elem);

        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }

        return tmp;
    }

    public void addChildNode(BNode<E> node, int where, BNode<E> tmp) {
        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return;
            node.right = tmp;
        }

    }

    private void inorderList(BNode<E> node, List<E> list) {
        if (node == null) return;

        inorderList(node.left, list);
        list.add(node.info);
        inorderList(node.right, list);
    }

    public List<E> inorderList() {
        List<E> list = new ArrayList<>();
        inorderList(root, list);
        return list;
    }
}


public class ConsecutiveNumbers {
    public static boolean checkConsecutive(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) - 1 != list.get(i - 1)) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        int i, j, k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        @SuppressWarnings("unchecked") BNode<Integer> nodes[] = new BNode[N];
        BTree<Integer> tree = new BTree<Integer>();

        for (i = 0; i < N; i++)
            nodes[i] = null;

        for (i = 0; i < N; i++) {
            String line = br.readLine();
            st = new StringTokenizer(line);
            int index = Integer.parseInt(st.nextToken());
            nodes[index] = new BNode<Integer>(Integer.parseInt(st.nextToken()));
            String action = st.nextToken();
            if (action.equals("LEFT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
            } else if (action.equals("RIGHT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
            } else {
                // this node is the root
                tree.makeRootNode(nodes[index]);
            }
        }

        br.close();

        // vasiot kod ovde
        List<Integer> list = tree.inorderList();
        System.out.println(checkConsecutive(list));
    }
}
