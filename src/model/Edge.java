package model;

public class Edge<T> {

	private Vertex<T> vertexTo;
	private Vertex<T> vertexFrom;
	private T value;

	public Edge(Vertex<T> v, Vertex<T> v2, T value) {

		vertexTo = v;
		vertexFrom = v2;
		this.value = value;

	}

	public Vertex<T> getVertexTo() {
		return vertexTo;
	}

	public void setVertexTo(Vertex<T> vertexTo) {
		this.vertexTo = vertexTo;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}
