package com.farid.azayev.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Graph {
    public List<Edge>[] getAdjacentEdges();

    public List<Edge> getNeighborsAtVertex(Vertex vertex);

    public List<Edge> getEdges();

    public List<Vertex> getVertices();

    public Vertex getVertexByName(String name);
}
