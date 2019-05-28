package model;

import java.util.*;

import javax.xml.stream.events.StartDocument;

import exceptions.VertexDoesNotExistException;

public class AdjacencyListGraph<T> implements IGenericGraph<T> {

	private ArrayList<Vertex<T>> vertices;
	private int numOfEdges = 0;
	private boolean directedGraph = false;

	public AdjacencyListGraph(boolean isDirected) {
		directedGraph = isDirected;
		vertices = new ArrayList<Vertex<T>>();
	}

	public int getNumberOfEdges() {
		return numOfEdges;
	}
	
	@Override
	public void insertVertex(T value, double xCoordinate, double yCoordinate) {

		Vertex<T> v = new Vertex<T>(value, xCoordinate, yCoordinate);
		vertices.add(v);

	}

	@Override
	public void insertEdge(int from, int to, double data) throws VertexDoesNotExistException {

		if (from < vertices.size() && to < vertices.size()) {

			Vertex<T> v = vertices.get(from);
			Vertex<T> v2 = vertices.get(to);
			if (directedGraph) {
				v.addEdge(new Edge<T>(v, v2, data, numOfEdges));
				numOfEdges++;
			} else {
				v.addEdge(new Edge<T>(v, v2, data, numOfEdges));
				v2.addEdge(new Edge<T>(v2, v, data, numOfEdges));
				numOfEdges++;
			}

		} else {
			throw new VertexDoesNotExistException();
		}

	}

	@Override
	public void deleteVertex(int v) throws VertexDoesNotExistException {

		if (v < vertices.size()) {

			Vertex<T> toDelete = vertices.get(v);
			vertices.remove(v);
			if (!directedGraph) {
				updateAdjacencyList(toDelete);
			} else {
				for (Vertex<T> vertex : vertices) {
					vertex.updateAdjacencyList(toDelete);
				}
			}

		} else {
			throw new VertexDoesNotExistException();
		}

	}

	@Override
	public void deleteEdge(int from, int to, int id) throws VertexDoesNotExistException {

		if (from < vertices.size() && to < vertices.size()) {
			Vertex<T> v = vertices.get(from);
			Vertex<T> v2 = vertices.get(to);
			if (v.isAdjacent(v2, id)) {
				if (directedGraph) {
					v.deleteEdge(id);
				} else {
					v.deleteEdge(id);
					v2.deleteEdge(id);
				}
			}
		} else {
			throw new VertexDoesNotExistException();
		}

	}

	@Override
	public ArrayList<T> BFS(int origin) {

		T temp = null;
		ArrayList<T> ret = new ArrayList<T>();
		boolean[] visited = new boolean[vertices.size()];
		LinkedList<T> queue = new LinkedList<T>();

		visited[origin] = true;
		queue.add(vertices.get(origin).getValue());
		while (queue.size() != 0) {

			temp = queue.poll();
			ret.add(temp);

			Iterator<Edge<T>> it = (Iterator<Edge<T>>) getVertex(temp).getAdjacencyList().listIterator();
			while (it.hasNext()) {

				Vertex<T> v = ((Edge<T>) it.next()).getVertexTo();
				int verIndex = findVertexIndex(v);
				T n = v.getValue();
				if (!visited[verIndex]) {
					visited[verIndex] = true;
					queue.add(n);
				}

			}

		}
		return ret;
	}

	@Override
	public ArrayList<T> DFS() {

		boolean[] visited = new boolean[vertices.size()];
		ArrayList<T> ret = new ArrayList<T>();
		DFSRecursive(visited, 0, ret);
		return ret;

	}

	private void DFSRecursive(boolean[] visited, int n, ArrayList<T> ret) {

		visited[n] = true;
		ret.add(vertices.get(n).getValue());
		Iterator<Edge<T>> it = (Iterator<Edge<T>>) vertices.get(n).getAdjacencyList().iterator();
		while (it.hasNext()) {

			int v = findVertexIndex(it.next().getVertexTo());
			if (!visited[v]) {
				DFSRecursive(visited, v, ret);
			}

		}

	}

	private void prepareKruskal(PriorityQueue<Edge<T>> queue, ArrayList<LinkedList<Vertex<T>>> DS) {
		for (int i = 0; i < vertices.size(); i++) {
			LinkedList<Vertex<T>> created = new LinkedList<Vertex<T>>();
			created.add(vertices.get(i));
			DS.add(created);
		}
		for (int i = 0; i < vertices.size(); i++) {
			ArrayList<Edge<T>> partial = vertices.get(i).getAdjacencyList();
			for (int j = 0; j < partial.size(); j++) {
				if (directedGraph) {
					if (!queue.contains(partial.get(j))) {
						queue.add(partial.get(j));
					}
				} else {
					boolean contained = false;
					Iterator<Edge<T>> it = queue.iterator();
					while (it.hasNext()) {
						Edge<T> comparison = it.next();
						if (comparison.getId() == partial.get(j).getId()) {
							contained = true;
						}
					}
					if (!contained) {
						queue.add(partial.get(j));
					}
				}
			}
		}
	}

	public AdjacencyListGraph<T> kruskal() throws VertexDoesNotExistException {
		AdjacencyListGraph<T> newGraph = new AdjacencyListGraph<>(directedGraph);
		PriorityQueue<Edge<T>> queue = new PriorityQueue<Edge<T>>(numOfEdges, new CompareEdgesByData<T>());
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

	public Vertex<T> getVertex(T value) {

		for (int j = 0; j < vertices.size(); j++) {
			if (vertices.get(j).getValue().equals(value)) {
				return vertices.get(j);
			}
		}
		return null;

	}

	public int findVertexIndex(Vertex<T> v) {

		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).equals(v)) {
				return i;
			}
		}
		return -1;

	}

	private void updateAdjacencyList(Vertex<T> vertex) {

		ArrayList<Edge<T>> list = vertex.getAdjacencyList();
		for (int i = 0; i < list.size(); i++) {

			int id = list.get(i).getId();
			list.get(i).getVertexTo().deleteEdge(id);

		}

	}

	/*
	 * This method transforms the adjacency list structure implemented in this graph
	 * into an adjacency matrix of K
	 */
	@SuppressWarnings({ "unchecked" })
	private PriorityQueue<Edge<T>>[][] transformIntoMatrix() {

		PriorityQueue<Edge<T>>[][] m = new PriorityQueue[vertices.size()][vertices.size()];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				m[i][j] = new PriorityQueue<Edge<T>>(vertices.size(), new CompareEdgesByData<T>());
			}
		}
		for (int i = 0; i < vertices.size(); i++) {

			Iterator<Edge<T>> it = (Iterator<Edge<T>>) vertices.get(i).getAdjacencyList().iterator();
			while (it.hasNext()) {

				Edge<T> e = it.next();
				int row = findVertexIndex(e.getVertexFrom());
				int column = findVertexIndex(e.getVertexTo());
				m[row][column].offer(e);

			}

		}
		return m;

	}

	public double[][] floydWarshal() {

		PriorityQueue<Edge<T>>[][] m = transformIntoMatrix();
		double[][] dist = new double[m.length][m[0].length];
		for (int i = 0; i < dist.length; i++) {
			for (int j = 0; j < dist.length; j++) {
				if (m[i][j].isEmpty()) {
					if (i == j) {
						dist[i][j] = 0;
					} else {
						dist[i][j] = Double.MAX_VALUE;
					}
				} else {
					dist[i][j] = m[i][j].peek().getData();
				}
			}
		}
		for (int k = 0; k < dist.length; k++) {
			for (int i = 0; i < dist.length; i++) {
				for (int j = 0; j < dist.length; j++) {

					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}

				}
			}
		}

		return dist;

	}

	public int[] prim() {

		PriorityQueue<Edge<T>>[][] m = transformIntoMatrix();
		double[][] graph = new double[m.length][m[0].length];
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				if (m[i][j].isEmpty()) {
					if (i == j) {
						graph[i][j] = 0;
					} else {
						graph[i][j] = Double.MAX_VALUE;
					}
				} else {
					graph[i][j] = m[i][j].peek().getData();
				}
			}
		}
		int[] prev = new int[graph.length];
		double[] keys = new double[graph.length];
		Boolean[] setInTree = new Boolean[graph.length];
		for (int i = 0; i < keys.length; i++) {
			keys[i] = Double.MAX_VALUE;
			setInTree[i] = false;
		}
		prev[0] = -1;
		keys[0] = 0;

		for (int i = 0; i < graph.length - 1; i++) {

			int u = findMinKey(keys, setInTree);
			if (u != -1) {

				setInTree[u] = true;
				for (int v = 0; v < graph.length; v++) {

					if (graph[u][v] != 0 && setInTree[v] == false && graph[u][v] < keys[v]) {

						prev[v] = u;
						keys[v] = graph[u][v];
						
					}

				}

			}

		}
		return prev;

	}

	public double[][] Dijkstra(T city) {
		double[][] result = new double[2][vertices.size()];
		Vertex<T> sartPoint = getVertex(city);
		int originPos = findVertexIndex(sartPoint);
		result[0][originPos] = 0;
		result[1][originPos] = -1;
		for (int i = 0; i < result[0].length; i++) {
			if (result[0][i] == 0 && i != originPos) {
				result[0][i] = Double.MAX_VALUE;
				result[1][i] = -1;
			}
			vertices.get(i).setDist(result[0][i]);
		}
		PriorityQueue<Vertex<T>> queue = new PriorityQueue<Vertex<T>>(vertices.size(), new CompareVertexByDistance());
		for (int i = 0; i < vertices.size(); i++) {
			queue.add(vertices.get(i));
		}
		while (!queue.isEmpty()) {
			Vertex<T> actual = queue.poll();
			int currentIt = findVertexIndex(actual);
			ArrayList<Edge<T>> connected = vertices.get(currentIt).getAdjacencyList();
			for (int i = 0; i < connected.size(); i++) {
				Edge<T> connection = connected.get(i);
				int comparison = findVertexIndex(connection.getVertexTo());
				if (result[0][currentIt] + connection.getData() < result[0][comparison]) {
					result[0][comparison] = result[0][currentIt] + connection.getData();
					connection.getVertexTo().setDist(result[0][comparison]);
					result[1][comparison] = currentIt;
				}
			}
		}
		return result;
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

	public ArrayList<Vertex<T>> getVertices() {

		return vertices;

	}

	public int getNumOfEdges() {
		return numOfEdges;
	}

}
