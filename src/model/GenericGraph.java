package model;

import java.util.*;

public class GenericGraph<T, K> implements IGenericGraph<T, K> {

	private ArrayList<Vertex<T, K>> vertices;
	private int numOfEdges = 0;
	private boolean directedGraph = false;

	public GenericGraph(boolean isDirected) {
		directedGraph = isDirected;
	}

	@Override
	public void insertVertex(T value) {

		Vertex<T, K> v = new Vertex<T, K>(value);
		vertices.add(v);

	}

	@Override
	public void insertEdge(int from, int to, K data) {

		Vertex<T, K> v = vertices.get(from);
		Vertex<T, K> v2 = vertices.get(to);
		if (directedGraph) {
			v.addEdge(new Edge<T, K>(v, v2, data, numOfEdges));
			numOfEdges++;
		} else {
			v.addEdge(new Edge<T, K>(v, v2, data, numOfEdges));
			v2.addEdge(new Edge<T, K>(v2, v, data, numOfEdges));
			numOfEdges++;
		}

	}

	@Override
	public void deleteVertex(int v) {
		vertices.remove(v);
	}

	@Override
	public void deleteEdge(int from, int to, int id) {

		Vertex<T, K> v = vertices.get(from);
		if (v.isAdjacent(vertices.get(to), id)) {
			if (directedGraph) {
				v.deleteEdge(id);
			} else {
				v.deleteEdge(id);
				Vertex<T, K> v2 = vertices.get(to);
				v2.deleteEdge(id);
			}
		}

	}

	@Override
	public int[] BFS(int origin) {

		return null;
	}

	@Override
	public int[] DFS() {

		return null;
	}

}
