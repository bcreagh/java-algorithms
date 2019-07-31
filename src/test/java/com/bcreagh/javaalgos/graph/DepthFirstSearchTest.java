package com.bcreagh.javaalgos.graph;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class DepthFirstSearchTest {

    private Digraph digraph;
    private DepthFirstSearch dfs;
    private int[][] defaultEdges;

    @Before
    public void setup() {
        dfs = null;
        digraph = new Digraph(10);
        defaultEdges = getDefaultEdges();
        for (int[] edge: defaultEdges) {
            digraph.addEdge(edge[0], edge[1]);
        }
    }

    @Test
    public void sys_test() {
        dfs = new DepthFirstSearch(digraph, 3);

        ensurePathsExist(4, 7, 2, 8, 1, 0);
        ensurePathsDoNotExist(6, 5, 9);

        int[] expectedPathTo0 = new int[] {3, 4, 7, 1, 0};
        List<Integer> pathTo0 = dfs.pathTo(0);

        assertEquals(expectedPathTo0.length, pathTo0.size());

        for (int i = 0; i < expectedPathTo0.length; i++) {
            assertEquals(expectedPathTo0[i], (int) pathTo0.get(i));
        }

        assertNull(dfs.pathTo(9));
    }

    private int[][] getDefaultEdges() {
        return new int[][] {
                new int[] {5, 3},
                new int[] {3, 4},
                new int[] {4, 7},
                new int[] {4, 2},
                new int[] {7, 1},
                new int[] {1, 4},
                new int[] {1, 0},
                new int[] {2, 8},
                new int[] {9, 2}
        };
    }

    private void ensurePathsExist(int... vertices) {
        for (int vertex: vertices) {
            assertTrue(dfs.pathExists(vertex));
        }
    }

    private void ensurePathsDoNotExist(int... vertices) {
        for (int vertex: vertices) {
            assertFalse(dfs.pathExists(vertex));
        }
    }

}
