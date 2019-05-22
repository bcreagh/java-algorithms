package com.bcreagh.javaalgos.stack;

import com.bcreagh.javaalgos.resizingarray.ResizingArray;

import java.util.Iterator;

public class StackWithResizingArray<T> implements Iterable<T> {

    private ResizingArray<T> items = new ResizingArray<>();

    public void push(T item) {
        items.add(item);
    }

    public T pop() {
        return items.removeAt(items.getLength() - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return items.iterator();
    }
}
