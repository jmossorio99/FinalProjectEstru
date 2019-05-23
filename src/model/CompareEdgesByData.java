package model;

import java.util.Comparator;

public class CompareEdgesByData implements Comparator<Edge>{

	@Override
	public int compare(Edge o1, Edge o2) {
		return o1.compareTo(o2);
	}

}
