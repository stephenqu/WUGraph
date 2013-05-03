package graph;

import list.*;

public class Edge {

	VertexPair pair;
	int weight;
	DListNode node1;
	DListNode node2;

	/**
	 * Edge constructor creates an Edge object that consists of the first
	 * vertex, second vertex, and the weight. It also finds its corresponding
	 * partner to facilitate removeEdge()
	 * 
	 * @param a
	 *            is one of the vertex objects.
	 * @param b
	 *            is the other of the vertex objects for this edge.
	 * @param w
	 *            is the associated weight of this edge.
	 */
	public Edge(Vertex a, Vertex b, int w) {
		pair = new VertexPair(a.item(), b.item());
		weight = w;
	}

	/**
	 * This weight() method returns the weight of the edge, which in itself is
	 * hidden.
	 * 
	 * @return an integer representative of the weight of the edge
	 */
	public int weight() {
		return weight;
	}

	/**
	 * This setWeight() method allows an outside source to change the weight of
	 * an edge, without violating abstraction rules.
	 * 
	 * @param w
	 *            is the new weight for the edge.
	 */
	public void setWeight(int w) {
		this.weight = w;
	}

	/**
	 * This pair() method gives the program access to the pair object
	 * constructed by Edge
	 * 
	 * @return VertexPair pair
	 */
	public VertexPair pair() {
		return pair;
	}

	/**
	 * This equals() method overrides the default equals method and compares
	 * whether an edge is the same as another edge, regardless of the order in
	 * which the vertices are provided and the edge's weight
	 * 
	 * @param e
	 *            is the edge given to compare the "this" edge
	 * @return true if both edges are equals, false otherwise
	 */
	public boolean equals(Object e) {
		if (e instanceof Edge) {
			return (this.pair.equals(((Edge) e).pair));
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode() Overrides default java hash code by
	 * calling VertexPair's hash code
	 * 
	 * @return integer for the hash code
	 */
	public int hashCode() {
		return pair.hashCode();
	}

}