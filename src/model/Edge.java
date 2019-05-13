package model;

public class Edge<T> {

	private Vertex<T> vertexTo;
	private Vertex<T> vertexFrom;
	private double price;
	private int time;
	private double distance;

	public Edge(Vertex<T> v, Vertex<T> v2, double price, int time, double distance) {

		setVertexTo(v);
		setVertexFrom(v2);
		this.price = price;
		this.time = time;
		this.distance = distance;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

}
