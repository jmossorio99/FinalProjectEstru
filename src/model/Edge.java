package model;


public class Edge<T, K extends Comparable>{

	private Vertex<T, K> vertexFrom;
	private Vertex<T, K> vertexTo;
	private K data;
	private int id;

	public Edge(Vertex<T, K> v, Vertex<T, K> v2, K data, int id) {

		setVertexFrom(v);
		setVertexTo(v2);
		this.setData(data);

	}

	public Vertex<T, K> getVertexTo() {
		return vertexTo;
	}

	public void setVertexTo(Vertex<T, K> vertexTo) {
		this.vertexTo = vertexTo;
	}

	public Vertex<T, K> getVertexFrom() {
		return vertexFrom;
	}

	public void setVertexFrom(Vertex<T, K> vertexFrom) {
		this.vertexFrom = vertexFrom;
	}

	public K getData() {
		return data;
	}

	public void setData(K data) {
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int compareTo(Edge o2) {
		return this.data.compareTo(o2.getData());
	}

}
