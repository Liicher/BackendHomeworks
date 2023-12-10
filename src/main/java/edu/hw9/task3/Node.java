package edu.hw9.task3;

import java.util.ArrayList;
import java.util.List;

// Класс узла графа
class Node {
    private int value;
    private List<Node> adjacentNodes;

    public Node(int value) {
        this.value = value;
        this.adjacentNodes = new ArrayList<>();
    }

    public void addAdjacentNode(Node node) {
        this.adjacentNodes.add(node);
    }

    public List<Node> getAdjacentNodes() {
        return adjacentNodes;
    }
}
