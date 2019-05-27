package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exceptions.VertexDoesNotExistException;
import model.AdjacencyListGraph;
import model.AdjacencyMatrixGraph;
import model.Vertex;

class AdyacencyMatrixGraphTest {

	private AdjacencyMatrixGraph graph;

	@Test
	void setUp1() {

		graph = new AdjacencyMatrixGraph<String>(true);

	}

	@Test
	void setUp2() {

		setUp1();
		graph.insertVertex("Cali");
		graph.insertVertex("Bogotá");
		graph.insertVertex("Medellín");
		graph.insertVertex("Pasto");
		graph.insertVertex("Barranquilla");

	}

	@Test
	void setUp3() {

		graph = new AdjacencyMatrixGraph<String>(false);
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

	@Test
	void setUp4() {

		setUp1();
		graph.insertVertex("Cali");
		graph.insertVertex("Barranquilla");

		try {
			graph.insertEdge(0, 1, 500000);
		} catch (VertexDoesNotExistException e) {
			e.printStackTrace();
		}
	}

	@Test
	void setUp5() {

		setUp1();
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
	void setUp6() {
		graph = new AdjacencyMatrixGraph<String>(false);
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

	@Test
	void testInsertVertex() {
		setUp1();
		graph.insertVertex("Cali");
		assertFalse(graph.getVertices().isEmpty());
	}

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
		assertFalse(graph.getQueue(0, 1).isEmpty() | graph.getQueue(1, 3).isEmpty() | graph.getQueue(3, 4).isEmpty()
				| graph.getQueue(3, 1).isEmpty());

	}

	@Test
	void testDeleteVertex() {

		setUp4();

		graph.deleteVertex(0);

		assertTrue(
				graph.getVertices().size() == 1 && ((Vertex) graph.getVertices().get(0)).getValue() == "Barranquilla");

	}

	@Test
	void testDeleteEdge() {

		setUp3();

		graph.deleteEdge(0, 1, 0);

	}

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
	void testPrim() {

		setUp6();
		int[] arr = { -1, 0, 3, 4, 0 };
		int[] prim = graph.prim();
		boolean pass = true;
		try {
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] != prim[i]) {
					System.out.println(i);
					pass = false;
				}
			}
		} catch (Exception e) {
			pass = false;
		}
		assertTrue(pass);

	}

}
