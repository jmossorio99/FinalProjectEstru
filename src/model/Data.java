package model;

import java.util.Comparator;

public class Data implements Comparable<Data>, Comparator<Data> {

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
	public int compareTo(Data d) {

		if (this.price > d.getPrice()) {
			return 1;
		} else if (this.price < d.getPrice()) {
			return -1;
		}
		return 0;

	}

	@Override
	public int compare(Data o1, Data o2) {

		if (this.price + o1.getPrice() < o2.getPrice()) {
			return 1;
		}
		return -1;
	}

}
