package model;

import java.util.ArrayList;

public interface IGenericGraph<T, K> {

	public void insertVertex(T value);
	public void insertEdge(int from, int to, K data);
	public void deleteVertex(int v);
	public void deleteEdge(int from, int to, int id);
	public ArrayList<T> BFS(int origin);
	public int[] DFS();
	
}
