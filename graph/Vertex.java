package graph;

import list.*;

public class Vertex {

	private Object Obj1;
	private DList edges;
	

	/**
	 * Vertex constructor that creates a Vertex object, which includes the
	 * attributes: obj1 for the original object, and a DList of objects that
	 * obj1 is connected to.
	 */
	protected Vertex(Object name) {
		Obj1 = name;
		DList edges = new DList(); //each item in the Dlist should be a vertex pair
	}
	
	protected int Degree() {
		return edges.length();
	}
	
	protected Object Obj1() {
		return Obj1;
	}
	
	protected boolean Equals(Vertex v) {
		return this.Obj1.equals(v.Obj1); 
	}
	
	public int Hashcode() {
		//let stephen do hashcode stuff here
		return 0;
	}
	

}
