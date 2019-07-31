package com.bcreagh.javaalgos.graph;

import java.util.*;

public class DepthFirstSearch {

    private Digraph digraph;
    private int searchRoot;
    private boolean[] visited;
    private int[] previousVertex;

    public DepthFirstSearch(Digraph digraph, int searchRoot) {
        this.digraph = digraph;
        this.searchRoot = searchRoot;
        visited = new boolean[digraph.numberOfVertices()];
        previousVertex = new int[digraph.numberOfVertices()];
        dfs(searchRoot);
    }

    private void dfs(int vertex) {
        visited[vertex] = true;
        for (int adjacentVertex: digraph.adjacentVertices(vertex)) {
            if (visited[adjacentVertex]) {
                continue;
            }
            previousVertex[adjacentVertex] = vertex;
            dfs(adjacentVertex);
        }
    }


    public boolean pathExists(int vertex) {
        return visited[vertex];
    }

    public List<Integer> pathTo(int vertex) {
        if (!visited[vertex]) {
            return null;
        }
        LinkedList<Integer> path = new LinkedList<>();
        int currentVertex = vertex;
        while (visited[currentVertex]) {
            path.add(0, currentVertex);
            if (currentVertex == searchRoot) {
                break;
            }
            currentVertex = previousVertex[currentVertex];
        }
        return path;
    }

}
