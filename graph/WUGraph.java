/* WUGraph.java */

package graph;

import list.*;
import hash.*;

/**
 * The WUGraph class represents a weighted, undirected graph. Self-edges are
 * permitted.
 */

public class WUGraph {

	protected DList adjList;
	protected HashTableChained adjListHash;
	protected HashTableChained edgeHash;

	/**
	 * WUGraph() constructs a graph having no vertices or edges.
	 * 
	 * Running time: O(1).
	 */
	public WUGraph() {
		adjList = new DList();
		adjListHash = new HashTableChained(20);
		edgeHash = new HashTableChained(50);
	}

	/**
	 * vertexCount() returns the number of vertices in the graph.
	 * 
	 * Running time: O(1).
	 */
	public int vertexCount() {
	    return adjListHash.size();
	}

	/**
	 * edgeCount() returns the number of edges in the graph.
	 * 
	 * Running time: O(1).
	 */
	public int edgeCount() {
	    return edgeHash.size();
	}

	/**
	 * getVertices() returns an array containing all the objects that serve as
	 * vertices of the graph. The array's length is exactly equal to the number
	 * of vertices. If the graph has no vertices, the array has length zero.
	 * 
	 * (NOTE: Do not return any internal data structure you use to represent
	 * vertices! Return only the same objects that were provided by the calling
	 * application in calls to addVertex().)
	 * 
	 * Running time: O(|V|).
	 */
	public Object[] getVertices() {
	    Object[] listVertices = new Object[vertexCount()];
		int count = 0;
		try{
			for (DListNode node : adjList) {	//apparently still need a try catch
			    listVertices[count] = ((Vertex) node.item()).item();
			    count++;
			}	
		}catch (InvalidNodeException e){
			System.err.println(e);
			e.printStackTrace(); //This shouldn't happen
		}
		return listVertices;
	}

	/**
	 * addVertex() adds a vertex (with no incident edges) to the graph. The
	 * vertex's "name" is the object provided as the parameter "vertex". If this
	 * object is already a vertex of the graph, the graph is unchanged.
	 * 
	 * Running time: O(1).
	 */
	public void addVertex(Object vertex) {
		Vertex v = new Vertex(vertex);
		if (adjListHash.find(v) == null) {
			adjList.insertBack(v);
			v.node = (DListNode) adjList.back();
			adjListHash.insert(v);
		}
	}

	/**
	 * removeVertex() removes a vertex from the graph. All edges incident on the
	 * deleted vertex are removed as well. If the parameter "vertex" does not
	 * represent a vertex of the graph, the graph is unchanged.
	 * 
	 * Running time: O(d), where d is the degree of "vertex".
	 */
	public void removeVertex(Object vertex) {
		Vertex v = (Vertex) adjListHash.find(new Vertex(vertex));
		if (v == null){
		    return;
		}
		try {
		    if (v.edges().length() > 0){
			for (DListNode edge : v.edges()) {
			    edgeHash.remove(edge.item());
			    DListNode vertex1 =  ((Edge) edge.item()).node1;
			    DListNode vertex2 =  ((Edge) edge.item()).node2;
			    vertex1.remove();
			    if (vertex2 != null){
				vertex2.remove();
			    }
			}
		    }
		    v.node.remove();
		    adjListHash.remove(v);
		} catch (InvalidNodeException e){ //Do nothing if Vertex is no longer in adjList
		}
	}

	/**
	 * isVertex() returns true if the parameter "vertex" represents a vertex of
	 * the graph.
	 * 
	 * Running time: O(1).
	 */
	public boolean isVertex(Object vertex) {
	    return (adjListHash.find(new Vertex(vertex)) != null);
	}

	/**
	 * degree() returns the degree of a vertex. Self-edges add only one to the
	 * degree of a vertex. If the parameter "vertex" doesn't represent a vertex
	 * of the graph, zero is returned.
	 * 
	 * Running time: O(1).
	 */
	public int degree(Object vertex) {
	    Vertex v = (Vertex) adjListHash.find(new Vertex(vertex));
	    if (v == null){
		return 0;
	    }
	    return v.degree();
	}

	/**
	 * getNeighbors() returns a new Neighbors object referencing two arrays. The
	 * Neighbors.neighborList array contains each object that is connected to
	 * the input object by an edge. The Neighbors.weightList array contains the
	 * weights of the corresponding edges. The length of both arrays is equal to
	 * the number of edges incident on the input vertex. If the vertex has
	 * degree zero, or if the parameter "vertex" does not represent a vertex of
	 * the graph, null is returned (instead of a Neighbors object).
	 * 
	 * The returned Neighbors object, and the two arrays, are both newly
	 * created. No previously existing Neighbors object or array is changed.
	 * 
	 * (NOTE: In the neighborList array, do not return any internal data
	 * structure you use to represent vertices! Return only the same objects
	 * that were provided by the calling application in calls to addVertex().)
	 * 
	 * Running time: O(d), where d is the degree of "vertex".
	 */
	public Neighbors getNeighbors(Object vertex){
		Vertex v = (Vertex) adjListHash.find(new Vertex(vertex));
		if (v != null && v.degree() > 0){
			Object[] nList = new Object[v.degree()];
			int[] wList = new int[v.degree()];
			try{
				DListNode e = (DListNode) (v.edges()).front();
				for (int i = 0; i < v.degree(); i++){
					nList[i] = (v.other((Edge) e.item())).item();
					wList[i] = ((Edge) e.item()).weight();
					e = (DListNode) e.next();
				}
			}catch (InvalidNodeException e){
				System.err.println(e);
				e.printStackTrace(); //This shouldn't happen
			}
			Neighbors n = new Neighbors();
			n.neighborList = nList;
			n.weightList = wList;
			return n;
		}
		return null;
	}

	/**
	 * addEdge() adds an edge (u, v) to the graph. If either of the parameters u
	 * and v does not represent a vertex of the graph, the graph is unchanged.
	 * The edge is assigned a weight of "weight". If the edge is already
	 * contained in the graph, the weight is updated to reflect the new value.
	 * Self-edges (where u == v) are allowed.
	 * 
	 * Running time: O(1).
	 */
	public void addEdge(Object u, Object v, int weight){
		Vertex a = (Vertex) adjListHash.find(new Vertex(u));
		Vertex b = (Vertex) adjListHash.find(new Vertex(v));
		if ((a!=null) && (b!=null)) {
			Edge query = new Edge(a,b,weight);
			Edge e = (Edge) edgeHash.find(query);
			if (e != null){
				e.setWeight(weight);
			}else{
				edgeHash.insert(query);
				a.edges().insertBack(query);
				query.node1 = (DListNode) a.edges().back();
				if (!(a.equals(b))){
				    b.edges().insertBack(query);
				    query.node2 = (DListNode) b.edges().back();
				}
			}
		}
	}

	/**
	 * removeEdge() removes an edge (u, v) from the graph. If either of the
	 * parameters u and v does not represent a vertex of the graph, the graph is
	 * unchanged. If (u, v) is not an edge of the graph, the graph is unchanged.
	 * 
	 * Running time: O(1).
	 */
	public void removeEdge(Object u, Object v){
		Vertex a = (Vertex) adjListHash.find(new Vertex(u));
		Vertex b = (Vertex) adjListHash.find(new Vertex(v));
		try{
			if ((a!=null) && (b!=null)) {
				Edge query = new Edge(a,b,0);
				Edge e = (Edge) edgeHash.find(query);
				if (e != null){
					e.node1.remove();
					if (e.node2 != null){
					    e.node2.remove();
					}
					edgeHash.remove(e);
				}
			}
		}catch (InvalidNodeException q){
			System.err.println(q);
			q.printStackTrace(); //This shouldn't happen
		}
	}

	/**
	 * isEdge() returns true if (u, v) is an edge of the graph. Returns false if
	 * (u, v) is not an edge (including the case where either of the parameters
	 * u and v does not represent a vertex of the graph).
	 * 
	 * Running time: O(1).
	 */
	public boolean isEdge(Object u, Object v){
		return (edgeHash.find(new Edge(new Vertex(u),new Vertex(v),0)) != null);
	}

	/**
	 * weight() returns the weight of (u, v). Returns zero if (u, v) is not an
	 * edge (including the case where either of the parameters u and v does not
	 * represent a vertex of the graph).
	 * 
	 * (NOTE: A well-behaved application should try to avoid calling this method
	 * for an edge that is not in the graph, and should certainly not treat the
	 * result as if it actually represents an edge with weight zero. However,
	 * some sort of default response is necessary for missing edges, so we
	 * return zero. An exception would be more appropriate, but also more
	 * annoying.)
	 * 
	 * Running time: O(1).
	 */
	public int weight(Object u, Object v){
		Edge e = (Edge) edgeHash.find(new Edge(new Vertex(u),new Vertex(v),0));
		if (e != null){
			return e.weight();
		}
		return 0;
	}
}