package com.farid.azayev.utils;

import com.farid.azayev.components.Graph;
import com.farid.azayev.components.Vertex;

import java.util.*;

public class DijkstraAlgorithm {
	private Set<Vertex> settledNodes;
	private Set<Vertex> unSettledNodes;
	private Map<Vertex, Vertex> predecessors;
	public Map<Vertex, Integer> distance;
	private Graph graph;

	public DijkstraAlgorithm(Graph graph) {
        this.graph = graph;
	}

	public void execute(Vertex source) {
		init();
		distance.put(source, 0);
        settleDistances(source);
	}

    private void settleDistances(Vertex source) {
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Vertex node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    public void executeCycle(Vertex source) {
        init();
        settleDistances(source);
    }

    private Vertex getMinimum(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            if (minimum == null) minimum = vertex;
            else if (getShortestDistance(vertex) < getShortestDistance(minimum)) minimum = vertex;
        }
        return minimum;
    }

    private int getShortestDistance(Vertex destination) {
        Integer d = distance.get(destination);
        if (d == null) return Integer.MAX_VALUE;
        else return d;
    }

    public Integer getDistance(Vertex destination){
	    if(distance.get(destination) == null || distance.get(destination) ==Integer.MAX_VALUE) return null;
	    else return distance.get(destination);
    }

    private void init() {
		settledNodes = new HashSet<>();
		unSettledNodes = new HashSet<>();
		distance = new HashMap<>();
		predecessors = new HashMap<>();
	}

    private void findMinimalDistances(Vertex node) {
        graph.getNeighborsAtVertex(node).forEach(x -> {
            if (getShortestDistance(x.getTail()) > getShortestDistance(node) + x.getWeight()) {
                distance.put(x.getTail(), getShortestDistance(node) + x.getWeight());
                predecessors.put(x.getTail(), node);
                unSettledNodes.add(x.getTail());
            }
        });
    }

    private int cycleCount = 0;
    public LinkedList<Vertex> getPathCycle(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<>();
        Vertex step = target;
        if (predecessors.get(step) == null)  return null;

        path.add(step);
        while (predecessors.get(step) != null) {
            if (step == target) if(path.contains(step) && cycleCount>0) break; else cycleCount++;
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }

    public LinkedList<Vertex> getPath(Vertex target) {
		LinkedList<Vertex> path = new LinkedList<>();
		Vertex step = target;
		if (predecessors.get(step) == null)  return null;

		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		Collections.reverse(path);
		return path;
	}
}