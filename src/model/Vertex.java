package model;

import java.util.ArrayList;

public class Vertex<T> {

	private T value;
	private ArrayList<Edge<T>> adjacency;

	public Vertex(T value) {

		this.setValue(value);
		adjacency = new ArrayList<Edge<T>>();

	}

	public void addEdge(Edge<T> e) {

		adjacency.add(e);

	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
