package com.bcreagh.javaalgos.resizingarray;

import java.util.Iterator;

public class ResizingArray<T> implements Iterable<T> {

    private T[] items;
    private int length = 0;

    public ResizingArray() {
        items = newArray(10);
    }

    public ResizingArray(int initialLength) {
        items = newArray(initialLength);
    }

    private T[] newArray(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("The length must be greater than 0");
        }
        @SuppressWarnings("unchecked")
        T[] newArray = (T[]) new Object[length];
        return newArray;
    }

    public int getLength() {
        return length;
    }

    public int getCapacity() {
        return items.length;
    }

    public T get(int index) {
        checkIndexIsInBounds(index);
        return items[index];
    }

    public void add(T item) {
        if (length == items.length) {
            resizeArray(length * 2);
        }
        items[length] = item;
        length += 1;
    }

    // O(n)
    public T removeAt(int index) {
        checkIndexIsInBounds(index);
        T itemAtIndex = items[index];
        for (int i = index; i + 1 < length; i++) {
            items[i] = items[i + 1];
        }
        items[length - 1] = null;
        length -= 1;
        if((items.length / 4) >= length) {
            resizeArray(items.length / 2);
        }
        return itemAtIndex;
    }

    // O(n)
    private void resizeArray(int newLength) {
        T[] newItems = newArray(newLength);
        for (int i = 0; i < length; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    private void checkIndexIsInBounds(int index) {
        if (index > length - 1 || index < 0) {
            String error = "The index you provided was out of bounds. The length of the " +
                    "ResizingArray is %d, the index you provided was %d";
            throw new IndexOutOfBoundsException(String.format(error, length, index));
        }
    }

    public Iterator<T> iterator() {
        return new ResizingArrayIterator<>(this);
    }

    private class ResizingArrayIterator<T>  implements Iterator<T> {

        private ResizingArray<T> array;
        private int index;

        public ResizingArrayIterator(ResizingArray<T> array) {
            this.array = array;
        }

        @Override
        public boolean hasNext() {
            return index < array.length;
        }

        @Override
        public T next() {
            T next = array.get(index);
            index += 1;
            return next;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
