package model;

public interface IGenericGraph<T, K> {

	public void insertVertex(T value);
	public void insertEdge(int from, int to, K data);
	public void deleteVertex(int v);
	public void deleteEdge(int from, int to, int id);
	public int[] BFS(int origin);
	public int[] DFS();
	
}
