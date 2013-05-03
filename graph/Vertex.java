package graph;

import list.*;

public class Vertex {

	private Object item;
	protected DList edges;
	protected DListNode node;
	

	/**
	 * Vertex constructor that creates a Vertex object, which includes the
	 * attributes: item for the original object, and a DList of objects that
	 * item is connected to.
	 */
	public Vertex(Object name) {
		item = name;
		edges = new DList(); //each item in the Dlist should be a vertex pair
	}
	
	public int degree() {
	    return edges.length();
	}
	
	public Object item() {
		return item;
	}
	
	public Vertex other(Edge e){
		Vertex a = null;
		Vertex b = null;
		a = new Vertex(e.pair().object1);
		b = new Vertex(e.pair().object2);
		if (this.equals(a)){
			return b;
		}else{
			return a;
		}
	}
	
	public boolean equals(Object v) {
	    if (v instanceof Vertex){
		return this.item.equals(((Vertex) v).item); 
	    }else{
		return false;
	    }
	}
	
	public DList edges(){
		return edges;
	}
	
	public int hashCode() {
		return item.hashCode();
	}
	

}