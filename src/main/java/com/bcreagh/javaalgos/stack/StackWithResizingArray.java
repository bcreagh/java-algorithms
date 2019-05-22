package com.bcreagh.javaalgos.stack;

import com.bcreagh.javaalgos.resizingarray.ResizingArray;

import java.util.Iterator;

public class StackWithResizingArray<T> implements Iterable<T> {

    private ResizingArray<T> items = new ResizingArray<>();

    // O(n) in worst case
    // O(1) amortized
    public void push(T item) {
        items.add(item);
    }

    // O(n) in worst case
    // O(1) amortized
    public T pop() {
        return items.removeAt(items.getLength() - 1);
    }

    @Override
    public Iterator<T> iterator() {
        // Returning items.iterator() will make the stack be iterated over in
        // a FIFO order. Really it should be iterated over in a LIFO order, but
        // this is just a really quick implementation
        return items.iterator();
    }
}
