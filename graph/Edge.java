package graph;

import list.*;

public class Edge {

	Vertex vertex;
	Vertex other;
	int weight;
	Edge partner;

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
		vertex = a;
		other = b;
		weight = w;
		partner = new Edge(b, a, w, this);
	}
	
	public Edge(Vertex a, Vertex b, int w, Edge p){
		vertex = a;
		other = b;
		weight = w;
		partner = p;
	}
	
	
	public Vertex other(){
		return other;
	}
	
	public int weight(){
		return weight;
	}
	
	public int hashCode(){
		return 0; //TODO
	}
	
}
