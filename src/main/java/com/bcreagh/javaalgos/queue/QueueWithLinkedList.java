package com.bcreagh.javaalgos.queue;

import com.bcreagh.javaalgos.linkedlist.LinkedList;

import java.util.Iterator;


public class QueueWithLinkedList<T> implements Iterable<T> {

    private LinkedList<T> items = new LinkedList<>();

    // O(1)
    public void enqueue(T item) {
        items.add(item);
    }

    // O(1)
    public T dequeue() {
        return items.removeAt(0);
    }


    @Override
    public Iterator<T> iterator() {
        return items.iterator();
    }
}
