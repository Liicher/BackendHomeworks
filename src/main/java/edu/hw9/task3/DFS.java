package edu.hw9.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Класс потока для поиска в глубину
class DFS extends Thread {
    private Graph graph;
    private List<Boolean> visitedNodes;
    private List<Integer> solveWay;
    private int endPosition;

    public DFS(Graph graph, int endPosition) {
        this.graph = graph;
        this.endPosition = endPosition;
        this.solveWay = new ArrayList<>();
        this.visitedNodes = new ArrayList<>();
    }

    @Override
    public void run() {
        Node startNode = graph.getNode(0);
        dfs(0);
    }

    private List<Integer> dfs(int node) {
        visitedNodes.set(node, true);
        solveWay.add(node);

        if (node == endPosition) {
            return solveWay;
        }

        return solveWay;
    }
}
