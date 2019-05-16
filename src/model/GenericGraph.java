package model;

import java.util.*;

public class GenericGraph<T, K> implements IGenericGraph<T, K> {

	private ArrayList<Vertex<T, K>> vertices;
	private int numOfEdges = 0;
	private boolean directedGraph = false;

	public GenericGraph(boolean isDirected) {
		directedGraph = isDirected;
	}

	@Override
	public void insertVertex(T value) {

		Vertex<T, K> v = new Vertex<T, K>(value);
		vertices.add(v);

	}

	@Override
	public void insertEdge(int from, int to, K data) {

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

	}

	@Override
	public void deleteVertex(int v) {
		vertices.remove(v);
	}

	@Override
	public void deleteEdge(int from, int to, int id) {

		Vertex<T, K> v = vertices.get(from);
		if (v.isAdjacent(vertices.get(to), id)) {
			if (directedGraph) {
				v.deleteEdge(id);
			} else {
				v.deleteEdge(id);
				Vertex<T, K> v2 = vertices.get(to);
				v2.deleteEdge(id);
			}
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

			Iterator<T> it = (Iterator<T>) getVertex(temp).getAdjacencyList().listIterator();
			while (it.hasNext()) {

				Vertex<T, K> v = ((Edge<T, K>) it.next()).getVertexFrom();
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

}
