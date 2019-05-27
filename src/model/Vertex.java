package model;

import java.util.ArrayList;

public class Vertex<T> implements IVertex<T> {

	private T value;
	private ArrayList<Edge<T>> adjacency;
	private Double dist;
	private double xCoordinate;
	private double yCoordinate;

	public Vertex(T value, double xCoordinate, double yCoordinate) {

		this.setValue(value);
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
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

	public double getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(double xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public double getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(double yCoordinate) {
		this.yCoordinate = yCoordinate;
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
