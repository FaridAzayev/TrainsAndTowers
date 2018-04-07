package com.farid.azayev.utils;

import com.farid.azayev.components.Graph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphAnalyzerTest {
    private Graph graph;
    private GraphParser gp;
    private GraphAnalyzer analyzer;
    @Before
    public void setUp() throws Exception {
        gp = new GraphParser("AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7");
        graph = gp.getGraph();
        analyzer = new GraphAnalyzer(graph);
    }

    @Test
    public void pathCount() {
        assertEquals(1, analyzer.pathCount("A","B",1));
        assertEquals(0, analyzer.pathCount("B","A",1));

        assertEquals(0, analyzer.pathCount("D","A",3));
        assertEquals(2, analyzer.pathCount("A","D",3));
        assertEquals(3, analyzer.pathCount("A","D",5));

        assertEquals(2, analyzer.pathCount("C","C",3));
    }

    @Test
    public void pathCountExact() {
        assertEquals(3, analyzer.pathCountExact("A","C",4));
    }

    @Test
    public void pathCountWeightLimit() {
        assertEquals(1, analyzer.pathCountWeightLimit("A","C",10));
        assertEquals(2, analyzer.pathCountWeightLimit("A","C",14));
        assertEquals(3, analyzer.pathCountWeightLimit("A","C",15));
    }
}