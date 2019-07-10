package com.bcreagh.javaalgos.tree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RedBlackTreeTest extends TreeTest {

    @Test
    public void height_returns0_treeIsEmpty() {
        tree = getEmptyTestTree();
        assertEquals(0, tree.height());
    }

    @Test
    public void height_returnsCorrectHeight_treeIsPopulated() {
        assertEquals(4, tree.height());
    }

    @Override
    protected Tree<Integer, Integer> getEmptyTestTree() {
        return new RedBlackTree<>();
    }
}
