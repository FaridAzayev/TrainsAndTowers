package com.farid.azayev.components;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphEdgeTest {
    private Vertex v1;
    private Vertex v2;
    private Edge edge;
    private Edge heavyEdge;
    private int weight = 10;


    @Before
    public void setUp() throws Exception {
        v1 = new GraphVertex(0,"A");
        v2 = new GraphVertex(1,"B");
        edge = new GraphEdge(v1, v2, weight);
        heavyEdge = new GraphEdge(new GraphVertex(2,"C"), new GraphVertex(3,"D"), 100);
    }

    @Test
    public void getHead() {
        assertEquals(v1, edge.getHead());
    }

    @Test
    public void getTail() {
        assertEquals(v2, edge.getTail());
    }

    @Test
    public void getWeight() {
        assertEquals(weight, edge.getWeight());
    }

    @Test
    public void compareTo() {
        assertEquals(-1,edge.compareTo(heavyEdge));
    }

    @Test
    public void toStringTest() {
        assertEquals("{"+v1+","+v2+"}:"+weight, edge.toString());
    }
}