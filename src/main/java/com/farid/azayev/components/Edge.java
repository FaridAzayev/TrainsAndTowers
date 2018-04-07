package com.farid.azayev.components;

public interface Edge {
    Vertex getHead();

    Vertex getTail();

    int getWeight();

    int compareTo(Edge edge);
}
