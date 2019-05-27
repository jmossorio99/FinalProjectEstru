package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.VertexDoesNotExistException;
import model.AdjacencyListGraph;
import model.AdjacencyMatrixGraph;
import model.Data;
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

		setUp2();

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

	@Test
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
	
}
