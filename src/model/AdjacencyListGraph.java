package model;

import java.util.*;

import exceptions.VertexDoesNotExistException;

public class AdjacencyListGraph<T, K extends Comparable> implements IGenericGraph<T, K> {

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
//		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		boolean[] visited = new boolean[vertices.size()];
		LinkedList<T> queue = new LinkedList<T>();

		visited[origin] = true;
		queue.add(vertices.get(origin).getValue());
		while (queue.size() != 0) {

			temp = queue.poll();
			ret.add(temp);

			Iterator<T> it = (Iterator<T>) getVertex(temp).getAdjacencyList().listIterator();
			while (it.hasNext()) {

				System.out.println(1);
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
	public int[] DFS() {

		return null;
	}

	public AdjacencyListGraph<T, K> kruskal() {
		AdjacencyListGraph<T, K> newGraph = new AdjacencyListGraph<>(directedGraph);
		
		return newGraph;
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

	public void updateAdjacencyList(Vertex<T, K> vertex) {

		ArrayList<Edge<T, K>> list = vertex.getAdjacencyList();
		for (int i = 0; i < list.size(); i++) {

			int id = list.get(i).getId();
			list.get(i).getVertexTo().deleteEdge(id);

		}

	}

	public ArrayList<Vertex<T, K>> getVertices() {

		return vertices;

	}

}
