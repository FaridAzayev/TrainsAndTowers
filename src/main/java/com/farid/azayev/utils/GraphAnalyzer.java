package com.farid.azayev.utils;

import com.farid.azayev.components.Edge;
import com.farid.azayev.components.Graph;
import com.farid.azayev.components.Vertex;

public class GraphAnalyzer {
    private Graph graph;
    private int count;

    public GraphAnalyzer(Graph graph) {
        this.graph = graph;
    }

    public int pathCount(String startVertex, String endVertex, int stopsCount) {
        resetCount();
        dfs(graph.getVertexByName(startVertex),graph.getVertexByName(endVertex),stopsCount,0);
        return this.count;
    }

    public int pathCountExact(String startVertex, String endVertex, int length) {
        resetCount();
        dfsExact(graph.getVertexByName(startVertex),graph.getVertexByName(endVertex),length,0);
        return this.count;
    }

    public int pathCountWeightLimit(String startVertex, String endVertex, int weightLimit) {
        resetCount();
        dfsExactWeight(graph.getVertexByName(startVertex),graph. getVertexByName(endVertex), weightLimit, 0);
        return this.count;
    }

    private void dfs(Vertex start, Vertex search, int limit, int depth) {
        if (depth == limit) return;
        graph.getNeighborsAtVertex(start).forEach(x->{
            if(x.getTail().equals(search)) count++;
            else dfs(x.getTail(),search,limit,depth+1);
        });
    }

    private void dfsExact(Vertex start, Vertex search, int length, int depth) {
        if (depth == length) {
            if(start.equals(search))count++;
            return;
        }
        graph.getNeighborsAtVertex(start).forEach(x-> dfsExact(x.getTail(), search, length,depth+1));
    }

    private void dfsExactWeight(Vertex start, Vertex search, int weightLimit, int pathWeight) {
        if (pathWeight >= weightLimit) return;
        graph.getNeighborsAtVertex(start).forEach(x->{
            int currentWeight = pathWeight + x.getWeight();
            if(x.getTail().equals(search) && currentWeight < weightLimit) count++;
            dfsExactWeight(x.getTail(), search, weightLimit, currentWeight);
        });
    }

    private void resetCount() {
        this.count = 0;
    }
}
