package model;

import java.util.Comparator;

public class CompareEdgesByData<T> implements Comparator<Edge<T>>{

	@Override
	public int compare(Edge<T> o1, Edge<T> o2) {
		return o1.compareTo(o2);
	}

}
