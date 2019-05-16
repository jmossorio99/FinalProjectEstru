package model;

import java.util.ArrayList;

public interface IVertex<T, K> {

	public void addEdge(Edge<T, K> e);
	public T getValue();
	public void setValue(T value);
	public ArrayList<Edge<T, K>> getAdjacencyList();
	

}
