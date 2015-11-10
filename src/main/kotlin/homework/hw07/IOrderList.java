package homework.hw07;


import java.util.Iterator;

public abstract class IOrderList<T extends Comparable<? super T>> implements
        Comparable<IOrderList<? extends T>>, Iterable<T> {

    @Override
    public int hashCode() {
        int answer = 0;
        for(T item : this) answer = (answer + item.hashCode()) * 31;
        return Math.abs(answer);
    }

    @Override
    public boolean equals(Object other) {

        if (!(other instanceof IOrderList)) return false;

        IOrderList<T> otherList = (IOrderList) other;

        if (size() != otherList.size()) return false;

        Iterator<T> iterator = otherList.iterator();
        for( T item : this)
            if(item != iterator.next()) return false;
        return true;
    }

    @Override
    public int compareTo(IOrderList<? extends T> other) {
        if (size() > other.size()) return 1;
        if (size() < other.size()) return -1;

        Iterator<? extends T> iterator = other.iterator();
        for(T item : this) {
            Integer comprassion = item.compareTo(iterator.next());
            if (comprassion != 0) return comprassion;
        }
        return 0;
    }

    public Iterator<T> iterator() {
        return this.iterator();
    }

    public abstract void add(T val);

    public  abstract void remove(T val);

    public abstract int size();

    public abstract T get(int index);
}

