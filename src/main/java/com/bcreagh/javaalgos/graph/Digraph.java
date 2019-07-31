package com.bcreagh.javaalgos.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Simple directed graph data structure. Vertices are represented as
 * indices in an array. The edges are stored in the values of this array.
 */
public class Digraph {

    private List<Integer>[] edges;
    private int numberOfEdges = 0;

    @SuppressWarnings("unchecked")
    public Digraph(int numberOfVertices) {
        if (numberOfVertices < 0) {
            throw new IllegalArgumentException("Number of vertices must be greater than 0");
        }
        edges = (List<Integer>[]) new List[numberOfVertices];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<>();
        }
    }

    public void addEdge(int vertex1, int vertex2) {
        validateVertex(vertex1);
        validateVertex(vertex2);
        numberOfEdges += 1;
        edges[vertex1].add(vertex2);
    }

    public int numberOfVertices() {
        return edges.length;
    }

    public int numberOfEdges() {
        return numberOfEdges;
    }

    public Collection<Integer> adjacentVertices(int vertex) {
        validateVertex(vertex);
        return edges[vertex];
    }

    public Digraph reverse() {
        Digraph reversed = new Digraph(edges.length);
        for (int i = 0; i < edges.length; i++) {
            for (int adjacentVertex: edges[i]) {
                reversed.edges[adjacentVertex].add(i);
            }
        }
        return reversed;
    }

    void validateVertex(int v) {
        if (v < 0 || v >= edges.length) {
            throw new IllegalArgumentException("Vertex does not exist: " + v);
        }
    }
}
