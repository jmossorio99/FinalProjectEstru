package model;

public class GenericMinHeap<T extends Comparable<T>> {

	private T[] heap;
	private int size;
	private int maxsize;

	 @SuppressWarnings("unchecked")
		public GenericMinHeap(int maxsize) {
	        this.maxsize = maxsize;
	        this.size = 0;
	        heap = (T[]) new Comparable[maxsize];
	    }
	    
	    public GenericMinHeap(T[] array, int maxsize) {
	    	this.maxsize = maxsize;
	        this.size = array.length;
	        heap = (T[]) new Comparable[maxsize];
	        for(int i=0;i<array.length;i++)
	        	heap[i+1] = array[i];
	        buildMinHeap();
	    }
	 
	    private int parent(int pos) {
	        return pos / 2;
	    }
	 
	    private int leftChild(int pos) {
	        return (2 * pos);
	    }
	 
	    private int rightChild(int pos) {
	        return (2 * pos) + 1;
	    }
	 
	    private void swap(int fpos,int spos) {
	        T tmp;
	        tmp = heap[fpos];
	        heap[fpos] = heap[spos];
	        heap[spos] = tmp;
	    }
	 
	    @SuppressWarnings("unchecked")
		private void minHeapify(int pos) {
	    	int smallest;
	    	int l = leftChild(pos);
	    	int r = rightChild(pos);
	    	if(l <= size && heap[l].compareTo(heap[pos]) < 0)
	    		smallest = l;
	    	else
	    		smallest = pos;
	    	if(r <= size && heap[r].compareTo(heap[smallest]) < 0)
	    		smallest = r;
	    	if (smallest != pos) {
	    		swap(pos, smallest);
	    		minHeapify(smallest);
	    	}
	    }
	    
	    public int getSize() {
	    	return this.size;
	    }
	 
	    @SuppressWarnings("unchecked")
		public void insert(T element) {
	    	size += 1;
	        heap[size] = element;
	        int current = size;
	        while(current > 1 && heap[current].compareTo(heap[parent(current)]) < 0) {
	            swap(current,parent(current));
	            current = parent(current);
	        }	
	    }
	    
	    public void print() {
	    	System.out.print("[");
	        for (int i = 1; i <= size; i++ ) {
	        	if(i==size)
	        		System.out.print(heap[i]);
	        	else
	        		System.out.print(heap[i]+", ");
	        }
	        System.out.println("]");
	    }
	 
	    public void buildMinHeap() {
	    	for(int i=size/2;i>=1;i--)
	    		minHeapify(i);
	    }
	    
	    public T get(int pos) {
	    	return heap[pos];
	    }
	    
	    public void set(int pos, T element) {
	    	heap[pos] = element;
	    }
	    
	    public T remove(int pos) {
	    	T extracted = heap[pos];
	        heap[pos] = heap[size--]; 
	        minHeapify(pos);
	        return extracted;
	    }

}
