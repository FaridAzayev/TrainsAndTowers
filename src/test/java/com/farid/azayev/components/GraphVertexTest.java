package com.farid.azayev.components;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphVertexTest {
    private String name = "A";
    private int id = 0;
    private Vertex v;
    @Before
    public void setUp() throws Exception {
        v = new GraphVertex(id,name);
    }

    @Test
    public void getId() {
        assertEquals(id,v.getId());
    }

    @Test
    public void getName() {
        assertEquals(name,v.getName());
    }

    @Test
    public void toStringTest() {
        assertEquals("("+id+","+name+")",v.toString());
    }

    @Test
    public void equals() {
        Vertex newVertex = new GraphVertex(0,"A");
        assertTrue(v.equals(newVertex));
    }
}