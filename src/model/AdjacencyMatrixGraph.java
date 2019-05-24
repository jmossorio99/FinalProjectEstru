package model;

import java.util.ArrayList;
import java.util.PriorityQueue;

import exceptions.VertexDoesNotExistException;

public class AdjacencyMatrixGraph<T,K extends Comparable<K>> implements IGenericGraph<T,K> {

	private boolean isDirected;
	private PriorityQueue<Edge<T,K>>[][] adyacencyMatrix;
	private ArrayList<Vertex<T,K>> vertexOrder;
	public ArrayList<Edge<T,K>> edgeOrder;
	
	public AdjacencyMatrixGraph(boolean isDirected) {
		this.isDirected=isDirected;
	}
	
	@Override
	public void insertVertex(T value) {
	
		Vertex<T, K> v = new Vertex<T, K>(value);
		vertexOrder.add(v);
		
		newVertexToAdyacencyMatrix(vertexOrder.size());
		
	}

	@Override
	public void insertEdge(int from, int to, K data) throws VertexDoesNotExistException {
		
		
		
	}

	@Override
	public void deleteVertex(int v) {
		
		vertexOrder.remove(v);
		deleteVertexFromTheMatrix(v);

	}

	@Override
	public void deleteEdge(int from, int to, int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList BFS(int origin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] DFS() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void newVertexToAdyacencyMatrix(int numberOfVertices) {
		
		PriorityQueue<Edge<T, K>>[][] newAdyacencyMatrix = new PriorityQueue[numberOfVertices][numberOfVertices];

		for (int i = 0; i < adyacencyMatrix.length; i++) {
			for (int j = 0; j < adyacencyMatrix.length; j++) {

				newAdyacencyMatrix[i][j] = adyacencyMatrix[i][j];

				if (i == adyacencyMatrix.length - 1 || j == adyacencyMatrix.length - 1) {
					PriorityQueue<Edge<T, K>> temp = new PriorityQueue<Edge<T, K>>(Integer.MAX_VALUE, new CompareEdgesByData());
					newAdyacencyMatrix[i][j] = temp;
					// no one care x2
				}
			}
		}

	}

	public void deleteVertexFromTheMatrix(int position) {
		
		
		
	}
	



	


}
