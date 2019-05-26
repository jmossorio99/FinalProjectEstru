package model;

import java.util.ArrayList;

public class Vertex<T> implements IVertex<T> {

	private T value;
	private ArrayList<Edge<T>> adjacency;
	private Double dist;

	public Vertex(T value) {

		this.setValue(value);
		adjacency = new ArrayList<Edge<T>>();

	}

	@Override
	public void addEdge(Edge<T> e) {

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
	public ArrayList<Edge<T>> getAdjacencyList() {
		return adjacency;
	}

	public double getDist() {
		return dist;
	}

	public void setDist(double dist) {
		this.dist = dist;
	}

	@Override
	public boolean isAdjacent(Vertex<T> vertex, int id) {

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

	public void updateAdjacencyList(Vertex<T> vertex) {

		for (int i = 0; i < adjacency.size(); i++) {

			if (adjacency.get(i).getVertexTo().equals(vertex)) {
				adjacency.remove(i);
			}

		}

	}

	public int compareTo(Vertex v2) {
		return this.dist.compareTo(v2.getDist());
	}

}
