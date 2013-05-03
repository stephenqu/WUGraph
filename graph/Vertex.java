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
		edges = new DList(); // each item in the Dlist should be a vertex pair
	}

	/**
	 * This degree() method returns the degree of the vertex. The degree is the
	 * number of edges coming out of this vertex
	 * 
	 * @return an integer for the degree
	 */
	public int degree() {
		return edges.length();
	}

	/**
	 * This item() method returns the Vertex item itself, not the object which
	 * also has references to its edges
	 * 
	 * @return an Object that is the item of the vertex
	 */
	public Object item() {
		return item;
	}

	/**
	 * This other() method returns the corresponding vertex of the edge
	 * 
	 * @param e
	 *            is the edge that you wish to examine
	 * @return a Vertex object that is connected to "this" Vertex via an edge
	 */
	public Vertex other(Edge e) {
		Vertex a = null;
		Vertex b = null;
		a = new Vertex(e.pair().object1);
		b = new Vertex(e.pair().object2);
		if (this.equals(a)) {
			return b;
		} else {
			return a;
		}
	}

	/**
	 * This equals() method tells the program whether two vertices are equal,
	 * despite differences in their dlist of edges
	 * 
	 * @param v
	 *            is the Vertex the program wishes to compare "this" Vertex to
	 * @return true if equals; false otherwise
	 */
	public boolean equals(Object v) {
		if (v instanceof Vertex) {
			return this.item.equals(((Vertex) v).item);
		} else {
			return false;
		}
	}

	/**
	 * This edges() method allows something outside this program to access the
	 * Vertex's edges
	 * 
	 * @return DList of edges for this vertex
	 */
	public DList edges() {
		return edges;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode() overrides java built in hash code
	 * 
	 * @return integer that is the Vertex's hash code
	 */
	public int hashCode() {
		return item.hashCode();
	}

}