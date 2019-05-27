package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exceptions.VertexDoesNotExistException;
import model.*;

class AdjacencyListGraphTest {

	@SuppressWarnings("rawtypes")
	private AdjacencyListGraph graph;

	// empty directed graph
	private void setUp1() {

		graph = new AdjacencyListGraph<String>(true);

	}

	// directed graph with vertices
	@SuppressWarnings("unchecked")
	private void setUp2() {

		graph = new AdjacencyListGraph<String>(true);
		graph.insertVertex("Cali");
		graph.insertVertex("Bogotá");
		graph.insertVertex("Medellín");
		graph.insertVertex("Pasto");
		graph.insertVertex("Barranquilla");

	}

	// nonDirected graph with vertices and edges
	@SuppressWarnings("unchecked")
	private void setUp3() {

		graph = new AdjacencyListGraph<String>(false);
		graph.insertVertex("Cali");
		graph.insertVertex("Bogotá");
		graph.insertVertex("Medellín");
		graph.insertVertex("Pasto");
		graph.insertVertex("Barranquilla");
		try {
			graph.insertEdge(0, 1, 250000);
			graph.insertEdge(1, 2, 500000);
			graph.insertEdge(2, 3, 150000);
			graph.insertEdge(1, 4, 800000);
			graph.insertEdge(0, 4, 650000);
		} catch (VertexDoesNotExistException e) {
			e.printStackTrace();
		}

	}

	// nonDirected Graph with two vertices and an edge
	@SuppressWarnings("unchecked")
	private void setUp4() {

		graph = new AdjacencyListGraph<String>(false);
		graph.insertVertex("Cali");
		graph.insertVertex("Barranquilla");
		try {
			graph.insertEdge(0, 1, 500000);
		} catch (VertexDoesNotExistException e) {
			e.printStackTrace();
		}

	}

	// directed graph with three vertices and edges
	@SuppressWarnings("unchecked")
	private void setUp5() {

		graph = new AdjacencyListGraph<String>(true);
		graph.insertVertex("Cali");
		graph.insertVertex("Barranquilla");
		graph.insertVertex("Bogota");
		try {
			graph.insertEdge(0, 1, 500000);
			graph.insertEdge(1, 0, 500000);
			graph.insertEdge(2, 1, 250000);
		} catch (VertexDoesNotExistException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	private void setUp6() {
		graph = new AdjacencyListGraph<String>(false);
		graph.insertVertex("a");
		graph.insertVertex("b");
		graph.insertVertex("c");
		graph.insertVertex("d");
		graph.insertVertex("e");
		try {
			graph.insertEdge(0, 1, 1);
			graph.insertEdge(0, 2, 4);
			graph.insertEdge(0, 4, 2);
			graph.insertEdge(1, 3, 3);
			graph.insertEdge(1, 4, 3);
			graph.insertEdge(2, 3, 1);
			graph.insertEdge(2, 4, 3);
			graph.insertEdge(3, 4, 2);
		} catch (VertexDoesNotExistException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void setUp7() {
		graph = new AdjacencyListGraph<String>(false);
		graph.insertVertex("a");
		graph.insertVertex("b");
		graph.insertVertex("c");
		graph.insertVertex("d");
		graph.insertVertex("e");
		graph.insertVertex("z");
		try {
			graph.insertEdge(0, 1, 2);
			graph.insertEdge(0, 2, 3);
			graph.insertEdge(1, 3, 5);
			graph.insertEdge(1, 4, 2);
			graph.insertEdge(2, 4, 5);
			graph.insertEdge(3, 4, 1);
			graph.insertEdge(3, 5, 2);
			graph.insertEdge(4, 5, 4);
		} catch (VertexDoesNotExistException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	void testInsertVertex() {
		setUp1();
		graph.insertVertex("Cali");
		assertFalse(graph.getVertices().isEmpty());
	}

	@SuppressWarnings("unchecked")
	@Test
	void testInsertEdge() {

		setUp2();
		try {
			graph.insertEdge(0, 1, 121212312);
			graph.insertEdge(1, 3, 121212312);
			graph.insertEdge(3, 4, 121212312);
			graph.insertEdge(3, 1, 121212312);
		} catch (VertexDoesNotExistException e) {
			e.printStackTrace();
		}
		assertTrue(graph.getVertex("Cali").getAdjacencyList().size() == 1
				&& graph.getVertex("Bogotá").getAdjacencyList().size() == 1
				&& graph.getVertex("Pasto").getAdjacencyList().size() == 2);

	}

	@SuppressWarnings("unchecked")
	@Test
	void testDeleteVertex1() {

		setUp4();
		try {
			graph.deleteVertex(0);
		} catch (VertexDoesNotExistException e) {
			e.printStackTrace();
		}
		assertTrue(graph.getVertices().size() == 1 && graph.getVertex("Barranquilla").getAdjacencyList().isEmpty());

	}

	@SuppressWarnings("unchecked")
	@Test
	void testDeleteVertex2() {

		setUp5();
		try {
			graph.deleteVertex(1);
		} catch (VertexDoesNotExistException e) {
			e.printStackTrace();
		}
		assertTrue(graph.getVertices().size() == 2 && graph.getVertex("Cali").getAdjacencyList().isEmpty()
				&& graph.getVertex("Bogota").getAdjacencyList().isEmpty());

	}

	@SuppressWarnings("unchecked")
	@Test
	void testIsAdjacent() {

		setUp3();
		assertTrue((graph.getVertex("Cali")).isAdjacent(graph.getVertex("Bogotá"), 0));

	}

	@SuppressWarnings("unchecked")
	@Test
	void testDeleteEdge() {

		setUp3();
		try {
			graph.deleteEdge(0, 1, 0);
			assertFalse((graph.getVertex("Cali")).isAdjacent(graph.getVertex("Bogotá"), 0));
		} catch (VertexDoesNotExistException e) {
			e.printStackTrace();
		}

	}

	@Test
	void testBFS() {

		setUp3();
		ArrayList<String> list = new ArrayList<String>();
		list.add("Cali");
		list.add("Bogotá");
		list.add("Barranquilla");
		list.add("Medellín");
		list.add("Pasto");
		assertEquals(list, graph.BFS(0));

	}

	@SuppressWarnings("unchecked")
	@Test
	void testKruskal() {

		setUp6();
		try {
			AdjacencyListGraph<String> newGraph = (AdjacencyListGraph<String>) graph.kruskal();
			assertEquals(4, newGraph.getNumOfEdges());
		} catch (VertexDoesNotExistException e) {
			e.printStackTrace();
		}

	}

	@Test
	void testDFS() {

		setUp3();
		ArrayList<String> list = new ArrayList<String>();
		list.add("Cali");
		list.add("Bogotá");
		list.add("Medellín");
		list.add("Pasto");
		list.add("Barranquilla");
		assertEquals(list, graph.DFS());

	}

	@Test
	void testFloyd() {

		setUp6();
		double[] arr = { 0, 1, 4, 4, 2, 1, 0, 4, 3, 3, 4, 4, 0, 1, 3, 4, 3, 1, 0, 2, 2, 3, 3, 2, 0 };
		double[][] m = auxFillMatrix(graph.getVertices().size(), graph.getVertices().size(), arr);
		double[][] floyd = graph.floydWarshal();
		boolean pass = true;
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				if (floyd[i][j] != m[i][j]) {
					pass = false;
				}
			}
		}
		assertTrue(pass);

	}

	@Test
	void testDijkstra() {
		setUp7();
		double[][] expected = { { 0, 2, 3, 5, 4, 7 }, { -1, 0, 0, 4, 1, 3 } };
		double[][] obtained = graph.Dijkstra("a");
		for (int i = 0; i < expected[0].length; i++) {
			assertEquals(expected[0][i], obtained[0][i]);
			assertEquals(expected[1][i], obtained[1][i]);
		}
	}

	@Test
	void testPrim() {

		setUp6();
		int[] arr = { -1, 0, 3, 4, 0 };
		int[] prim = graph.prim();
		boolean pass = true;
		try {
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] != prim[i]) {
					pass = false;
				}
			}
		} catch (Exception e) {
			pass = false;
		}
		assertTrue(pass);

	}

	private double[][] auxFillMatrix(int rows, int columns, double[] m) {

		int count = 0;
		double[][] result = new double[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {

				result[i][j] = m[count];
				count++;

			}
		}
		return result;

	}

}
