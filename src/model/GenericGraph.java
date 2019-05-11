package model;

import java.util.*;

public class GenericGraph<T> {

	private Map<Vertex, ArrayList<Edge>> graphMap;

	public GenericGraph(ArrayList<Vertex> vertices) {

		// Creating graph using Adjacency List---------------------------
		graphMap = new HashMap<Vertex, ArrayList<Edge>>();
		for (int i = 0; i < vertices.size(); i++) {

			ArrayList<Edge> neighbours = new ArrayList<Edge>();
			graphMap.put(vertices.get(i), neighbours);

		}

	}

	// Add edge to adjacency list
	public void addEdgesToList() {


		
	}

}
