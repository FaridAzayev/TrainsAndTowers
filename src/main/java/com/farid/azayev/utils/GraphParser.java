package com.farid.azayev.utils;

import com.farid.azayev.components.*;
import com.farid.azayev.config.TrainsAndTowersAppProperties;

import java.io.IOException;
import java.util.*;

public class GraphParser {
    private TrainsAndTowersAppProperties prop = new TrainsAndTowersAppProperties();
    private String edgeSeparationSymbol = prop.getProperty("edge-separation-symbol");
    private Map<String, Vertex> verticesMap;
    private List<Vertex> vertices;
    private List<String> edgesList;
    private List<Edge> edges;
    private Graph graph;
    private int vertexCount = 0;

    public GraphParser(String edgeListStr) throws IOException {
        init(edgeListStr);
        setup();
        graph = new OrientedGraph(vertices, edges);
    }

    private void init(String edgeListStr) {
        edgesList = new ArrayList<>(Arrays.asList((edgeListStr.trim()).split(edgeSeparationSymbol)));
        edges = new ArrayList<>();
        vertices = new LinkedList<>();
        verticesMap = new HashMap<>();
    }

    private void setup() {
        edgesList.forEach(x-> {
            Vertex v1 = getVertex(getFirstVertex(x));
            Vertex v2 = getVertex(getSecondVertex(x));
            edges.add(new GraphEdge(v1,v2,new Integer(getWeight(x))));
        });
    }

    private Vertex getVertex(String first) {
        Vertex v;
        if(!verticesMap.containsKey(first)) {
            v = new GraphVertex(vertexCount++, first);
            verticesMap.put(first, v);
            vertices.add(v);
        }else{
            v = verticesMap.get(first);
        }
        return v;
    }

    private String getFirstVertex(String edge){
        return edge.substring(0,1);
    }

    private String getSecondVertex(String edge){
        return edge.substring(1,2);
    }

    private String getWeight(String edge){
        return edge.substring(2,edge.length());
    }

    public Graph getGraph() {
        return graph;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
