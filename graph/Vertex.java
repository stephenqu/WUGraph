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
		DList edges = new DList(); //each item in the Dlist should be a vertex pair
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
		try{
			a = (Vertex) e.node1.item();
			b = (Vertex) e.node2.item();
		}catch (InvalidNodeException q){
			System.err.println(q);
			q.printStackTrace(); //This shouldn't happen
		}
		if (this.equals(a)){
			return b;
		}else{
			return a;
		}
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