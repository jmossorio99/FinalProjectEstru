package model;


public class GenericMinPriorityQueue<T extends Comparable<T>> {

	private GenericMinHeap<T> Heap;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericMinPriorityQueue(int maxsize) {
		Heap = new GenericMinHeap(maxsize);
	}
	
	public boolean isEmpty() {
		if(Heap.getSize() == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public T peek() {
		if(Heap.getSize() <= 0) {
			return null;
		}else {
			return Heap.get(1);
		}
	}
	
	public T poll() {
		if (Heap.getSize() <= 0)
			return null;
		else {
			T minVal = Heap.remove(1);
			return minVal;
		}
	}
	
	public void offer(T elem) {
		Heap.insert(elem);
	}
	
}
