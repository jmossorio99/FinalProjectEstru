package model;

import java.util.Comparator;

public class CompareEdgesByData<T, K extends Comparable<K>> implements Comparator<Edge<T, K >>{

	@Override
	public int compare(Edge<T, K> o1, Edge<T, K> o2) {
		return o1.compareTo(o2);
	}

}
