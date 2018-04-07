package com.farid.azayev.utils;

import com.farid.azayev.components.Graph;
import com.farid.azayev.components.OrientedGraph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphParserTest {
    private Graph graph;
    private GraphParser gp;
    @Before
    public void setUp() throws Exception {
        gp = new GraphParser("AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7");
        graph = gp.getGraph();
    }

    @Test
    public void getGraph() {
        assertTrue(gp.getGraph()!=null);
        assertEquals(OrientedGraph.class.getName(), gp.getGraph().getClass().getName());
    }

    @Test
    public void getVertices() {
        assertEquals(5, gp.getVertices().size());
        assertEquals(graph.getVertices().size(), gp.getVertices().size());
    }

    @Test
    public void getEdges() {
        assertEquals(9, gp.getEdges().size());
        assertEquals(graph.getEdges().size(), gp.getEdges().size());
    }
}