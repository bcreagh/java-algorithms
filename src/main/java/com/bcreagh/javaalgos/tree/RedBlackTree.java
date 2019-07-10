package com.bcreagh.javaalgos.tree;

import com.bcreagh.javaalgos.exceptions.DuplicateKeyException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class RedBlackTree<Key extends Comparable, Value> implements Tree<Key, Value> {

    private Node root;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node currentNode) {
        if (currentNode == null) {
            return 0;
        }
        int leftHeight = height(currentNode.left);
        int rightHeight = height(currentNode.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    @Override
    public Value get(Key key) {
        checkForNulls(key, "key");
        Node node = getNode(key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    private Node getNode(Key key) {
        Node currentNode = root;
        while (currentNode != null) {
            int compare = key.compareTo(currentNode.key);
            if (compare == 0) {
                return currentNode;
            }
            if (compare < 0) {
                currentNode = currentNode.left;
            }
            if (compare > 0) {
                currentNode = currentNode.right;
            }
        }
        return null;
    }

    @Override
    public void insert(Key key, Value value) {
        checkForNulls(key, "key");
        checkForNulls(value, "value");
        Node newNode = new Node(key, value);
        root = insertNode(newNode, root);
        size ++;
    }

    @Override
    public void update(Key key, Value value) {
        checkForNulls(key, "key");
        checkForNulls(value, "value");
        Node node = getNode(key);
        if (node == null) {
            throw new NoSuchElementException("The key <" + key.toString() + "> could not be found");
        }
        node.value = value;
    }

    @Override
    public Value delete(Key key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value minKey() {
        Node currentNode = root;
        if (currentNode == null) {
            return null;
        }
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode.value;
    }

    @Override
    public Value maxKey() {
        Node currentNode = root;
        if (currentNode == null) {
            return null;
        }
        while (currentNode.right != null) {
            currentNode = currentNode.right;
        }
        return currentNode.value;
    }

    @Override
    public Value floor(Key key) {
        checkForNulls(key, "key");
        Node floor = floor(key, root);
        if (floor == null) {
            return null;
        }
        return floor.value;
    }

    private Node floor(Key key, Node currentNode) {
        if (currentNode == null) {
            return null;
        }
        int compare = key.compareTo(currentNode.key);
        if (compare == 0) {
            return currentNode;
        }
        if (compare < 0) {
            return floor(key, currentNode.left);
        }
        if (compare > 0) {
            Node rightFloor = floor(key, currentNode.right);
            if (rightFloor != null) {
                return rightFloor;
            }
        }
        return currentNode;
    }

    @Override
    public Value ceiling(Key key) {
        checkForNulls(key, "key");
        Node ceiling = ceiling(key, root);
        if (ceiling == null) {
            return null;
        }
        return ceiling.value;
    }

    private Node ceiling(Key key, Node currentNode) {
        if (currentNode == null) {
            return null;
        }
        int compare = key.compareTo(currentNode.key);
        if (compare == 0) {
            return currentNode;
        }
        if (compare > 0) {
            return ceiling(key, currentNode.right);
        }
        if (compare < 0) {
            Node leftCeiling = ceiling(key, currentNode.left);
            if (leftCeiling != null) {
                return leftCeiling;
            }
        }
        return currentNode;
    }

    @Override
    public Iterable<Key> getKeys() {
        List<Key> orderedKeys = new ArrayList<>(size);
        addNextKey(root, orderedKeys);
        return orderedKeys;
    }

    private void addNextKey(Node currentNode, List<Key> keys) {
        if (currentNode == null) {
            return;
        }
        addNextKey(currentNode.left, keys);
        keys.add(currentNode.key);
        addNextKey(currentNode.right, keys);
    }

    private void checkForNulls(Object arg, String argName) {
        if (arg == null) {
            throw new IllegalArgumentException("\"" + argName + "\" was null, but nulls are not allowed!");
        }
    }

    private Node insertNode(Node newNode, Node currentNode) {
        if (currentNode == null) {
            newNode.color = Color.RED;
            return newNode;
        }
        int compare = newNode.compareTo(currentNode);
        if (compare < 0) {
            currentNode.left = insertNode(newNode, currentNode.left);
        }
        if (compare > 0) {
            currentNode.right = insertNode(newNode, currentNode.right);
        }
        if (compare == 0) {
            throw new DuplicateKeyException(newNode.key.toString());
        }

        if (!isRed(currentNode.left) && isRed(currentNode.right)) {
            return rotateLeft(currentNode);
        }
        if (isRed(currentNode.left) && isRed(currentNode.left.left)) {
            return rotateRight(currentNode);
        }
        if (isRed(currentNode.left) && isRed(currentNode.right)) {
            flipColors(currentNode);
            return currentNode;
        }
        return currentNode;
    }

    private Node rotateRight(Node currentNode) {
        Node left = currentNode.left;
        currentNode.left = left.right;
        left.right = currentNode;
        left.color = currentNode.color;
        currentNode.color = Color.RED;
        return left;
    }

    private Node rotateLeft(Node currentNode) {
        Node right = currentNode.right;
        currentNode.right = right.left;
        right.left = currentNode;
        right.color = currentNode.color;
        currentNode.color = Color.RED;
        return right;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color == Color.RED;
    }

    private void flipColors(Node currentNode) {
        currentNode.color = Color.RED;
        currentNode.left.color = Color.BLACK;
        currentNode.right.color = Color.BLACK;
    }

    private class Node implements Comparable<Node> {
        Key key;
        Value value;
        Node left;
        Node right;
        Color color;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.key.compareTo(o.key);
        }
    }

    private enum Color {
        RED,
        BLACK
    }
}
