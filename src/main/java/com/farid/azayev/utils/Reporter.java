package com.farid.azayev.utils;

import com.farid.azayev.components.Edge;
import com.farid.azayev.components.Graph;
import com.farid.azayev.components.Vertex;
import com.farid.azayev.config.TrainsAndTowersAppProperties;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Reporter {
    TrainsAndTowersAppProperties prop = new TrainsAndTowersAppProperties();
    final String routeSeparationSymbol = prop.getProperty("route-separation-symbol");
    final String outputFormat = prop.getProperty("output-format");
    final String noRouteMessage = prop.getProperty("no-route-message");

    private String[] routeDistances = {"A-B-C","A-D","A-D-C","A-E-B-C-D","A-E-D"};
    private String[] shortestPaths  = {"A-C"};
    private String[] routeCountDistancesMax = {"C-C:3"};
    private String[] routeCountExactStops = {"A-C:4"};
    private String[] shortestPathCycles ={"B-B"};
    private String[] routeCountDistancesMaxCycle = {"C-C:30"};

    private int outputCount;
    private Graph graph;
    private GraphAnalyzer analyzer;

    public Reporter(Graph graph) throws IOException, IOException {
        this.graph = graph;
        this.analyzer = new GraphAnalyzer(graph);
        this.outputCount = 1;
    }

    public void report(){
        reportRouteDistances();
        reportRouteCountDistancesMax();
        reportRouteCountExactStops();
        reportShortestPaths();
        reportShortestPathCycles();
        reportRouteCountDistancesMaxCycle();
    }

    private void reportShortestPathCycles() {
        for(String path: shortestPathCycles){
            int pathLength = 0;
            String[] vertexList = getVertexNameList(routeSeparationSymbol, path);
            for (int i = 0; i < vertexList.length - 1; i++) {
                Vertex source = graph.getVertexByName(vertexList[i]);
                Vertex destination = graph.getVertexByName(vertexList[i + 1]);
                DijkstraAlgorithm da = new DijkstraAlgorithm(graph);
                da.executeCycle(source);
                LinkedList<Vertex> p = da.getPathCycle(source);

                for(int w=0; w<p.size()-1; w++){
                    List<Edge> neighbours = graph.getNeighborsAtVertex(p.get(w));
                    for(int y=0; y < neighbours.size(); y++){
                        if (neighbours.get(y).getTail()==p.get(w+1)) pathLength+=neighbours.get(y).getWeight();
                    }
                }
            }
            displayMessage(outputFormat, outputCount++, String.valueOf((pathLength != 0) ? pathLength : noRouteMessage));
        }
    }

    private String[] getVertexNameList(String routeSeparationSymbol, String path) {
        return path.split(routeSeparationSymbol);
    }

    private void reportRouteDistances() {
        for(String path: routeDistances){
            int pathWeight = 0;
            String[] vertexNameList = getVertexNameList(routeSeparationSymbol, path);
            for (int i=0 ; i< vertexNameList.length-1 ; i++){
                Vertex source = graph.getVertexByName(vertexNameList[i]);
                int dist = 0;
                for (Edge edge : graph.getNeighborsAtVertex(source))
                    if(edge.getTail().getName().equals(vertexNameList[i+1])) dist = edge.getWeight();
                if(dist == 0){
                    pathWeight = 0;
                    break;
                }else pathWeight += dist;
            }
            displayMessage(outputFormat, this.outputCount++, String.valueOf((pathWeight!=0) ? pathWeight : noRouteMessage));
        }
    }

    private void displayMessage(String outputFormat, int outputCount, String message) {
        System.out.println(String.format(outputFormat,outputCount,message));
    }

    private void reportRouteCountDistancesMax() {
        for (String route : routeCountDistancesMax) {
            int count = analyzer.pathCount(route.split(":")[0].split("-")[0],
                    route.split(":")[0].split("-")[1], new Integer(route.split(":")[1]));
            displayMessage(outputFormat, this.outputCount++, String.valueOf((count != 0) ? count : noRouteMessage));
        }
    }

    private void reportRouteCountExactStops() {
        for (String route : routeCountExactStops) {
            int count = analyzer.pathCountExact(route.split(":")[0].split("-")[0],
                    route.split(":")[0].split("-")[1], new Integer(route.split(":")[1]));
            displayMessage(outputFormat, this.outputCount++, String.valueOf((count != 0) ? count : noRouteMessage));
        }
    }

    private void reportShortestPaths() {
        for (String path : shortestPaths) {
            int pathWeight = 0;
            String[] vertexList = getVertexNameList(routeSeparationSymbol, path);
            for (int i = 0; i < vertexList.length - 1; i++) {
                Vertex source = graph.getVertexByName(vertexList[i]);
                Vertex destination = graph.getVertexByName(vertexList[i + 1]);
                DijkstraAlgorithm da = new DijkstraAlgorithm(graph);
                da.execute(source);
                Integer dist = da.getDistance(destination);
                if (dist == null) {
                    pathWeight = 0;
                    break;
                } else pathWeight += dist;
            }
            displayMessage(outputFormat, outputCount++, String.valueOf((pathWeight != 0) ? pathWeight : noRouteMessage));
        }
    }

    private void reportRouteCountDistancesMaxCycle() {
        for (String route : routeCountDistancesMaxCycle) {
            int count = analyzer.pathCountWeightLimit(route.split(":")[0].split("-")[0],
                    route.split(":")[0].split("-")[1], new Integer(route.split(":")[1]));
            displayMessage(outputFormat, outputCount++, String.valueOf((count != 0) ? count : noRouteMessage));
        }
    }
}