package model;

import java.util.*;

import exceptions.VertexDoesNotExistException;

public class AdjacencyListGraph<T, K extends Comparable<K>> implements IGenericGraph<T, K> {

	private ArrayList<Vertex<T, K>> vertices;
	private int numOfEdges = 0;
	private boolean directedGraph = false;

	public AdjacencyListGraph(boolean isDirected) {
		directedGraph = isDirected;
		vertices = new ArrayList<Vertex<T, K>>();
	}

	@Override
	public void insertVertex(T value) {

		Vertex<T, K> v = new Vertex<T, K>(value);
		vertices.add(v);

	}

	@Override
	public void insertEdge(int from, int to, K data) throws VertexDoesNotExistException {

		if (from < vertices.size() && to < vertices.size()) {

			Vertex<T, K> v = vertices.get(from);
			Vertex<T, K> v2 = vertices.get(to);
			if (directedGraph) {
				v.addEdge(new Edge<T, K>(v, v2, data, numOfEdges));
				numOfEdges++;
			} else {
				v.addEdge(new Edge<T, K>(v, v2, data, numOfEdges));
				v2.addEdge(new Edge<T, K>(v2, v, data, numOfEdges));
				numOfEdges++;
			}

		} else {
			throw new VertexDoesNotExistException();
		}

	}

	@Override
	public void deleteVertex(int v) throws VertexDoesNotExistException {

		if (v < vertices.size()) {

			Vertex<T, K> toDelete = vertices.get(v);
			vertices.remove(v);
			if (!directedGraph) {
				updateAdjacencyList(toDelete);
			} else {
				for (Vertex<T, K> vertex : vertices) {
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
			Vertex<T, K> v = vertices.get(from);
			Vertex<T, K> v2 = vertices.get(to);
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

	@SuppressWarnings("unchecked")
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

			Iterator<Edge<T, K>> it = (Iterator<Edge<T, K>>) getVertex(temp).getAdjacencyList().listIterator();
			while (it.hasNext()) {

				Vertex<T, K> v = ((Edge<T, K>) it.next()).getVertexTo();
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
		Iterator<Edge<T, K>> it = (Iterator<Edge<T, K>>) vertices.get(n).getAdjacencyList().iterator();
		while (it.hasNext()) {

			int v = findVertexIndex(it.next().getVertexTo());
			if (!visited[v]) {
				DFSRecursive(visited, v, ret);
			}

		}

	}

	private void prepareKruskal(PriorityQueue<Edge<T, K>> queue, ArrayList<LinkedList<Vertex<T, K>>> DS) {
		for (int i = 0; i < vertices.size(); i++) {
			LinkedList<Vertex<T, K>> created = new LinkedList<Vertex<T, K>>();
			created.add(vertices.get(i));
			DS.add(created);
		}
		for (int i = 0; i < vertices.size(); i++) {
			ArrayList<Edge<T, K>> partial = vertices.get(i).getAdjacencyList();
			for (int j = 0; j < partial.size(); j++) {
				if (directedGraph) {
					if (!queue.contains(partial.get(j))) {
						queue.add(partial.get(j));
					}
				} else {
					boolean contained = false;
					Iterator<Edge<T, K>> it = queue.iterator();
					while (it.hasNext()) {
						Edge<T, K> comparison = it.next();
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

	public AdjacencyListGraph<T, K> kruskal() throws VertexDoesNotExistException {
		AdjacencyListGraph<T, K> newGraph = new AdjacencyListGraph<>(directedGraph);
		PriorityQueue<Edge<T, K>> queue = new PriorityQueue<Edge<T, K>>(numOfEdges, new CompareEdgesByData());
		ArrayList<LinkedList<Vertex<T, K>>> DS = new ArrayList<LinkedList<Vertex<T, K>>>();
		prepareKruskal(queue, DS);
		while (!queue.isEmpty()) {
			Edge<T, K> current = queue.poll();
			if (!isConected(current.getVertexFrom(), current.getVertexTo(), DS)) {
				Vertex<T, K> v1 = current.getVertexFrom();
				Vertex<T, K> v2 = current.getVertexTo();
				if (newGraph.getVertex(v1.getValue()) == null) {
					newGraph.insertVertex(v1.getValue());
				}
				if (newGraph.getVertex(v2.getValue()) == null) {
					newGraph.insertVertex(v2.getValue());
				}
				newGraph.insertEdge(findVertexIndex(v1), findVertexIndex(v2), current.getData());
				connect(v1, v2, DS);
			}
		}
		return newGraph;
	}

	private boolean isConected(Vertex<T, K> v1, Vertex<T, K> v2, ArrayList<LinkedList<Vertex<T, K>>> DS) {
		boolean conected = false;
		for (int i = 0; i < DS.size(); i++) {
			LinkedList<Vertex<T, K>> actual = (LinkedList<Vertex<T, K>>) DS.get(i);
			if (actual.contains(v1) && actual.contains(v2)) {
				conected = true;
			}
		}
		return conected;
	}

	private void connect(Vertex<T, K> v1, Vertex<T, K> v2, ArrayList<LinkedList<Vertex<T, K>>> DS) {
		LinkedList<Vertex<T, K>> toConnect1 = null;
		LinkedList<Vertex<T, K>> toConnect2 = null;
		for (int i = 0; i < DS.size(); i++) {
			LinkedList<Vertex<T, K>> current = (LinkedList<Vertex<T, K>>) DS.get(i);
			if (current.contains(v1)) {
				toConnect1 = current;
			} else if (current.contains(v2)) {
				toConnect2 = current;
			}
		}
		toConnect1.addAll(toConnect2);
		DS.remove(toConnect2);
	}

	public Vertex<T, K> getVertex(T value) {

		for (int j = 0; j < vertices.size(); j++) {
			if (vertices.get(j).getValue().equals(value)) {
				return vertices.get(j);
			}
		}
		return null;

	}

	public int findVertexIndex(Vertex<T, K> v) {

		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).equals(v)) {
				return i;
			}
		}
		return -1;

	}

	private void updateAdjacencyList(Vertex<T, K> vertex) {

		ArrayList<Edge<T, K>> list = vertex.getAdjacencyList();
		for (int i = 0; i < list.size(); i++) {

			int id = list.get(i).getId();
			list.get(i).getVertexTo().deleteEdge(id);

		}

	}

	/*
	 * This method transforms the adjacency list structure implemented in this graph
	 * into an adjacency matrix of K
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private PriorityQueue<Edge<T, K>>[][] preFloyd() {

		ArrayList<Integer> usedIds = new ArrayList<Integer>();
		PriorityQueue<Edge<T, K>>[][] m = new PriorityQueue[vertices.size()][vertices.size()];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				m[i][j] = new PriorityQueue<Edge<T, K>>(Integer.MAX_VALUE, new CompareEdgesByData());
			}
		}
		for (int i = 0; i < vertices.size(); i++) {

			Iterator<Edge<T, K>> it = (Iterator<Edge<T, K>>) vertices.get(i).getAdjacencyList().iterator();
			while (it.hasNext()) {

				Edge<T, K> e = it.next();
				int id = e.getId();
				if (usedIds.isEmpty() || !usedIds.contains(id)) {

					usedIds.add(id);
					int row = findVertexIndex(e.getVertexFrom());
					int column = findVertexIndex(e.getVertexTo());
					m[row][column].offer(e);

				}

			}

		}
		return m;

	}

	@SuppressWarnings("unchecked")
	public K[][] floydWarshal() {

		PriorityQueue<Edge<T, K>>[][] m = preFloyd();
		K[][] dist = (K[][]) new Object[m.length][m[0].length];
		for (int i = 0; i < dist.length; i++) {
			for (int j = 0; j < dist.length; j++) {
				if (m[i][j].isEmpty()) {
					dist[i][j] = null;
				} else {
					dist[i][j] = m[i][j].peek().getData();
				}
			}
		}
		for (int k = 0; k < dist.length; k++) {
			for (int i = 0; i < dist.length; i++) {
				for (int j = 0; j < dist.length; j++) {
					
					if (!(dist[i][k] == null) && !(dist[k][j] == null)) {
						
//						if (dist[i][j] == null) {
//							dist[i][j] = dist[i][k] + dist[k][j];
//						} else if (dist[i][k]. + dist[k][j] < dist[i][j]){
//							dist[i][j] = dist[i][k] + dist[k][j];
//						}
						
					}
					 
					
				}
			}
		}
		
		return null;

	}

	public ArrayList<Vertex<T, K>> getVertices() {

		return vertices;

	}

	public int getNumOfEdges() {
		return numOfEdges;
	}

}
