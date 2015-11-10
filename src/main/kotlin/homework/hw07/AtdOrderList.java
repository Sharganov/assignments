package homework.hw07;

import java.util.Iterator;

public class AtdOrderList<T extends Comparable<? super T>> extends IOrderList<T> {

    private int count = 0;
    private Node list = null;


    class Node<T extends Comparable<? super T>> {

        T value;
        Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> temp = list;

            @Override
            public boolean hasNext() {
                return (temp != null);
            }

            @Override
            public T next() {
                T val = temp.value;
                temp = temp.next;
                return val;
            }

            @Override
            public void remove() {}
        };
    }

    @Override
    public void add(T val) {
        list = insert(val, list);
        count++;
    }


    private Node insert(T val, Node node) {
        if (node == null)
            return new Node(val, null);
        else if (node.value.compareTo(val) >= 0)
            return new Node(val, new Node(node.value, node.next));
        else
            return new Node(node.value, insert(val, node.next));
    }

    @Override
    public void remove(T val) {
        if(list != null) list = delete(val,list);
    }

    private Node delete(T val, Node node){
        if (node.value.equals(val)) return node.next;
        if(!node.next.value.equals(val)) return delete(val, node.next);
        return new Node(node.value, node.next.next);
    }

    @Override
    public int size() {return count;}

    @Override
    public T get(int index) {
        Node temp = list;
        while (index > 0) {
            temp = temp.next;
            index--;
        }
        return (T) temp.value;
    }

    public void print() {
        Node temp = list;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }
}

