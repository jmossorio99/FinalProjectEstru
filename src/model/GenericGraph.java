package model;

import java.util.*;

public class GenericGraph<T, K> implements IGenericGraph<T, K> {

	private ArrayList<Vertex<T, K>> vertices;
	private int numOfEdges = 0;

	@Override
	public void insertVertex(T value) {

	}

	@Override
	public void insertEdge(int from, int to, K data) {

	}

	@Override
	public void deleteVertex(int v) {

	}

	@Override
	public void deleteEdge(int from, int to, int id) {

	}

	@Override
	public int[] BFS(int origin) {

		return null;
	}

	@Override
	public int[] DFS() {

		return null;
	}

}
