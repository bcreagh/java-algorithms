package com.bcreagh.javaalgos.linkedlist;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {
    private int length = 0;
    private Node<T> first;
    private Node<T> last;

    // O(n)
    // O(1) for adding at position 0
    public void addAt(int position, T item) {
        if (position < 0 || position > length) {
            throw new IndexOutOfBoundsException(
                    String.format("The length of the list is %d, the index you provided was %d", length, position));
        }
        Node<T> newNode = new Node<>(item);
        if (position == 0) {
            newNode.next = first;
            first = newNode;
        } else {
            Node<T> previousNode = getNodeAt(position -1);
            newNode.next = previousNode.next;
            previousNode.next = newNode;
        }
        if (position == length) {
            last = newNode;
        }
        length += 1;
    }

    // O(1)
    public void add(T item) {
        Node<T> newNode = new Node<>(item);
        Node<T> oldLast = last;
        last = newNode;
        int oldLength = length;
        length += 1;
        if (oldLength == 0) {
            first = newNode;
            return;
        }
        oldLast.next = newNode;
    }

    // O(n)
    public T getAt(int position) {
        Node<T> node = getNodeAt(position);
        return node.item;
    }

    private Node<T> getNodeAt(int position) {
        if (position >= length || position < 0) {
            throw new IndexOutOfBoundsException(
                    String.format("The length of the list is %d, the index you provided was %d", length, position));
        }
        Node<T> current = first;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current;
    }

    // O(1)
    public T getLast() {
        if (length == 0) {
            throw new IndexOutOfBoundsException("You cannot get the last item because the list is empty!");
        }
        return last.item;
    }

    // O(n)
    // O(1) for removing at position 0
    public T removeAt(int position) {
        if (position >= length || position < 0) {
            throw new IndexOutOfBoundsException(
                    String.format("The length of the list is %d, the index you provided was %d", length, position));
        }
        Node<T> nodeToRemove;
        if (position == 0) {
            nodeToRemove = first;
            if (length == 1) {
                first = null;
                last = null;
            } else {
                first = first.next;
            }
        } else {
            Node<T> previousNode = getNodeAt(position - 1);
            nodeToRemove = previousNode.next;
            previousNode.next = nodeToRemove.next;
            if (position == length - 1)  {
                last = previousNode;
            }
        }
        length -= 1;
        return nodeToRemove.item;
    }

    // O(1)
    public int getLength() {
        return length;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {

        Node<T> currentNode = LinkedList.this.first;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            T item = currentNode.item;
            currentNode = currentNode.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node<T> {
        Node<T> next;
        T item;

        Node(T item) {
            this.item = item;
        }
    }
}
