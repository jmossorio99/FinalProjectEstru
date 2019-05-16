package model;

public class Edge<T, K> {

	private Vertex<T, K> vertexTo;
	private Vertex<T, K> vertexFrom;
	private K data;

	public Edge(Vertex<T, K> v, Vertex<T, K> v2, K data) {

		setVertexTo(v);
		setVertexFrom(v2);
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

	public Object getData() {
		return data;
	}

	public void setData(K data) {
		this.data = data;
	}

}
