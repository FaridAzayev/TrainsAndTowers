package com.farid.azayev.components;

import java.util.ArrayList;
import java.util.List;

public class OrientedGraph implements Graph {
    private List<Edge>[] adjacentEdges;
    private List<Edge> edges;
    private List<Vertex> vertices;

    public OrientedGraph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
        this.adjacentEdges = new ArrayList[vertices.size()];
        setAdjacentEdges(edges, vertices.size());
    }

    private void setAdjacentEdges(List<Edge> edges, int vertexCount) {
        for(int v = 0; v < vertexCount; v++) this.adjacentEdges[v] = new ArrayList<>();
        edges.forEach(x-> adjacentEdges[x.getHead().getId()].add(x));
    }

    public List<Edge>[] getAdjacentEdges() {
        return adjacentEdges;
    }

    public List<Edge> getNeighborsAtVertex(Vertex vertex) {
        return this.adjacentEdges[vertex.getId()];
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public Vertex getVertexByName(String name) {
        for (Vertex v : vertices) if (v.getName().equals(name)) return v;
        return null;
    }
}
