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
		pair = new VertexPair(a.item(),b.item());
		weight = w;
	}
	
	public int weight(){
		return weight;
	}
	
	public void setWeight(int w){
		this.weight = w;
	}
	
	public boolean equals(Edge e){
		return (this.pair.equals(e.pair));
	}
	
	public int hashCode(){
		return 0; //TODO
	}
	
}