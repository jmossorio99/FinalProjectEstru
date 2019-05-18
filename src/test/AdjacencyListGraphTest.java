package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exceptions.VertexDoesNotExistException;
import model.*;

class AdjacencyListGraphTest {

	private AdjacencyListGraph<String, Data> graph;

	// empty directed graph
	private void setUp1() {

		graph = new AdjacencyListGraph<String, Data>(true);

	}

	// directed graph with vertices
	private void setUp2() {

		graph = new AdjacencyListGraph<String, Data>(true);
		graph.insertVertex("Cali");
		graph.insertVertex("Bogotá");
		graph.insertVertex("Medellín");
		graph.insertVertex("Pasto");
		graph.insertVertex("Barranquilla");

	}

	// nonDirected graph with vertices and edges
	private void setUp3() {

		graph = new AdjacencyListGraph<String, Data>(false);
		graph.insertVertex("Cali");
		graph.insertVertex("Bogotá");
		graph.insertVertex("Medellín");
		graph.insertVertex("Pasto");
		graph.insertVertex("Barranquilla");
		try {
			graph.insertEdge(0, 1, new Data(250000, 45));
			graph.insertEdge(1, 2, new Data(500000, 160));
			graph.insertEdge(2, 3, new Data(150000, 200));
			graph.insertEdge(1, 4, new Data(800000, 150));
			graph.insertEdge(0, 4, new Data(650000, 180));
		} catch (VertexDoesNotExistException e) {
			e.printStackTrace();
		}

	}

	// nonDirected Graph with two vertices and an edge
	private void setUp4() {

		graph = new AdjacencyListGraph<String, Data>(false);
		graph.insertVertex("Cali");
		graph.insertVertex("Barranquilla");
		try {
			graph.insertEdge(0, 1, new Data(500000, 500));
		} catch (VertexDoesNotExistException e) {
			e.printStackTrace();
		}

	}

	// directed graph with three vertices and edges
	private void setUp5() {

		graph = new AdjacencyListGraph<String, Data>(true);
		graph.insertVertex("Cali");
		graph.insertVertex("Barranquilla");
		graph.insertVertex("Bogota");
		try {
			graph.insertEdge(0, 1, new Data(500000, 500));
			graph.insertEdge(1, 0, new Data(500000, 600));
			graph.insertEdge(2, 1, new Data(200000, 250));
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
			graph.insertEdge(0, 1, new Data(121212312, 52));
			graph.insertEdge(1, 3, new Data(121212312, 52));
			graph.insertEdge(3, 4, new Data(121212312, 52));
			graph.insertEdge(3, 1, new Data(121212312, 52));
		} catch (VertexDoesNotExistException e) {
			e.printStackTrace();
		}
		assertTrue(graph.getVertex("Cali").getAdjacencyList().size() == 1
				&& graph.getVertex("Bogotá").getAdjacencyList().size() == 1
				&& graph.getVertex("Pasto").getAdjacencyList().size() == 2);

	}

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

	@Test
	void testIsAdjacent() {

		setUp3();
		assertTrue((graph.getVertex("Cali")).isAdjacent(graph.getVertex("Bogotá"), 0));

	}

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

}
