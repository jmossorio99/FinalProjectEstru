package model;

public class Data implements Comparable<Data> {

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
	public int compareTo(Data arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
