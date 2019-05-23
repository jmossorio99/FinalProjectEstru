package model;

public class Data implements Comparable {

	private double price;
	private int time;
	
	public Data(double price, int time) {

		this.price = price;
		this.time = time;

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

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

}
