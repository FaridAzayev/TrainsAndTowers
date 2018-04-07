package com.farid.azayev.components;

public class GraphEdge implements Edge {
    private Vertex head;
    private Vertex tail;
    private int weight;

    public GraphEdge(Vertex head, Vertex tail, int weight) {
        this.head = head;
        this.tail = tail;
        this.weight = weight;
    }

    public Vertex getHead() {
        return head;
    }

    public Vertex getTail() {
        return tail;
    }

    public int getWeight() {
        return weight;
    }

    public int compareTo(Edge edge) {
        return (new Integer(this.weight)).compareTo(new Integer(edge.getWeight()));
    }

    @Override
    public String toString() {
        return "{" + head + "," + tail + "}:" + weight;
    }
}
