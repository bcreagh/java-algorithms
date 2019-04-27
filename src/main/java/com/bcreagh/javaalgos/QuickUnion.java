package com.bcreagh.javaalgos;

public class QuickUnion {

    private int[] nodes;
    private int[] sizes;

    public QuickUnion(int totalNumberOfNodes) {
        nodes = new int[totalNumberOfNodes];
        sizes = new int[totalNumberOfNodes];
        for (int i = 0; i < totalNumberOfNodes; i++) {
            nodes[i] = i;
            sizes[i] = 1;
        }
    }

    public void union(int node1, int node2) {
        int root1 = getRoot(node1);
        int root2 = getRoot(node2);
        if (sizes[root1] < sizes[root2]) {
            join(root1, root2);
        } else {
            join(root2, root1);
        }
    }

    private void join(int child, int root) {
        nodes[child] = root;
        sizes[root] += sizes[child];
    }

    public boolean connected(int node1, int node2) {
        return getRoot(node1) == getRoot(node2);
    }

    private int getRoot(int node) {
        int rootNode = nodes[node];
        while (rootNode != nodes[rootNode]) {
            rootNode = nodes[rootNode];
        }
        return rootNode;
    }

}
