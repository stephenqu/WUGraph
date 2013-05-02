package graph;

import list.*;

public class Vertex {

	private Object item;
	protected DList edges;
	

	/**
	 * Vertex constructor that creates a Vertex object, which includes the
	 * attributes: item for the original object, and a DList of objects that
	 * item is connected to.
	 */
	public Vertex(Object name) {
		item = name;
		DList edges = new DList(); //each item in the Dlist should be a vertex pair
	}
	
	public int degree() {
		return edges.length();
	}
	
	public Object item() {
		return item;
	}
	
	public boolean equals(Vertex v) {
		return this.item.equals(v.item); 
	}
	
	public DList edges(){
		return edges;
	}
	
	public int hashCode() {
		return item.hashCode();
	}
	

}
