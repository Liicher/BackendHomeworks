package edu.hw9.task3;

import java.util.ArrayList;
import java.util.List;

// Класс графа
class Graph {
    private List<Node> nodes;

    public Graph() {
        this.nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void addEdge(Node node1, Node node2) {
        node1.addAdjacentNode(node2);
        node2.addAdjacentNode(node1);
    }

    public Node getNode(int i) {
        return nodes.get(i);
    }
}
