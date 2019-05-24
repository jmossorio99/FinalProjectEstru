package model;

import java.util.ArrayList;
import java.util.PriorityQueue;

import exceptions.VertexDoesNotExistException;

public class AdjacencyMatrixGraph<T,K extends Comparable<K>> implements IGenericGraph<T,K> {

	private boolean isDirected;
	private PriorityQueue<Edge<T,K>>[][] adyacencyMatrix;
	private ArrayList<Vertex<T,K>> vertexOrder;
	private ArrayList<Edge<T,K>> edgeOrder;
	private int numberOfEdge;
	
	public AdjacencyMatrixGraph(boolean isDirected) {
		this.isDirected=isDirected;
		numberOfEdge=0;
		vertexOrder = new ArrayList<Vertex<T,K>>();
 		edgeOrder = new ArrayList<Edge<T,K>>();
		
	}
	
	@Override
	public void insertVertex(T value) {
	
		Vertex<T, K> v = new Vertex<T, K>(value);
		vertexOrder.add(v);
		
		newVertexToAdyacencyMatrix(vertexOrder.size());
		
	}

	@Override
	public void insertEdge(int from, int to, K data) throws VertexDoesNotExistException {
		
		
		if(isDirected) {
			numberOfEdge++;
			Edge<T,K> edge = new Edge<T,K>(vertexOrder.get(from), vertexOrder.get(to), data, numberOfEdge);
			edgeOrder.add(edge);
			 
			PriorityQueue<Edge<T,K>> temp = adyacencyMatrix[from][to];
			temp.add(edge);
		}
		else {
			numberOfEdge++;
			Edge<T,K> edge = new Edge<T,K>(vertexOrder.get(from), vertexOrder.get(to), data, numberOfEdge);
			numberOfEdge++;
			Edge<T,K> edge2 = new Edge<T,K>(vertexOrder.get(to), vertexOrder.get(from), data, numberOfEdge);
			edgeOrder.add(edge);
			edgeOrder.add(edge2);
			 
			PriorityQueue<Edge<T,K>> temp = adyacencyMatrix[from][to];
			temp.add(edge);
			temp.add(edge2);
		}
		
	}

	@Override
	public void deleteVertex(int v) {
		
		vertexOrder.remove(v);
		deleteVertexFromTheMatrix(v,vertexOrder.size());

	}

	public Edge<T,K> searchEdgeById(int id){
		Edge<T,K> found=null;
		boolean stop=false;
		
		for(int i=0;i<edgeOrder.size() && !stop;i++) {
			
			Edge<T,K> temp = edgeOrder.get(i);
			if(temp.getId()==id) {
				found = temp;
				stop=true;
			}
			
		}
		
		return found;
	}
	
	@Override
	public void deleteEdge(int from, int to, int id) {
		
		PriorityQueue<Edge<T,K>> temp = adyacencyMatrix[from][to];
        
		if(isDirected) {
			Edge<T,K> edge = searchEdgeById(id);
			
			temp.remove(edge);
			
			edgeOrder.remove(edge);
		}
		else {
            Edge<T,K> edge = searchEdgeById(id);
            Edge<T,K> edge2 = searchEdgeById(id+1);
            
			temp.remove(edge);
			temp.remove(edge2);
			
			edgeOrder.remove(edge);
			edgeOrder.remove(edge2);
			
		}
		
	}

	@Override
	public ArrayList BFS(int origin) {
		
		
		return null;
	}

	@Override
	public int[] DFS() {
		
		
		return null;
	}
	
	public void newVertexToAdyacencyMatrix(int numberOfVertices) {
		
		PriorityQueue<Edge<T, K>>[][] newAdyacencyMatrix = new PriorityQueue[numberOfVertices][numberOfVertices];

		if(adyacencyMatrix==null) {
			PriorityQueue<Edge<T, K>> temp = new PriorityQueue<Edge<T, K>>(1000, new CompareEdgesByData());
		    for(int i=0;i<newAdyacencyMatrix.length;i++) {
		    	for(int j=0;j<newAdyacencyMatrix.length;j++) {
		    		newAdyacencyMatrix[i][j]=temp;
		    	}
		    }
		   
		    adyacencyMatrix=newAdyacencyMatrix;
		    
		}
		else {
		for (int i = 0; i < adyacencyMatrix.length; i++) {
			for (int j = 0; j < adyacencyMatrix.length; j++) {

				newAdyacencyMatrix[i][j] = adyacencyMatrix[i][j];

				if (i == adyacencyMatrix.length - 1 || j == adyacencyMatrix.length - 1) {
					PriorityQueue<Edge<T, K>> temp = new PriorityQueue<Edge<T, K>>(1000, new CompareEdgesByData());
					newAdyacencyMatrix[i][j] = temp;
					
				}
			}
		  }
		
		adyacencyMatrix=newAdyacencyMatrix;
		
		}
	}

	public void deleteVertexFromTheMatrix(int position,int newSize) {
		
		PriorityQueue<Edge<T, K>>[][] newAdyacencyMatrix = new PriorityQueue[newSize][newSize];
		
		for(int i=0;i<adyacencyMatrix.length;i++) {
			for(int j =0;j<adyacencyMatrix.length;j++) {
				
				if(!(i==position || j==position)) {
					
					if(i<position && j<position) {
						newAdyacencyMatrix[i][j]=adyacencyMatrix[i][j];
					}
					else if(j>position && i<position) {
						newAdyacencyMatrix[i][j-1]=adyacencyMatrix[i][j];
					}
					else if(i>position && j<position) {
						newAdyacencyMatrix[i-1][j]=adyacencyMatrix[i][j];
					}
					else {
						newAdyacencyMatrix[i-1][j-1]=adyacencyMatrix[i][j];
					}
					
				}
				
			}
		}
		
		adyacencyMatrix=newAdyacencyMatrix;
		
	}
	
	public String verifyMessaje() {
		String messaje="";
		for(int i=0;i<edgeOrder.size();i++) {
		
			messaje=edgeOrder.get(i).getData().toString()+"\n";
			
		}
		
		return messaje;
	}

}
