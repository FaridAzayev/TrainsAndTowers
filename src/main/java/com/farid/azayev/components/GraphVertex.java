package com.farid.azayev.components;

public class GraphVertex implements Vertex {
    private int id;
    private String name;

    public GraphVertex(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "(" + id + "," + name +")";
    }

    public boolean equals(Vertex v) {
        return this.id == v.getId() && this.name.equals(v.getName());
    }
}
