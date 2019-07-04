package com.bcreagh.javaalgos.tree;

public class RedBlackTree<Key, Value> implements Tree<Key, Value> {
    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int height() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value get(Key key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insert(Key key, Value value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Key key, Value value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value delete(Key key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value minKey() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value maxKey() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value floor(Key key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Value ceiling(Key key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Key> getKeys() {
        throw new UnsupportedOperationException();
    }

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        Color color;
    }

    private enum Color {
        RED,
        BLACK
    }
}
