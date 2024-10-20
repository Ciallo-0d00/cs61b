package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> implements Deque<T>,Iterable<T>{
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T maxElement = this.get(0);
        for (int i = 0; i < size(); i++) {
            if (comparator.compare(maxElement, get(i)) < 0){
                maxElement = get(i);
            }
        }
        return maxElement;
    }
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maxElement = this.get(0);
        for (int i = 0; i < size(); i++) {
            if (c.compare(maxElement, get(i)) < 0){
                maxElement = get(i);
            }
        }
        return maxElement;
    }
}