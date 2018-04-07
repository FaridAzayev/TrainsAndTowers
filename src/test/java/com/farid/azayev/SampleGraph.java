package com.farid.azayev;

import com.farid.azayev.components.*;

import java.util.ArrayList;
import java.util.List;

public class SampleGraph{
        public Graph graph;
        public List<Edge> edges;
        public List<Vertex> vertices;
        public Vertex v1;
        public Vertex v2;
        public Vertex v3;
        public Edge e1;
        public Edge e2;
        public Edge e3;

        public SampleGraph() {
            createVertices();
            createEdges();
            graph = new OrientedGraph(vertices, edges);
        }

        private void createEdges() {
            edges = new ArrayList<>();

            e1 = new GraphEdge(v1, v2, 1);
            edges.add(e1);

            e2 = new GraphEdge(v2, v3, 2);
            edges.add(e2);

            e3 = new GraphEdge(v2, v1, 3);
            edges.add(e3);
        }

        private void createVertices() {
            vertices = new ArrayList<>();

            v1 = new GraphVertex(0,"A");
            vertices.add(v1);

            v2 = new GraphVertex(1,"B");
            vertices.add(v2);

            v3 = new GraphVertex(2,"C");
            vertices.add(v3);
        }
    }
