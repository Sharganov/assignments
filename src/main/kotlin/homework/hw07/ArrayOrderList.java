package homework.hw07;

import java.util.Iterator;

public class ArrayOrderList<T extends Comparable<? super T>>
        extends IOrderList<T> {

    private T[] arr;
    private Integer count;

    public ArrayOrderList() {

        arr = (T[]) new Comparable[10];
        count = 0;
    }


    @Override
    public void add(T val) {
        if (count == arr.length - 1) bigger();
        int i = 0;
        while (i < count && arr[i].compareTo(val) < 0) i++;
        toRight(i);
        arr[i] = val;
        count++;
    }

    private void bigger() {
        T[] newarr = (T[]) new Comparable[arr.length + arr.length/2];
        for (int i = 0; i < arr.length; i++) {
            newarr[i] = arr[i];
        }
        arr = newarr;
    }

    private void toRight(int index) {

        if (count == 0) arr[1] = arr[0];
        else
            for (int i = count; i > index; i--) {
                arr[i] = arr[i - 1];
            }
    }

    @Override
    public void remove(T val) {
        int i = 0;
        while(i<count && !arr[i].equals(val)) i++;
        toLeft(i);
    }

    private void toLeft(int index)
    {
        for(int i = index; i<= count; i++)
        {
            arr[i] = arr[i+1];
        }
    }
    @Override
    public int size() {
        return count;
    }

    @Override
    public T get(int index) {
        return arr[index];
    }

    public void print() {
        int i = 0;
        while (i < size() & arr[i] != null) {
            System.out.println(arr[i]);
            i++;
        }
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            private Integer index = 0;

            @Override
            public boolean hasNext() {
                return index < count;
            }

            @Override
            public T next() {
                index++;
                return arr[index - 1];
            }

            @Override
            public void remove() {}
        };
    }
}