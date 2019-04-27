package com.bcreagh.javaalgos;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuickUnionTest {

    private QuickUnion quickUnion;

    @Before
    public void connectNodes() {
        quickUnion = new QuickUnion(10);

        // connect 1, 2, 3, 6, 7, with 1 as root
        quickUnion.union(1, 2);
        quickUnion.union(6, 7);
        quickUnion.union(3, 2);
        quickUnion.union(1, 7);

        // connect 0, 8, 9, with 0 as root
        quickUnion.union(0, 8);
        quickUnion.union(8, 9);
    }

    @Test
    public void connected_shouldReturnTrue_whenNodesAreConnected() {
        quickUnion.union(3, 8);
        assertTrue(quickUnion.connected(1, 2));
        assertTrue(quickUnion.connected(1, 6));
        assertTrue(quickUnion.connected(3, 8));
    }


    @Test
    public void connected_shouldReturnFalse_whenNodesAreNotConnected() {
        assertFalse(quickUnion.connected(1, 8));
        assertFalse(quickUnion.connected(1, 5));
        assertFalse(quickUnion.connected(5, 8));
    }
}
