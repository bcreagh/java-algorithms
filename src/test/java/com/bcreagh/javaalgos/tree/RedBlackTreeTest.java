package com.bcreagh.javaalgos.tree;

import org.junit.Before;

public class RedBlackTreeTest extends TreeTest {


    @Override
    protected Tree<Integer, Integer> getTestTree() {
        return new RedBlackTree<>();
    }
}
