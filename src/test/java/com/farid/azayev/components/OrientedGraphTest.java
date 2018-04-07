package com.farid.azayev.components;

import com.farid.azayev.SampleGraph;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class OrientedGraphTest {
    private SampleGraph sg;
    private Graph graph;

    @Before
    public void setUp() throws Exception {
        sg = new SampleGraph();
        graph = sg.graph;
    }

    @Test
    public void getAdjacentEdges() {
        assertEquals(3, graph.getAdjacentEdges().length);
        assertEquals(1, graph.getAdjacentEdges()[0].size());
        assertEquals(2, graph.getAdjacentEdges()[1].size());
        assertEquals(0, graph.getAdjacentEdges()[2].size());
    }

    @Test
    public void getNeighborsAtVertex() {
        assertTrue(graph.getNeighborsAtVertex(sg.v1).contains(sg.e1));
        assertTrue(!graph.getNeighborsAtVertex(sg.v1).contains(sg.e2));
        assertTrue(graph.getNeighborsAtVertex(sg.v2).contains(sg.e3));
    }

    @Test
    public void getEdges() {
        assertEquals(sg.edges.size(), graph.getEdges().size());
    }

    @Test
    public void getVertices() {
        assertEquals(sg.vertices.size(), graph.getVertices().size());
    }

    @Test
    public void getVertexByName() {
        assertEquals(sg.v1, graph.getVertexByName("A"));
    }
}