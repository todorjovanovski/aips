package lab6.bubbleSortDLL;

import java.util.Scanner;

class DLLNode<T> {
    T element;
    DLLNode<T> pred;
    DLLNode<T> succ;

    public DLLNode(T element, DLLNode<T> pred, DLLNode<T> succ) {
        this.element = element;
        this.pred = pred;
        this.succ = succ;
    }
}

class DLL<T> {
    DLLNode<T> first;
    DLLNode<T> last;

    public DLL() {
        emptyList();
    }

    public DLLNode<T> getFirst() {
        return first;
    }

    public DLLNode<T> getLast() {
        return last;
    }

    public void insertFirst(T element) {
        DLLNode<T> ins = new DLLNode<>(element, null, first);
        if(first == null) {
            last = ins;
        } else {
            first.pred = ins;
        }
        first = ins;
    }

    public void insertLast(T element) {
        if(first == null) {
            insertFirst(element);
        } else {
            DLLNode<T> ins = new DLLNode<>(element, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(T element, DLLNode<T> after) {
        if(after == last) {
            insertLast(element);
        } else {
            DLLNode<T> ins = new DLLNode<>(element, after, after.succ);
            after.succ.pred = ins;
            after.succ = ins;
        }
    }

    public void insertBefore(T element, DLLNode<T> before) {
        if(before == first) {
            insertFirst(element);
        } else {
            DLLNode<T> ins = new DLLNode<>(element, before.pred, before);
            before.pred.succ = ins;
            before.pred = ins;
        }
    }

    public T deleteFirst() {
        if(first != null) {
            DLLNode<T> temp = first;
            first = first.succ;
            if(first != null) first.pred = null;
            if(first == null) last = null;
            return temp.element;
        }
        return null;
    }

    public T deleteLast() {
        if(first != null) {
            if(first.succ == null)
                return deleteFirst();
            else {
                DLLNode<T> temp = last;
                last = last.pred;
                last.succ = null;
                return temp.element;
            }
        }
        return null;
    }

    public T delete(DLLNode<T> node) {
        if(node == first) return deleteFirst();
        if(node == last) return deleteLast();
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;
    }

    public DLLNode<T> find(T element) {
        if(first != null) {
            DLLNode<T> temp = first;
            while(temp != null && !temp.element.equals(element))
                temp = temp.succ;
            if(temp != null && temp.element.equals(element)) return temp;
            else System.out.println("Element not found!");
        } else System.out.println("The list is empty");
        return null;
    }

    public void emptyList() {
        first = null;
        last = null;
    }

    public int getSize() {
        int size = 0;
        DLLNode<T> temp = first;
        while(temp != null) {
            size++;
            temp = temp.succ;
        }
        return size;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        DLLNode<T> temp = first;
        while(temp.succ != null) {
            result.append(temp.element).append(" ");
            temp = temp.succ;
        }
        result.append(temp.element);
        return result.toString();
    }
}

public class BubbleSortDLL {

    public static void sort(DLL<Integer> list) {
        DLLNode<Integer> tempI = list.getFirst();
        while (tempI.succ != null) {
            DLLNode<Integer> tempJ = list.getFirst();
            while (tempJ.succ != null) {
                if(tempJ.element > tempJ.succ.element) {
                    list.insertAfter(list.delete(tempJ), tempJ.succ);
                }
                tempJ = tempJ.succ;
            }
            tempI = tempI.succ;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        DLL<Integer> list = new DLL<>();
        for (int i=0; i<n; i++) {
            list.insertLast(input.nextInt());
        }

        sort(list);
        System.out.println(list);
    }
}