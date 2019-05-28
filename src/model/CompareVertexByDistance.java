package model;

import java.util.Comparator;

public class CompareVertexByDistance<T> implements Comparator<Vertex>{

	@Override
	public int compare(Vertex v1, Vertex v2) {
		return v1.compareTo(v2);
	}

}
