package com.bcreagh.javaalgos.sort;

import java.util.Arrays;

public class HeapSort<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public T[] sort(T[] input) {
        int heapSize = 0;
        // add all items to the heap
        for (int i = 0; i < input.length; i++) {
            heapSize++;
            swim(input, i);
        }
        // remove largest item from heap and put it into its final position
        for (int j = 0; j < input.length; j++) {
            swap(input, 0, heapSize - 1);
            heapSize--;
            sink(input, 0, heapSize);
        }
        return input;
    }

    private void sink(T[] input, int index, int heapSize) {
        int current = index;
        int leftChild = leftChild(index, heapSize);
        int rightChild = rightChild(index, heapSize);
        while (leftChild > 0) {
            if (rightChild > 0 && input[current].compareTo(input[rightChild]) < 0) {
                if (input[leftChild].compareTo(input[rightChild]) > 0) {
                    swap(input, current, leftChild);
                    current = leftChild;
                } else {
                    swap(input, current, rightChild);
                    current = rightChild;
                }
            } else if (input[current].compareTo(input[leftChild]) < 0) {
                swap(input, current, leftChild);
                current = leftChild;
            } else {
                break;
            }
            leftChild = leftChild(current, heapSize);
            rightChild = rightChild(current, heapSize);
        }
    }

    protected void swim(T[] input, int index) {
        int current = index;
        int parent = parent(current);
        while (parent >= 0 && input[current].compareTo(input[parent]) > 0) {
            swap(input, current, parent);
            current = parent;
            parent = parent(current);
        }
    }

    private int parent(int index) {
        if (index == 0) {
            return -1;
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index, int heapSize) {
        int leftChild = (index * 2) + 1;
        if (leftChild >= heapSize) {
            return -1;
        }
        return leftChild;
    }

    private int rightChild(int index, int heapSize) {
        int rightChild = (index * 2) + 2;
        if (rightChild >= heapSize) {
            return -1;
        }
        return rightChild;
    }

    private void swap(T[] input, int index1, int index2) {
        T temp = input[index1];
        input[index1] = input[index2];
        input[index2] = temp;
    }

}
