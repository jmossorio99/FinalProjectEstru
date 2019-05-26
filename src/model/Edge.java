package model;

public class Edge<T> {

	private Vertex<T> vertexFrom;
	private Vertex<T> vertexTo;
	private double data;
	private int id;

	public Edge(Vertex<T> v, Vertex<T> v2, double data, int id) {

		setVertexFrom(v);
		setVertexTo(v2);
		this.setData(data);
		this.id = id;

	}

	public Vertex<T> getVertexTo() {
		return vertexTo;
	}

	public void setVertexTo(Vertex<T> vertexTo) {
		this.vertexTo = vertexTo;
	}

	public Vertex<T> getVertexFrom() {
		return vertexFrom;
	}

	public void setVertexFrom(Vertex<T> vertexFrom) {
		this.vertexFrom = vertexFrom;
	}

	public double getData() {
		return data;
	}

	public void setData(double data) {
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@SuppressWarnings("unchecked")
	public int compareTo(Edge<T> o2) {

		if (this.getData() > o2.getData()) {
			return 1;
		} else if (this.getData() < o2.getData()) {
			return -1;
		}
		return 0;

	}

}
