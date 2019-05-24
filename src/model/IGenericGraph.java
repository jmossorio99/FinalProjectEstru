package model;

import java.util.ArrayList;

import exceptions.VertexDoesNotExistException;

public interface IGenericGraph<T, K> {

	public void insertVertex(T value);
	public void insertEdge(int from, int to, K data) throws VertexDoesNotExistException;
	public void deleteVertex(int v) throws VertexDoesNotExistException;
	public void deleteEdge(int from, int to, int id) throws VertexDoesNotExistException;
	public ArrayList<T> BFS(int origin);
	public ArrayList<T> DFS();
	
}
