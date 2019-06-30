package com.bcreagh.javaalgos.queue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

// this is a Priority Queue which uses a Binary Heap as its underlying data structure

// WARNING: This class breaks if you mutate the data from outside the class
// It is done this way so that I could use Generics. In real world scenarios,
// I would not recommend doing it this way

public class PriorityQueue<T> {

    ArrayList<T> data;
    Comparator<T> comparator;

    public PriorityQueue(T[] data, Comparator<T> comparator) {
        this.comparator = comparator;
        initializeHeap(data);
    }

    public T peek() {
        verifyNotEmpty();
        return data.get(0);
    }

    public T dequeue() {
        verifyNotEmpty();
        T item = data.get(0);
        int lastIndex = data.size() - 1;
        if (data.size() > 1) {
            data.set(0, data.get(lastIndex));
        }
        data.remove(lastIndex); // removing the last index is O(1)
        sink(0);
        return item;
    }

    public void enqueue(T item) {
        if (item == null) {
            throw new IllegalArgumentException("You are not allowed to enqueue a null");
        }
        data.add(item);
        swim(data.size() - 1);
    }

    public int size() {
        return data.size();
    }


    private void initializeHeap(T[] data) {
        this.data = new ArrayList<>(data.length);
        for(int i = 0; i < data.length; i++) {
            enqueue(data[i]);
        }
    }

    private void verifyNotEmpty() {
        if (data.size() == 0) {
            throw new NoSuchElementException();
        }
    }

    private void sink(int index) {
        int current = index;
        int leftChild = leftChild(index);
        int rightChild = rightChild(index);
        while (leftChild > 0) {
            if (rightChild > 0 && compare(current, rightChild) < 0) {
                if (compare(leftChild, rightChild) > 0) {
                    swap(current, leftChild);
                    current = leftChild;
                } else {
                    swap(current, rightChild);
                    current = rightChild;
                }
            } else if (compare(current, leftChild) < 0) {
                swap(current, leftChild);
                current = leftChild;
            } else {
                break;
            }
            leftChild = leftChild(current);
            rightChild = rightChild(current);
        }
    }

    private void swim(int index) {
        int current = index;
        int parent = parent(current);
        while (parent >= 0 && compare(current, parent) > 0) {
            swap(current, parent);
            current = parent;
            parent = parent(current);
        }
    }
    private int compare(int index1, int index2) {
        return comparator.compare(data.get(index1), data.get(index2));
    }

    private void swap(int index1, int index2) {
        T temp = data.get(index1);
        data.set(index1, data.get(index2));
        data.set(index2, temp);
    }

    private int parent(int index) {
        if (index == 0) {
            return -1;
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        int leftChild = (index * 2) + 1;
        if (leftChild >= data.size()) {
            return -1;
        }
        return leftChild;
    }

    private int rightChild(int index) {
        int rightChild = (index * 2) + 2;
        if (rightChild >= data.size()) {
            return -1;
        }
        return rightChild;
    }
}
