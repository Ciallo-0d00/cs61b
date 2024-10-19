package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>,Iterable<T> {

    private T[] items;
    private int size;
    private int front;
    private int rear;
    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        front = 0;
        rear = 0;
    }
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        int firstIndex = (size == 0)? 0 : (front - 1 + items.length) % items.length;
        items[firstIndex]=item;
        if (size == 0) {
            front = firstIndex;
            rear = firstIndex;
        } else {
            front = firstIndex;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        int lastIndex = (size == 0)? 0: (rear + 1) % items.length;
        items[lastIndex] = item;
        if (size == 0){
            front = lastIndex;
            rear = lastIndex;
        }else {
            rear = lastIndex;
        }
        size ++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int index = front;
        for (int i = 0; i < size; i++) {
            System.out.print(items[index] + " ");
            index=(index + 1)%items.length;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size <= 0) {
            return null;
        }
        T removed = items[front];
        size--;
        front = (front + 1) % items.length;
        if (items.length >= 16 && size < items.length / 4){
            resize(items.length / 2);
        }
        return removed;
    }

    @Override
    public T removeLast() {
        if (size <= 0) {
            return null;
        }
        T removed = items[rear];
        size--;
        rear = (rear - 1 + items.length) % items.length;
        if (items.length >= 16 && size < items.length / 4){
            resize(items.length / 2);
        }
        return removed;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(front + index) % items.length];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int count = 0;
            private int currentIndex = front;
            @Override
            public boolean hasNext() {
                return count < size;
            }
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                T item = items[currentIndex];
                currentIndex = (currentIndex + 1)% items.length;
                count++;
                return item;
            }
        };
    }
    private void resize(int cap) {
        T[] a = (T []) new Object[cap];
        if(front <= rear){
            System.arraycopy(items, front, a, front, size);
        }else {
            System.arraycopy(items, front, a, front, items.length - front);
            System.arraycopy(items, 0, a, items.length - front, rear + 1);
            items = a;
            front = 0;
            rear = size - 1;
        }
    }

}
