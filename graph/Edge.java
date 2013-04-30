package graph;

import list.*;

public class Edge {

	Object Obj1;
	Object Obj2;
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
	protected Edge(Object a, Object b, int w) {
		Obj1 = a;
		Obj2 = b;
		weight = w;
		// to find partner, traverse through adjacencylist, find vertex.obj1 =
		// b; then find the corresponding edge

	}
}
