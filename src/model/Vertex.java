package model;

import java.util.ArrayList;

public class Vertex<T, K> implements IVertex<T, K> {

	private T value;
	private ArrayList<Edge<T, K>> adjacency;

	public Vertex(T value) {

		this.setValue(value);
		adjacency = new ArrayList<Edge<T, K>>();

	}

	@Override
	public void addEdge(Edge<T, K> e) {

		adjacency.add(e);

	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public void setValue(T value) {

		this.value = value;

	}

	@Override
	public ArrayList<Edge<T, K>> getAdjacencyList() {
		return adjacency;
	}

	@Override
	public boolean isAdjacent(Vertex<T, K> vertex, int id) {

		for (int i = 0; i < adjacency.size(); i++) {
			if (adjacency.get(i).getVertexTo().equals(vertex) && adjacency.get(i).getId() == id) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void deleteEdge(int id) {

		for (int i = 0; i < adjacency.size(); i++) {
			if (adjacency.get(i).getId() == id) {
				adjacency.remove(i);
			}
		}

	}

}
