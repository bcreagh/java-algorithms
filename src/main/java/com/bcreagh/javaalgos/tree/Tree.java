package com.bcreagh.javaalgos.tree;

public interface Tree<Key, Value> {
    int size();
    int height();
    Value get(Key key);
    void insert(Key key, Value value);
    void update(Key key, Value value);
    Value delete (Key key);
    Value minKey();
    Value maxKey();
    Value floor(Key key);
    Value ceiling(Key key);
    Iterable<Key> getKeys();
}
