package model;

import java.util.ArrayList;

public interface IVertex<T, K extends Comparable<K>> {

	public void addEdge(Edge<T, K> e);
	public void deleteEdge(int id);
	public T getValue();
	public void setValue(T value);
	public ArrayList<Edge<T, K>> getAdjacencyList();
	public boolean isAdjacent(Vertex<T, K> vertex, int id);

}
