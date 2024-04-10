package lab8._1_windowsExplorer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

interface Tree<E> {
    ////////////Accessors ////////////

    public Node<E> root();

    public Node<E> parent(Node<E> node);

    public int childCount(Node<E> node);

    ////////////Transformers ////////////
    public void makeRoot(E elem);

    public Node<E> addChild(Node<E> node, E elem);

    public void remove(Node<E> node);

    ////////////Iterator ////////////
    public Iterator<E> children(Node<E> node);

}

interface Node<E> {

    public E getElement();

    public void setElement(E elem);
}


class SLLTree<E> implements Tree<E> {

    private SLLNode<E> root;

    public SLLTree() {
        root = null;
    }

    public Node<E> root() {
        return root;
    }

    public Node<E> parent(Node<E> node) {
        return ((SLLNode<E>) node).parent;
    }

    public int childCount(Node<E> node) {
        SLLNode<E> tmp = ((SLLNode<E>) node).firstChild;
        int num = 0;
        while (tmp != null) {
            tmp = tmp.sibling;
            num++;
        }
        return num;
    }

    public void makeRoot(E elem) {
        root = new SLLNode<E>(elem);
    }

    public Node<E> addChild(Node<E> node, E elem) {
        SLLNode<E> tmp = new SLLNode<E>(elem);
        SLLNode<E> curr = (SLLNode<E>) node;
        tmp.sibling = curr.firstChild;
        curr.firstChild = tmp;
        tmp.parent = curr;
        return tmp;
    }

    public void remove(Node<E> node) {
        SLLNode<E> curr = (SLLNode<E>) node;
        if (curr.parent != null) {
            if (curr.parent.firstChild == curr) {
                // The node is the first child of its parent
                // Reconnect the parent to the next sibling
                curr.parent.firstChild = curr.sibling;
            } else {
                // The node is not the first child of its parent
                // Start from the first and search the node in the sibling list and remove it
                SLLNode<E> tmp = curr.parent.firstChild;
                while (tmp.sibling != curr) {
                    tmp = tmp.sibling;
                }
                tmp.sibling = curr.sibling;
            }
        } else {
            root = null;
        }
    }

    static class SLLTreeIterator<T> implements Iterator<T> {

        SLLNode<T> start, current;

        public SLLTreeIterator(SLLNode<T> node) {
            start = node;
            current = node;
        }

        public boolean hasNext() {
            return (current != null);
        }

        public T next() throws NoSuchElementException {
            if (current != null) {
                SLLNode<T> tmp = current;
                current = current.sibling;
                return tmp.getElement();
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (current != null) {
                current = current.sibling;
            }
        }
    }

    public Iterator<E> children(Node<E> node) {
        return new SLLTreeIterator<E>(((SLLNode<E>) node).firstChild);
    }

    void printTreeRecursive(Node<E> node, int level) {
        if (node == null)
            return;
        int i;
        SLLNode<E> tmp;

        for (i=0;i<level;i++)
            System.out.print(" ");
        System.out.println(node.getElement().toString());
        tmp = ((SLLNode<E>)node).firstChild;
        while (tmp != null) {
            printTreeRecursive(tmp, level+1);
            tmp = tmp.sibling;
        }
    }

    public void printTree() {
        printTreeRecursive(root, 0);
    }

}

class SLLNode<P> implements Node<P> {

    // Holds the links to the needed nodes
    public SLLNode<P> parent, sibling, firstChild;
    // Hold the data
    public P element;

    public SLLNode(P o) {
        element = o;
        parent = sibling = firstChild = null;
    }

    public P getElement() {
        return element;
    }

    public void setElement(P o) {
        element = o;
    }
}

public class WindowsExplorer {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] commands = new String[N];

        for (i=0;i<N;i++)
            commands[i] = br.readLine();

        br.close();

        SLLTree<String> tree = new SLLTree<String>();
        tree.makeRoot("c:");

        // vasiot kod stoi ovde
        SLLNode<String> curr = (SLLNode<String>) tree.root();
        for(j=0; j<commands.length; j++) {
            String[] parts = commands[j].split("\\s+");
            if(parts[0].equals("CREATE")) {
                if(curr.firstChild == null){
                    tree.addChild(curr, parts[1]);
                }
                else{
                    SLLNode<String> temp = curr.firstChild;
                    SLLNode<String> tempPred = null;
                    while(temp != null && temp.element.compareTo(parts[1]) < 0){
                        tempPred = temp;
                        temp = temp.sibling;
                    }
                    if(temp == null){
                        SLLNode<String> newSibling = new SLLNode<>(parts[1]);
                        newSibling.parent = curr;
                        tempPred.sibling = newSibling;
                    }
                    else if(tempPred != null){
                        SLLNode<String> newSibling = new SLLNode<>(parts[1]);
                        newSibling.parent = curr;
                        newSibling.sibling = temp;
                        tempPred.sibling = newSibling;
                    }
                    else{
                        SLLNode<String> newSibling = new SLLNode<>(parts[1]);
                        newSibling.parent = curr;
                        newSibling.sibling = temp;
                        curr.firstChild = newSibling;
                    }
                }
            }
            else if(parts[0].equals("OPEN")) {
                curr = curr.firstChild;
                while(!curr.element.equals(parts[1])) {
                    curr = curr.sibling;
                }
            }
            else if(parts[0].equals("DELETE")) {
                SLLNode<String> temp = curr.firstChild;
                while(!temp.element.equals(parts[1])) {
                    temp = temp.sibling;
                }
                tree.remove(temp);
            }
            else if(parts[0].equals("BACK")) {
                if(curr.parent != null)
                    curr = curr.parent;
            }
            else if(parts[0].equals("PATH")) {
                SLLNode<String> temp = curr;
                Stack<String> path = new Stack<>();
                while(temp.parent  != null) {
                    path.push(temp.element);
                    temp = temp.parent;
                }
                path.push(((SLLNode<String>) tree.root()).element);
                while (!path.empty()) {
                    System.out.print(path.pop() + "\\");
                }
                System.out.println();
            }
            else if(parts[0].equals("PRINT")) {
                tree.printTree();
            }
        }
    }
}
