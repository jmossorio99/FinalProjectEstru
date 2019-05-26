package model;

import java.util.ArrayList;

import exceptions.VertexDoesNotExistException;

public interface IGenericGraph<T> {

	public void insertVertex(T value);
	public void insertEdge(int from, int to, double data) throws VertexDoesNotExistException;
	public void deleteVertex(int v) throws VertexDoesNotExistException;
	public void deleteEdge(int from, int to, int id) throws VertexDoesNotExistException;
	public ArrayList<T> BFS(int origin);
	public ArrayList<T> DFS();
	public AdjacencyListGraph<T> kruskal() throws VertexDoesNotExistException;
	public double[][] floydWarshal();
	public int[] prim();
	public double[][] Dijkstra(T city);
	
}
