package model;

public class Main {

	public static void main(String[] args) {
		
		AdjacencyMatrixGraph<String,Data> graph= new AdjacencyMatrixGraph<String,Data>(true);
		
		graph.insertVertex("Cali");
		graph.insertVertex("bogota");
		
	}

}
