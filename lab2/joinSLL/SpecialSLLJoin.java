package lab2.joinSLL;

import java.util.Scanner;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int listSize = 0;
        SLLNode<E> tmp = first;
        while(tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += " " + tmp;
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, null);
        ins.succ = first;
        //SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }
    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if(first==before){
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before && tmp.succ!=null)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                tmp.succ = new SLLNode<E>(o, before);;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = new SLLNode<E>(o, null);
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if(first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void merge (SLL<E> in){
        if (first != null) {
            SLLNode<E> tmp = first;
            while(tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = in.getFirst();
        }
        else{
            first = in.getFirst();
        }
    }

    public void mirror() {
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while(tmp != null){
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }
    }
}

public class SpecialSLLJoin<E> {

    //todo: implement function
    public void insertElements(SLL<E> resultList, SLL<E> list) {
        resultList.insertLast(list.getFirst().element);
        list.deleteFirst();
        if(list.getFirst() != null) {
            resultList.insertLast(list.getFirst().element);
            list.deleteFirst();
        }
    }
    public SLL<E> specialJoin(SLL<E> list1, SLL<E> list2) {
        SLL<E> resultList = new SLL<>();
        int n = Math.min(list1.size(), list2.size());
        SLLNode<E> temp1 = list1.getFirst();
        SLLNode<E> temp2 = list2.getFirst();

/*        while(temp1 != null || temp2 != null) {
            if(temp1 != null){
                resultList.insertLast(temp1.element);
                temp1 = temp1.succ;
                list1.deleteFirst();
                if(temp1 != null) {
                    resultList.insertLast(temp1.element);
                    temp1 = temp1.succ;
                    list1.deleteFirst();
                }
            }
            if(temp2 != null) {
                resultList.insertLast(temp2.element);
                temp2 = temp2.succ;
                list2.deleteFirst();
                if(temp2 != null) {
                    resultList.insertLast(temp2.element);
                    temp2 = temp2.succ;
                    list2.deleteFirst();
                }
            }
        }*/

        while(list1.getFirst() != null || list2.getFirst() != null) {
            if(list1.getFirst() != null){
                insertElements(resultList, list1);
            }
            if(list2.getFirst() != null) {
                insertElements(resultList, list2);
            }
        }

        return resultList;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        SLL<Integer> list1 = new SLL<>();
        for(int i=0;i<n;i++) {
            list1.insertLast(input.nextInt());
        }

        n = input.nextInt();

        SLL<Integer> list2 = new SLL<>();
        for(int i=0;i<n;i++) {
            list2.insertLast(input.nextInt());
        }

        SpecialSLLJoin<Integer> tmp = new SpecialSLLJoin<>();

        System.out.println(tmp.specialJoin(list1, list2));
    }

}