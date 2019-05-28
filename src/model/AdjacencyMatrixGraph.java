package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import exceptions.VertexDoesNotExistException;

public class AdjacencyMatrixGraph<T> implements IGenericGraph<T> {

	private boolean isDirected;
	private PriorityQueue<Edge<T>>[][] adjacencyMatrix;
	private ArrayList<Vertex<T>> vertexOrder;
	private ArrayList<Edge<T>> edgeOrder;
	private int numberOfEdge;
	private double[][] lowestEdgesMatrix;

	public AdjacencyMatrixGraph(boolean isDirected) {
		this.isDirected = isDirected;
		numberOfEdge = 0;
		vertexOrder = new ArrayList<Vertex<T>>();
		edgeOrder = new ArrayList<Edge<T>>();

	}

	public int getNumberOfEdge() {
		return numberOfEdge;
	}
	
	@Override
	public void insertVertex(T value, double xCoordinate, double yCoordinate) {

		Vertex<T> v = new Vertex<T>(value, xCoordinate, yCoordinate);
		vertexOrder.add(v);

		newVertexToAdyacencyMatrix(vertexOrder.size());

	}

	@Override
	public void insertEdge(int from, int to, double data) throws VertexDoesNotExistException {

		if (isDirected) {

			Edge<T> edge = new Edge<T>(vertexOrder.get(from), vertexOrder.get(to), data, numberOfEdge);
			edgeOrder.add(edge);

			numberOfEdge++;

			PriorityQueue<Edge<T>> temp = adjacencyMatrix[from][to];
			temp.add(edge);
		} else {

			Edge<T> edge = new Edge<T>(vertexOrder.get(from), vertexOrder.get(to), data, numberOfEdge);
			Edge<T> edge2 = new Edge<T>(vertexOrder.get(to), vertexOrder.get(from), data, numberOfEdge);
			edgeOrder.add(edge);
			edgeOrder.add(edge2);
			numberOfEdge++;
			adjacencyMatrix[from][to].add(edge);
			adjacencyMatrix[to][from].add(edge2);

		}

	}

	@Override
	public void deleteVertex(int v) {

		vertexOrder.remove(v);
		deleteVertexFromTheMatrix(v, vertexOrder.size());

	}

	public Edge<T> searchEdgeById(int id) {
		Edge<T> found = null;
		boolean stop = false;

		for (int i = 0; i < edgeOrder.size() && !stop; i++) {

			Edge<T> temp = edgeOrder.get(i);
			if (temp.getId() == id) {
				found = temp;
				stop = true;
			}

		}

		return found;
	}

	@Override
	public void deleteEdge(int from, int to, int id) {

		PriorityQueue<Edge<T>> temp = adjacencyMatrix[from][to];

		if (isDirected) {
			Edge<T> edge = searchEdgeById(id);

			temp.remove(edge);

			edgeOrder.remove(edge);
		} else {
			Edge<T> edge = searchEdgeById(id);
			Edge<T> edge2 = searchEdgeById(id + 1);

			temp.remove(edge);
			temp.remove(edge2);

			edgeOrder.remove(edge);
			edgeOrder.remove(edge2);

		}

	}

	@Override
	public ArrayList BFS(int origin) {

		ArrayList<Vertex<T>> vertices = new ArrayList<Vertex<T>>();

		if (origin < vertexOrder.size()) {

			boolean[] visited = new boolean[vertexOrder.size()];
			Queue<Integer> tail = new LinkedList<Integer>();

			int position = origin;
			tail.add(position);
			visited[position] = true;
			vertices.add(vertexOrder.get(position));

			while (!tail.isEmpty()) {

				int current = tail.poll();

				for (int i = 0; i < vertexOrder.size(); i++) {

					PriorityQueue<Edge<T>> temp = adjacencyMatrix[current][i];

					if (!temp.isEmpty()) {

						if (visited[i] = false) {

							visited[i] = true;
							vertices.add(vertexOrder.get(i));
							tail.add(i);

						}

					}

				}

			}
		}

		return vertices;
	}

	@Override
	public ArrayList<T> DFS() {

		boolean[] visited = new boolean[vertexOrder.size()];
		ArrayList<T> result = new ArrayList<T>();
		DFSRecursive(visited, 0, result);
		return result;

	}

	private void DFSRecursive(boolean[] visited, int n, ArrayList<T> ret) {

		visited[n] = true;
		ret.add(vertexOrder.get(n).getValue());
		for (int i = 0; i < vertexOrder.size(); i++) {

			if (!visited[i] && !adjacencyMatrix[n][i].isEmpty()) {
				DFSRecursive(visited, i, ret);
			}

		}

	}

	public void newVertexToAdyacencyMatrix(int numberOfVertices) {

		PriorityQueue<Edge<T>>[][] newAdyacencyMatrix = new PriorityQueue[numberOfVertices][numberOfVertices];

		if (adjacencyMatrix == null) {

			newAdyacencyMatrix[0][0] = new PriorityQueue<Edge<T>>(1000, new CompareEdgesByData());
			adjacencyMatrix = newAdyacencyMatrix;

		} else {

			for (int i = 0; i < adjacencyMatrix.length; i++) {

				for (int j = 0; j < adjacencyMatrix[0].length; j++) {
					newAdyacencyMatrix[i][j] = adjacencyMatrix[i][j];
				}

			}
			fillNulls(newAdyacencyMatrix);
			adjacencyMatrix = newAdyacencyMatrix;

		}

	}

	private void fillNulls(PriorityQueue<Edge<T>>[][] matrix) {

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == null) {
					matrix[i][j] = new PriorityQueue<Edge<T>>(1000, new CompareEdgesByData());
				}
			}
		}
	}

	public void deleteVertexFromTheMatrix(int position, int newSize) {

		PriorityQueue<Edge<T>>[][] newAdyacencyMatrix = new PriorityQueue[newSize][newSize];

		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix.length; j++) {

				if (!(i == position || j == position)) {

					if (i < position && j < position) {
						newAdyacencyMatrix[i][j] = adjacencyMatrix[i][j];
					} else if (j > position && i < position) {
						newAdyacencyMatrix[i][j - 1] = adjacencyMatrix[i][j];
					} else if (i > position && j < position) {
						newAdyacencyMatrix[i - 1][j] = adjacencyMatrix[i][j];
					} else {
						newAdyacencyMatrix[i - 1][j - 1] = adjacencyMatrix[i][j];
					}

				}

			}
		}

		adjacencyMatrix = newAdyacencyMatrix;

	}

	public void seeMatrix(PriorityQueue<Edge<T>>[][] matrix) {

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {

				if (matrix[i][j] == null) {
					System.out.println("nulo  i=" + i + "  j=" + j);
				} else {
					System.out.println("no nulo  " + "i=" + i + "  j=" + j);
				}

			}
		}

	}

	public ArrayList<Vertex<T>> getVertices() {
		return vertexOrder;
	}

	public PriorityQueue<Edge<T>> getQueue(int row, int column) {

		return adjacencyMatrix[row][column];

	}

	public ArrayList<Edge<T>> getEdges() {

		return edgeOrder;

	}

	@Override
	public AdjacencyListGraph<T> kruskal() throws VertexDoesNotExistException {
		AdjacencyListGraph<T> newGraph = new AdjacencyListGraph<>(isDirected);
		PriorityQueue<Edge<T>> queue = new PriorityQueue<Edge<T>>(numberOfEdge, new CompareEdgesByData<T>());
		ArrayList<LinkedList<Vertex<T>>> DS = new ArrayList<LinkedList<Vertex<T>>>();
		prepareKruskal(queue, DS);
		while (!queue.isEmpty()) {
			Edge<T> current = queue.poll();
			if (!isConected(current.getVertexFrom(), current.getVertexTo(), DS)) {
				Vertex<T> v1 = current.getVertexFrom();
				Vertex<T> v2 = current.getVertexTo();
				Vertex<T> v11 = null;
				Vertex<T> v22 = null;
				if (newGraph.getVertex(v1.getValue()) == null) {
					newGraph.insertVertex(v1.getValue(), v1.getxCoordinate(), v1.getyCoordinate());
					v11 = newGraph.getVertices().get(newGraph.getVertices().size() - 1);
				}else {
					v11 = newGraph.getVertex(v1.getValue());
				}
				if (newGraph.getVertex(v2.getValue()) == null) {
					newGraph.insertVertex(v2.getValue(), v2.getxCoordinate(), v2.getyCoordinate());
					v22 = newGraph.getVertices().get(newGraph.getVertices().size() - 1);
				}else {
					v22 = newGraph.getVertex(v2.getValue());
				}
				newGraph.insertEdge(newGraph.findVertexIndex(v11), newGraph.findVertexIndex(v22), current.getData());
				connect(v1, v2, DS);
			}
		}
		return newGraph;
	}

	private void prepareKruskal(PriorityQueue<Edge<T>> queue, ArrayList<LinkedList<Vertex<T>>> dS) {
		for (int i = 0; i < vertexOrder.size(); i++) {
			LinkedList<Vertex<T>> created = new LinkedList<Vertex<T>>();
			created.add(vertexOrder.get(i));
			dS.add(created);
		}
		for (int i = 0; i < edgeOrder.size(); i++) {
			for (int j = 0; j < edgeOrder.size(); j++) {
				if (isDirected) {
					if (!queue.contains(edgeOrder.get(j))) {
						queue.add(edgeOrder.get(j));
					}
				} else {
					boolean contained = false;
					Iterator<Edge<T>> it = queue.iterator();
					while (it.hasNext()) {
						Edge<T> comparison = it.next();
						if (comparison.getId() == edgeOrder.get(j).getId()) {
							contained = true;
						}
					}
					if (!contained) {
						queue.add(edgeOrder.get(j));
					}
				}
			}
		}
	}

	private boolean isConected(Vertex<T> v1, Vertex<T> v2, ArrayList<LinkedList<Vertex<T>>> DS) {
		boolean conected = false;
		for (int i = 0; i < DS.size(); i++) {
			LinkedList<Vertex<T>> actual = (LinkedList<Vertex<T>>) DS.get(i);
			if (actual.contains(v1) && actual.contains(v2)) {
				conected = true;
			}
		}
		return conected;
	}

	private void connect(Vertex<T> v1, Vertex<T> v2, ArrayList<LinkedList<Vertex<T>>> DS) {
		LinkedList<Vertex<T>> toConnect1 = null;
		LinkedList<Vertex<T>> toConnect2 = null;
		for (int i = 0; i < DS.size(); i++) {
			LinkedList<Vertex<T>> current = (LinkedList<Vertex<T>>) DS.get(i);
			if (current.contains(v1)) {
				toConnect1 = current;
			} else if (current.contains(v2)) {
				toConnect2 = current;
			}
		}
		toConnect1.addAll(toConnect2);
		DS.remove(toConnect2);
	}
	
	@Override
	public double[][] floydWarshal() {
		getLowestEdgeMatrix();

		for (int p = 0; p < lowestEdgesMatrix.length; p++) {
			for (int i = 0; i < lowestEdgesMatrix.length; i++) {
				for (int j = 0; j < lowestEdgesMatrix.length; j++) {

					double sum = lowestEdgesMatrix[i][p] + lowestEdgesMatrix[p][j];

					if (!(sum < 0)) {

						if (sum < lowestEdgesMatrix[i][j]) {
							lowestEdgesMatrix[i][j] = sum;
						}

					}

				}
			}
		}

		return lowestEdgesMatrix;
	}

	private void getLowestEdgeMatrix() {

		lowestEdgesMatrix = new double[vertexOrder.size()][vertexOrder.size()];

		for (int i = 0; i < vertexOrder.size(); i++) {
			for (int j = 0; j < vertexOrder.size(); j++) {

				Edge<T> temp = adjacencyMatrix[i][j].peek();

				if (temp != null) {
					lowestEdgesMatrix[i][j] = temp.getData();
				} else {
					lowestEdgesMatrix[i][j] = Double.MAX_VALUE;
				}
				
				if(i==j) {
					lowestEdgesMatrix[i][j]=0.0;
				}
			}
		}

	}

	@Override
	public int[] prim() {

		getLowestEdgeMatrix();
		int[] prev = new int[vertexOrder.size()];
		double[] keys = new double[vertexOrder.size()];
		Boolean[] setInTree = new Boolean[vertexOrder.size()];
		for (int i = 0; i < setInTree.length; i++) {
			keys[i] = Double.MAX_VALUE;
			setInTree[i] = false;
		}
		prev[0] = -1;
		keys[0] = 0;

		for (int i = 0; i < setInTree.length - 1; i++) {

			int u = findMinKey(keys, setInTree);
			if (u != -1) {
				
				setInTree[u] = true;

				for (int v = 0; v < setInTree.length; v++) {

					if (lowestEdgesMatrix[u][v] != 0 && !setInTree[v] && lowestEdgesMatrix[u][v] < keys[v]) {

						keys[v] = lowestEdgesMatrix[u][v];
						prev[v] = u;

					}

				}
			}

		}

		return prev;
	}

	private int findMinKey(double[] keys, Boolean[] setInTree) {

		double min = Double.MAX_VALUE;
		int minIndex = -1;
		for (int i = 0; i < keys.length; i++) {
			if (setInTree[i] == false && keys[i] < min) {

				min = keys[i];
				minIndex = i;

			}
		}
		return minIndex;

	}

	@Override
	public double[][] Dijkstra(T city) {

		boolean[] visited = new boolean[vertexOrder.size()];
		double[][] matrix = new double[2][vertexOrder.size()];
		int position = searchPositionByCity(city);
		PriorityQueue<Vertex<T>> pq = new PriorityQueue<Vertex<T>>(10000,new CompareVertexByDistance());
		pq.add(vertexOrder.get(position));

		for (int i = 0; i < vertexOrder.size(); i++) {
			matrix[0][i] = -1;

			if (i == position) {
				matrix[1][i] = 0;
			} else {
				matrix[1][i] = Double.MAX_VALUE;
			}
		}
      
		
		while (!pq.isEmpty()) {

			Vertex<T> current = pq.poll();
			int pos = searchPositionByVertex(current);

			
			if(visited[pos]==false) {
				
				visited[pos]=true;
				
				for(int i=0;i<matrix.length;i++) {
										
					
					if(!adjacencyMatrix[pos][i].isEmpty()) {
					
						Edge<T> adjacency = adjacencyMatrix[pos][i].peek();
						
						double weight = adjacency.getData()+matrix[1][pos];
						
						if(weight<0) {
							weight=Double.MAX_VALUE;
						}
						
						if(matrix[1][i]>weight) {
							matrix[1][i]=weight;
							matrix[0][i]=pos;
							
							Vertex<T> vertex = vertexOrder.get(i);
							vertex.setDist(weight);
							pq.offer(vertex);
							
						} 
						
						
					}
				}
				
			}
		}
		
		System.out.println("devuelvo");

		return matrix;
	}

	private int searchPositionByVertex(Vertex<T> vertex) {
		return vertexOrder.indexOf(vertex);
	}

	public int searchPositionByCity(T city) {
		int position = -1;
		boolean stop = false;

		for (int i = 0; i < vertexOrder.size() && !stop; i++) {
			Vertex<T> temp = vertexOrder.get(i);
			if (city.equals(temp.getValue())) {
				position = i;
				stop = true;
			}
		}

		return position;
	}
	
}
