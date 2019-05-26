package model;

import java.util.ArrayList;

public interface IVertex<T> {

	public void addEdge(Edge<T> e);
	public void deleteEdge(int id);
	public T getValue();
	public void setValue(T value);
	public ArrayList<Edge<T>> getAdjacencyList();
	public boolean isAdjacent(Vertex<T> vertex, int id);

}
