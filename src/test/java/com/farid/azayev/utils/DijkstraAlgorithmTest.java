package com.farid.azayev.utils;

import com.farid.azayev.SampleGraph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DijkstraAlgorithmTest {
    SampleGraph sg;
    DijkstraAlgorithm da;
    @Before
    public void setUp() throws Exception {
        sg = new SampleGraph();
        da = new DijkstraAlgorithm(sg.graph);
    }

    @Test
    public void getDistance() {
        da.execute(sg.v1);
        assertEquals(sg.e1.getWeight(), da.getDistance(sg.v2));
        assertEquals(sg.e1.getWeight() + sg.e2.getWeight(), da.getDistance(sg.v3));

        da.execute(sg.v2);
        assertEquals(sg.e3.getWeight(), da.getDistance(sg.v1));

        da.execute(sg.v3);
        assertTrue(null == da.getDistance(sg.v2));
        assertEquals(null, da.getDistance(sg.v1));
    }

    @Test
    public void getPath() {
        da.execute(sg.v1);
        System.out.println(da.getPath(sg.v3));
    }
}