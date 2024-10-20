package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private IntNode sentinel;
    private int size;
    public LinkedListDeque() {
        sentinel = new IntNode(-1);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        IntNode<T> node = new IntNode<>(item);
        node.prev = sentinel;
        node.next = sentinel.next;
        sentinel.next.prev = node;
        sentinel.next = node;
        size++;
    }

    @Override
    public void addLast(T item) {
        IntNode<T> node = new IntNode<>(item);
        node.next = sentinel;
        node.prev = sentinel.prev;
        sentinel.prev.next = node;
        sentinel.prev = node;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        IntNode<T> current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if(size() > 0) {
            T item = (T) sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size--;
            return item;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if(size > 0) {
            T item = (T) sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size--;
            return item;
        }
        return null;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (size == 1) {
            return (T) sentinel.next.item;
        } else {
            IntNode<T> curr = sentinel.next;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
            return curr.item;
        }
    }
    public T getRecursive(int index) {
        if (size == 1) {
            return (T) sentinel.next.item;
        } else {
            IntNode<T> node = sentinel.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node.item;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private IntNode<T> current = sentinel.next;
            @Override
            public boolean hasNext() {
                return current!= sentinel;
            }
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                T item = current.item;
                current = current.next;
                return item;
            }
        };
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Deque) {
            if(this.size != ((Deque<?>) o).size()){
                return false;
            }
            for (int i = 0; i < size(); i++) {
                if(this.get(i) != ((Deque<?>) o).get(i)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private static class IntNode<T> {
        T item;
        IntNode next;
        IntNode prev;
        IntNode(T item) {
            this.item = item;
            this.prev = null;
            this.next = null;
        }
    }
}



