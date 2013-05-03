/* Kruskal.java */

import graph.*;
import set.*;
import hash.*;

/**
 * The Kruskal class contains the method minSpanTree(), which implements
 * Kruskal's algorithm for computing a minimum spanning tree of a graph.
 */

public class Kruskal {

	/**
	 * minSpanTree() returns a WUGraph that represents the minimum spanning tree
	 * of the WUGraph g. The original WUGraph g is NOT changed.
	 * 
	 * @param g
	 *            is the graph to conduct minimum spanning tree upon
	 * @return is a WUGraph of the minimum spanning tree
	 */
	public static WUGraph minSpanTree(WUGraph g) {
		WUGraph t = new WUGraph();
		Object[] vertices = g.getVertices();
		HashTableChained vertexHashValue = new HashTableChained(vertices.length);
		Edge2[] edgeList = new Edge2[2 * g.edgeCount()];
		int counter = 0;
		for (int a = 0; a < vertices.length; a++) {
			vertexHashValue.insert(new Entry(vertices[a], new Integer(a)));
			t.addVertex(vertices[a]);
			Neighbors n = g.getNeighbors(vertices[a]);
			for (int b = 0; b < n.neighborList.length; b++) {
				edgeList[counter] = new Edge2(vertices[a], n.neighborList[b],
						n.weightList[b]);
				counter++;
			}
		}
		Sort.sort(edgeList);
		DisjointSets minSpanTree = new DisjointSets(g.vertexCount());
		for (int count = 0; count < edgeList.length; count++) {
			if (edgeList[count] == null) {
				break;
			}
			Edge2 edge = edgeList[count];
			Object vertexU = edge.vertex1();
			Object vertexV = edge.vertex2();
			int valVertU = ((Integer) ((Entry) vertexHashValue.find(new Entry(
					vertexU, new Integer(0)))).value()).intValue();
			int valVertV = ((Integer) ((Entry) vertexHashValue.find(new Entry(
					vertexV, new Integer(0)))).value()).intValue();
			if (!(minSpanTree.find(valVertU) == minSpanTree.find(valVertV))) {
				t.addEdge(vertexU, vertexV, edge.weight());
				minSpanTree.union(minSpanTree.find(valVertU),
						minSpanTree.find(valVertV));
			}
		}
		return t;
	}

}