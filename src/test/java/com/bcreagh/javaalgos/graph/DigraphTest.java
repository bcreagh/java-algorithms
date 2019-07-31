package com.bcreagh.javaalgos.graph;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DigraphTest {

    private static final int DEFAULT_NUM_VERTICES = 10;

    private Digraph digraph;

    @Before
    public void setup() {
        digraph = new Digraph(DEFAULT_NUM_VERTICES);
    }

    @Test
    public void numberOfVertices_returnsNumberOfVertices() {
        assertEquals(DEFAULT_NUM_VERTICES, digraph.numberOfVertices());
    }

    @Test
    public void addEdge_returnsNumberOfEdges() {
        digraph.addEdge(2, 5);
        digraph.addEdge(3, 5);
        digraph.addEdge(6, 1);

        assertEquals(3, digraph.numberOfEdges());
    }

    @Test
    public void addEdge_addsEdge() {
        digraph.addEdge(2, 5);
        digraph.addEdge(2, 8);
        digraph.addEdge(3, 5);

        assertEquals(2, digraph.adjacentVertices(2).size());
        assertTrue(digraph.adjacentVertices(2).contains(5));
        assertTrue(digraph.adjacentVertices(2).contains(5));

        assertEquals(1, digraph.adjacentVertices(3).size());
        assertTrue(digraph.adjacentVertices(3).contains(5));


        assertEquals(0, digraph.adjacentVertices(5).size());

    }

    @Test(expected = IllegalArgumentException.class)
    public void validateVertex_throwsException_whenLessThan0() {
        digraph.validateVertex(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateVertex_throwsException_whenGreaterThanNumberVertices() {
        digraph.validateVertex(DEFAULT_NUM_VERTICES + 1);
    }

    @Test
    public void validateVertex_doesNothing_whenVertexExists() {
        digraph.validateVertex(DEFAULT_NUM_VERTICES - 1);
    }

    @Test
    public void reverse_returnsCorrectDigraph() {
        digraph.addEdge(2, 5);
        digraph.addEdge(2, 8);
        digraph.addEdge(3, 5);

        Digraph reversed = digraph.reverse();

        assertEquals(2, reversed.adjacentVertices(5).size());
        assertTrue(reversed.adjacentVertices(5).contains(2));
        assertTrue(reversed.adjacentVertices(5).contains(3));

        assertEquals(1, reversed.adjacentVertices(8).size());
        assertTrue(reversed.adjacentVertices(8).contains(2));


        assertEquals(0, reversed.adjacentVertices(2).size());
    }
}
