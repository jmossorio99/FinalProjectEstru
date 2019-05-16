package model;

public class Data {

	private double price;
	private int time;
	private double distance;

	public Data(double price, int time, double distance) {

		this.price = price;
		this.time = time;
		this.distance = distance;

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
