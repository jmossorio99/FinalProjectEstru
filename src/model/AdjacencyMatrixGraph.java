package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import exceptions.VertexDoesNotExistException;

public class AdjacencyMatrixGraph<T> implements IGenericGraph<T> {

	private boolean isDirected;
	private PriorityQueue<Edge<T>>[][] adyacencyMatrix;
	private ArrayList<Vertex<T>> vertexOrder;
	private ArrayList<Edge<T>> edgeOrder;
	private int numberOfEdge;
	private double[][] matrixToFloyd;
	private boolean compareByPrice=true;
	
	public AdjacencyMatrixGraph(boolean isDirected) {
		this.isDirected=isDirected;
		numberOfEdge=0;
		vertexOrder = new ArrayList<Vertex<T>>();
 		edgeOrder = new ArrayList<Edge<T>>();
		
	}
	
	public void setCompareByPrice(boolean value) {
		compareByPrice=value;
	}
	
	@Override
	public void insertVertex(T value) {
	
		Vertex<T> v = new Vertex<T>(value);
		vertexOrder.add(v);
		
		newVertexToAdyacencyMatrix(vertexOrder.size());
		
	}

	@Override
	public void insertEdge(int from, int to, double data) throws VertexDoesNotExistException {
		
		
		if(isDirected) {
			
			Edge<T> edge = new Edge<T>(vertexOrder.get(from), vertexOrder.get(to), data, numberOfEdge);
			edgeOrder.add(edge);
			
			numberOfEdge++;
			
			PriorityQueue<Edge<T>> temp = adyacencyMatrix[from][to];
			temp.add(edge);
		}
		else {
			
			Edge<T> edge = new Edge<T>(vertexOrder.get(from), vertexOrder.get(to), data, numberOfEdge);
			numberOfEdge++;
			Edge<T> edge2 = new Edge<T>(vertexOrder.get(to), vertexOrder.get(from), data, numberOfEdge);
			edgeOrder.add(edge);
			edgeOrder.add(edge2);
			
			numberOfEdge++;
			
			PriorityQueue<Edge<T>> temp = adyacencyMatrix[from][to];
			temp.add(edge);
			temp.add(edge2);
		}
		
	}

	@Override
	public void deleteVertex(int v) {
		
		vertexOrder.remove(v);
		deleteVertexFromTheMatrix(v,vertexOrder.size());

	}

	public Edge<T> searchEdgeById(int id){
		Edge<T> found=null;
		boolean stop=false;
		
		for(int i=0;i<edgeOrder.size() && !stop;i++) {
			
			Edge<T> temp = edgeOrder.get(i);
			if(temp.getId()==id) {
				found = temp;
				stop=true;
			}
			
		}
		
		return found;
	}
	
	@Override
	public void deleteEdge(int from, int to, int id) {
		
		PriorityQueue<Edge<T>> temp = adyacencyMatrix[from][to];
        
		if(isDirected) {
			Edge<T> edge = searchEdgeById(id);
			
			temp.remove(edge);
			
			edgeOrder.remove(edge);
		}
		else {
            Edge<T> edge = searchEdgeById(id);
            Edge<T> edge2 = searchEdgeById(id+1);
            
			temp.remove(edge);
			temp.remove(edge2);
			
			edgeOrder.remove(edge);
			edgeOrder.remove(edge2);
			
		}
		
	}

	@Override
	public ArrayList BFS(int origin) {
		
		ArrayList<Vertex<T>> vertices = new ArrayList<Vertex<T>>();
		
		if(origin<vertexOrder.size()) {
			
			boolean [] visited = new boolean[vertexOrder.size()];
			Queue<Integer> tail = new LinkedList<Integer>();
			
			int position = origin;
			tail.add(position);
			visited[position]=true;
			vertices.add(vertexOrder.get(position));
			
			while(!tail.isEmpty()) {
			
				int current=tail.poll();
				
			for(int i=0;i<vertexOrder.size();i++) {
				
				PriorityQueue<Edge<T>> temp = adyacencyMatrix[current][i];
				
				if(!temp.isEmpty()) {
					
					if(visited[i]=false) {
						
						visited[i]=true;
						vertices.add(vertexOrder.get(i));
						tail.add(i);
						
					}
					
				}
				
			 }
			
			}
		}
		
		return vertices;
	}

	@Override
	public ArrayList<T> DFS() {
		
		
		return null;
	}
	
	public void newVertexToAdyacencyMatrix(int numberOfVertices) {
		
		PriorityQueue<Edge<T>>[][] newAdyacencyMatrix = new PriorityQueue[numberOfVertices][numberOfVertices];

		if(adyacencyMatrix==null) {
			
			PriorityQueue<Edge<T>> temp = new PriorityQueue<Edge<T>>(1000, new CompareEdgesByData());
		    
		    newAdyacencyMatrix[0][0]=temp;
		 
		   
		    adyacencyMatrix=newAdyacencyMatrix;   
		    
		}
		else {
			
		for (int i = 0; i < adyacencyMatrix.length; i++) {
			for (int j = 0; j < adyacencyMatrix.length; j++) {

				newAdyacencyMatrix[i][j] = adyacencyMatrix[i][j];

			}
		  }
		
		fillNulls(newAdyacencyMatrix);
		
		adyacencyMatrix=newAdyacencyMatrix;
		
		}
		
	}
	
	private void fillNulls(PriorityQueue<Edge<T>>[][] matrix) {
		
		
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix.length;j++) {
				if(matrix[i][j]==null) {
					PriorityQueue<Edge<T>> temp = new PriorityQueue<Edge<T>>(1000, new CompareEdgesByData());
					matrix[i][j]=temp;
				}
			}
		}
	}

	public void deleteVertexFromTheMatrix(int position,int newSize) {
		
		PriorityQueue<Edge<T>>[][] newAdyacencyMatrix = new PriorityQueue[newSize][newSize];
		
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
	
	
	public void seeMatrix(PriorityQueue<Edge<T>>[][] matrix) {
		
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix.length;j++) {
				
				if(matrix[i][j]==null) {
					System.out.println("nulo  i="+i+"  j="+j);
				}
				else {
					System.out.println("no nulo  "+"i="+i+"  j="+j);
				}
				
			}
		}
		
	}
	
	public ArrayList<Vertex<T>> getVertices() {
		return vertexOrder;
	}
	
	public PriorityQueue<Edge<T>> getQueue(int row,int column){
		
		return adyacencyMatrix[row][column];
		
	}
	
	public ArrayList<Edge<T>> getEdges(){
		
		return edgeOrder;
		
	}

	@Override
	public AdjacencyListGraph<T> kruskal() throws VertexDoesNotExistException {
		
		
		return null;
	}

	@Override
	public double[][] floydWarshal() {
		prepareMatrixToFloyd();		
		
		for(int p=0;p<matrixToFloyd.length;p++) {
			for(int i=0;i<matrixToFloyd.length;i++) {
				for(int j=0;j<matrixToFloyd.length;j++) {
					
					double sum = matrixToFloyd[i][p]+matrixToFloyd[p][j];
					
					if(!(sum<0)) {
						
					  if(sum<matrixToFloyd[i][j]) {
						  matrixToFloyd[i][j]=sum;
					  }
						
					}
					
				}
			}
		}
		
		return matrixToFloyd;
	}
	
	private void prepareMatrixToFloyd() {
		
		matrixToFloyd = new double[vertexOrder.size()][vertexOrder.size()];
	
		for(int i=0;i<vertexOrder.size();i++) {
			for(int j=0;j<vertexOrder.size();j++) {
				
				Edge<T> temp = adyacencyMatrix[i][j].peek();
                
				if(temp!=null) {
					matrixToFloyd[i][j]= temp.getData();
				}
				else {
					matrixToFloyd[i][j]=Double.MAX_VALUE;
				}
			}
		}
		
	}

}
