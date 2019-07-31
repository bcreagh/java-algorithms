package com.bcreagh.javaalgos.graph;

import com.bcreagh.javaalgos.queue.QueueWithLinkedList;

import java.util.LinkedList;
import java.util.List;

public class BreadthFirstSearch {

    private Digraph digraph;
    private int searchRoot;
    private boolean[] visited;
    private int[] previousVertex;

    public BreadthFirstSearch(Digraph digraph, int searchRoot) {
        this.digraph = digraph;
        this.searchRoot = searchRoot;
        visited = new boolean[digraph.numberOfVertices()];
        previousVertex = new int[digraph.numberOfVertices()];
        bfs(searchRoot);
    }

    private void bfs(int searchRoot) {
        QueueWithLinkedList<Integer> queue = new QueueWithLinkedList<>();
        queue.enqueue(searchRoot);
        visited[searchRoot] = true;

        while (!queue.isEmpty()) {
            int currentVertex = queue.dequeue();
            for (int adjacentVertex: digraph.adjacentVertices(currentVertex)) {
                if (visited[adjacentVertex]) {
                    continue;
                }
                visited[adjacentVertex] = true;
                previousVertex[adjacentVertex] = currentVertex;
                queue.enqueue(adjacentVertex);
            }
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
